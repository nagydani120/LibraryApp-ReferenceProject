package com.safety.password;

public class PasswordCheck {

	/**
	 * This method validates the password passed
	 * 
	 * -to validate, i use the characters ASCII value,because storing
	 * 
	 * -the password in String is unsafe due to String pool/immutability
	 * 
	 * - the ASCII values range A-Z a-z is 65-90 + 97-122 and numbers 48-57
	 * 
	 * @param password -> the password as char array
	 * @returns false ->
	 *
	 *         - the password is null 
	 *         - the password`s length is less than 8 
	 *         - the password doesn't contain letter, big letter and number
	 * 
	 */

	public boolean isRegistrationPassValid(char[] password) {
		boolean containsLetter = false;
		boolean containsNumber = false;
		boolean containsBigLetter = false;
		if (password == null) {
			return false;
		}
		if (password.length < 8) {
			return false;
		}
		for (int i = 0; i < password.length; i++) {
			if (password[i] >= 65 && password[i] <= 90) {
				containsBigLetter = true;
			}
			if (password[i] >= 48 && password[i] <= 57) {
				containsNumber = true;
			}
			if (password[i] >= 97 && password[i] <= 122) {
				containsLetter = true;
			}
		}
		return containsLetter && containsNumber && containsBigLetter;
	}

	// checking that the two password matches or not
	public boolean isConfirmationPassMatches(char[] password, char[] confirmPassword) {
		if (password.length != confirmPassword.length) {
			return false;
		}
		for (int i = 0; i < password.length; i++) {
			if (password[i] != confirmPassword[i]) {
				return false;
			}
		}
		return true;
	}

}
