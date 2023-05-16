package com.windows.change_member_window;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.abstraction.AbstractMemberButton;
import com.abstraction.AbstractMemberWindow;
import com.database_manager.EntityDatabaseDataTransfer;
import com.entities.Person;

@SuppressWarnings("serial")
public class ChangeMemberDataButton extends AbstractMemberButton {

	private Person personToChange;
	private AbstractMemberWindow memberWindow;

	public ChangeMemberDataButton(String name, AbstractMemberWindow member, Person personToChange) {
		super(name, member);
		this.memberWindow = member;
		this.personToChange = personToChange;
		this.setFocusable(false);
	}

// updating process, after update the window closes
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");

		if (validateFilledData(personToChange)) {

			EntityDatabaseDataTransfer trans = new EntityDatabaseDataTransfer();
			refreshPerson(personToChange);

			if (trans.updatePerson(personToChange)) {

				JOptionPane.showMessageDialog(null, res.getString("upd_successful"));

			} else {
				JOptionPane.showMessageDialog(null, res.getString("upd_error"));
			}

			memberWindow.getFrame()
					.getWindowListeners()[0].windowClosing(null);
		}
	}

	private void refreshPerson(Person p) {
		p.setFirstName(getFirstNameFieldText());
		p.setLastName(getLastNameFieldText());
		p.setDateOfBirth(getBirthDayFromComboBoxAsLocalDate());
		p.setGender(getSelectedGender());
		p.setAddress(getAddressFieldText());
		p.setPhoneNumber(getPhoneFieldText());
		p.setEmailAddress(getEmailFieldText());
	}

}
