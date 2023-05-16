package com.abstraction;

import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.entities.Person;
import com.validators.FilledUserDataValidator;

/*
 * 
 * This abstract class is inherited by the AddMemberButton and the ChangeMemberDataButton.
 * The window what is used for AddMemberButton is the same as ChangeMemberDataButton, just the logic
 * is similar.
 * 
 *  ----> The add member button:
 *  -- creates a new member
 *  -- the administrator have to fill all the data to register
 *  -- generates a random password for the member
 *  -- sends an email to members address with the generated password 
 *  -- registers the member to database 
 *  
 *  
 *  ----> The change button:
 *  -- fills the fields with the actual data got from the database
 *  -- the administrator can change the values(just valid informations, the validator works here too)
 *  -- updates the members data in database 
 * 
 * */

@SuppressWarnings("serial")
public abstract class AbstractMemberButton extends JButton implements ActionListener {

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField errorMessage;
	private JComboBox<Integer> years;
	private JComboBox<Integer> months;
	private JComboBox<Integer> days;
	private ButtonGroup genders;
	protected char[] pass;

	/*
	 * The MemberWindow as parameter... this is because i need all the fields from
	 * the register window, it is saved to a Person object, and this person Object
	 * is sent to database.
	 */

	public AbstractMemberButton(String name, AbstractMemberWindow member) {
		super(name);
		this.firstNameField = member.getFirstNameField();
		this.lastNameField = member.getLastNameField();
		this.addressField = member.getAdressField();
		this.phoneField = member.getPhoneField();
		this.emailField = member.getEmailField();
		this.errorMessage = member.getErrorMsg();
		this.years = member.getYearBox();
		this.months = member.getMonthBox();
		this.days = member.getDayBox();
		this.genders = member.getGenders();
		this.setFocusable(false);
		this.addActionListener(this);
		this.setBounds(140, 264, 117, 25);

	}

	public LocalDate getDateOfBirth() {
		return LocalDate.of((Integer) years.getSelectedItem(), (Integer) months.getSelectedItem(),
				(Integer) days.getSelectedItem());
	}

	public String getSelectedGender() {
		return genders.getSelection()
				.getActionCommand();
	}

	public String getFirstNameFieldText() {
		return firstNameField.getText();
	}

	public String getLastNameFieldText() {
		return lastNameField.getText();
	}

	public String getEmailFieldText() {
		return emailField.getText();
	}

	public char[] getPass() {
		return pass;
	}

	public String getAddressFieldText() {
		return addressField.getText();
	}

	public String getPhoneFieldText() {
		return phoneField.getText();
	}

	/**
	 * 
	 * @param p -> if the method call is from the ChangeMember, the persons actual
	 *          email address doasn't count as new email address in
	 *          FilledDataValidator thats why is needed the Persons data.
	 * 
	 *          It can be null for the call from the AddMember class because there
	 *          is no need to check.
	 * @return true if everything is valid,else false
	 */
	protected boolean validateFilledData(Person p) {
		FilledUserDataValidator validator = new FilledUserDataValidator();
		return validator.checkForErrorsInFilledData(firstNameField, lastNameField, addressField, phoneField, emailField,
				errorMessage, p);
	}

	/**
	 * 
	 * @return -> a LocalDate representing the values from Day, Month and Year
	 *         ComboBox.
	 */
	public LocalDate getBirthDayFromComboBoxAsLocalDate() {
		int year = (Integer) years.getSelectedItem();
		int month = (Integer) months.getSelectedItem();
		int day = (Integer) days.getSelectedItem();
		return LocalDate.of(year, month, day);
	}

}
