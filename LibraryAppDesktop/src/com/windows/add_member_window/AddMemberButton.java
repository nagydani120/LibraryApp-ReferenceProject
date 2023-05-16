package com.windows.add_member_window;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.abstraction.AbstractMemberButton;
import com.abstraction.AbstractMemberWindow;
import com.database_manager.EntityDatabaseDataTransfer;
import com.entities.Person;
import com.safety.password.PasswordGenerator;
import com.safety.password.PasswordHasher;
import com.send_email.EmailSender;

@SuppressWarnings("serial")
public class AddMemberButton extends AbstractMemberButton {

	/**
	 * The class is created handle the registration process,using validators,
	 * password hasher, database connection and email sending.
	 */

	private AbstractMemberWindow memberWindow;

	public AddMemberButton(String name, AbstractMemberWindow member) {
		super(name, member);
		this.memberWindow = member;
		this.setFocusable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");
		if (validateFilledData(null)) {

			if (registerPersonToDatabase()) {

				/*
				 * if no errors email sender object is created and the password is sent to
				 * member, finally message displays that the registration is successful
				 */

				EmailSender email = new EmailSender();
				String fullName = getFirstNameFieldText() + " " + getLastNameFieldText();

				// !!!!!!!!!
				// COMMENTED OUT BECAUSE OF BLOCK IN EMAIL ADDRESS CUZ OF MUCH TEST
				email.sendRegisterEmail(getEmailFieldText(), fullName, getPass());

				JOptionPane.showMessageDialog(null, res.getString("reg_successful"));

			} else {
				JOptionPane.showMessageDialog(null, res.getString("reg_error"));
			}
			memberWindow.getFrame()
					.getWindowListeners()[0].windowClosing(null);
		}

	}

	private boolean registerPersonToDatabase() {
		/*
		 * creating random password for the user what can be used to login on the page,
		 * the transfer object sends the entities data to database
		 */
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();

		pass = PasswordGenerator.generatePassword(2, 2, 6);
		String encryptedPassword = PasswordHasher.encryptPassword(pass);

		Person person = new Person(getFirstNameFieldText(), getLastNameFieldText(),
				getBirthDayFromComboBoxAsLocalDate(), getSelectedGender(), getAddressFieldText(), getPhoneFieldText(),
				getEmailFieldText());

		return transfer.registerEntity(encryptedPassword, person);

	}
}
