package com.validators;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.database.EntityDatabaseDataTransfer;
import com.entities.Person;
import com.safety.password.PasswordCheck;

public class FilledUserDataValidator {

	/*
	 * 
	 * It will be reused in other registration form, this class checks that the
	 * filled data is good enough to register
	 *
	 */

//Checks that all field is at least has 1 character, this is an precheck to avoid redundant data validation check

	private boolean isAllFieldFilled(JTextField... fields) {
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getText()
					.length() < 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method validates the ADMIN register windows fields , just a basic
	 * control, the validator could be more precise, can be developed
	 * 
	 * @param adminUsername   -> the admin's username
	 * @param adminEmail      -> the admin's email address
	 * @param password        -> the password
	 * @param confirmPassword -> confirm password
	 * @param errorMessage    -> errorMessage, this is used to display the error to
	 *                        user via the windows
	 * @return true if every field is valid, else false
	 */

	public boolean checkForErrorsInFilledData(JTextField adminUsername, JTextField adminEmail, JPasswordField password,
			JPasswordField confirmPassword, JTextArea errorMessage) {

		ResourceBundle resource = ResourceBundle.getBundle("messages");
		String passedUsername = adminUsername.getText();
		String passedEmail = adminEmail.getText();
		char[] passedPassword = password.getPassword();
		char[] passedConfirmPassword = confirmPassword.getPassword();
		/*
		 * Validating the filled data except the password
		 */

		FilledUserDataValidator validator = new FilledUserDataValidator();

		/*
		 * Checking that everything is filled
		 */

		if (!validator.isAllFieldFilled(adminUsername, adminEmail) || passedPassword.length < 1
				|| passedConfirmPassword.length < 1) {
			errorMessage.setText(resource.getString("err_field_is_null"));
			return false;
		}

		if (!validator.isUsernameValid(passedUsername)) {
			errorMessage.setText(resource.getString("err_username_under_criterion"));
			return false;
		}
		if (!validator.isEmailValid(passedEmail)) {
			errorMessage.setText(resource.getString("err_email_not_valid"));
			return false;
		}

		/*
		 * Checking that the password is valid
		 */
		PasswordCheck passCheck = new PasswordCheck();

		if (!passCheck.isRegistrationPassValid(passedPassword)) {
			errorMessage.setText(resource.getString("err_password_under_criterion"));
			return false;
		}
		if (!passCheck.isConfirmationPassMatches(passedPassword, passedConfirmPassword)) {
			errorMessage.setText(resource.getString("err_password_not_matching"));
			return false;
		}
		return true;
	}

	/**
	 * This method validates the USER windows fields , just a basic control, the
	 * address validating is in low-level, the validator could be more precise, can
	 * be developed or to refractored to get input as MemberWindow and the get the
	 * fields via MemberWindow's getters (is chose this way now)
	 * 
	 * @param firstNameField -> persons first name field to check
	 * @param lastNameField  -> persons last name field to check
	 * @param addressField   -> persons address field to check
	 * @param phoneField     -> persons phone field to check
	 * @param emailField     -> persons email field to check
	 * @param errorMessage   -> the error field what is changed if error occurs
	 * @param personToChange -> if the method call is from the ChangeMember, the
	 *                       persons actual email address doasn't count as new email
	 *                       address. It can be null for the RegisterMember
	 * @return true if everything fine, else false and error fields text is set
	 */

	public boolean checkForErrorsInFilledData(JTextField firstNameField, JTextField lastNameField,
			JTextField addressField, JTextField phoneField, JTextField emailField, JTextField errorMessage,
			Person personToChange) {

		///

		/*
		 * Checks for the errors, returns false if something is not valid and the error
		 * fields text is set to actual error's message
		 */

		///
		ResourceBundle src = ResourceBundle.getBundle("messages");
		/*
		 * Some pre-check to avoid redundant data validating
		 */
		if (!isAllFieldFilled(firstNameField, lastNameField, addressField, phoneField, emailField)) {
			errorMessage.setText(src.getString("err_field_is_null"));
			return false;
		}

		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String address = addressField.getText();
		String phone = phoneField.getText();
		String email = emailField.getText();

		if (!isNameValid(firstName) || !isNameValid(lastName)) {
			errorMessage.setText(src.getString("err_name_not_valid"));
			return false;
		}
		if (!isAdressValid(address)) {
			errorMessage.setText(src.getString("err_adress_not_valid"));
			return false;
		}
		if (!isPhoneNumberValid(phone)) {
			errorMessage.setText(src.getString("err_phone_not_valid"));
			return false;
		}

		if (!isEmailValid(email)) {
			errorMessage.setText(src.getString("err_email_not_valid"));
			return false;
		}

		// description for personToCheck javadoc for this if branch
		if (personToChange != null && personToChange.getEmailAddress()
				.equals(email)) {
			return true;
		} else if (!isEmailFree(email)) {
			errorMessage.setText(src.getString("err_email_used"));
			return false;
		}

		return true;
	}

	// @formatter:off
	/*
	 * 		Returns false if :
	 * -username is null
	 * -username's length is less than 8 or more than(or equal) 50 
	 * -username doasn't contain letter
	 */
	// @formatter:on

	public boolean isUsernameValid(String username) {
		if (username == null) {
			return false;
		}
		if (username.length() < 8 || username.length() >= 50) {
			return false;
		}
		if (Pattern.compile("[\\.~ !@#$%^&*()_+{}|>?<]")
				.matcher(username)
				.find()) {
			return false;
		}
		return true;
	}

	public boolean isEmailFree(String email) {
		EntityDatabaseDataTransfer trans = new EntityDatabaseDataTransfer();
		return !trans.getAllMember()
				.values()
				.stream()
				.anyMatch(p -> p.getEmailAddress()
						.equals(email));
	}

	// @formatter:off
				/*
				 * 		Returns false if :
				 * -email is null
				 * -email daoasn't match to pattern
				 * 
				 * [no special character in name,just digit and words,
				 *  the '@' sign, domain name '.' and domain extension]
				 */
	// @formatter:on

	public boolean isEmailValid(String email) {
		if (email == null) {
			return false;
		}
		if (!email.matches("^[a-z]+[a-z0-9_\\.]{2,20}[@][a-z]{1,20}[\\.][a-z]{1,8}$")) {
			return false;
		}
		return true;
	}

	private boolean isNameValid(String name) {
		if (name == null) {
			return false;
		}
		if (name.length() < 3) {
			return false;
		}
		/*
		 * if contain number or not some special character return false
		 */
		if (Pattern.compile("[\\d<>?:|}{_)(*&^%$#@!~\\.]")
				.matcher(name)
				.find()) {
			return false;
		}

		return true;
	}

	/**
	 * Basic prototype validator for phone number
	 * 
	 * @param number -> the number to check
	 * @return true if the String contains just numbers or one '+' character at
	 *         beginning, and the number length is at least 9 character long
	 */
	private boolean isPhoneNumberValid(String number) {
		if (number.length() >= 9) {
			if (Pattern.compile("^[+]?[\\d-]{8,15}")
					.matcher(number)
					.find()) {
				return true;
			}
		}
		return false;
	}

	// @formatter:off
	/*
	 * !!! 
	 * The address validation is not really realized yet, but i think i don't
	 * deal with this in this project, now the emphasis is on the connection between
	 * database and program with little web side programming... 
	 * !!!
	 * */
	// @formatter:on

	private boolean isAdressValid(String address) {
		if (address.length() < 6) {
			return false;
		}

		return true;
	}

}
