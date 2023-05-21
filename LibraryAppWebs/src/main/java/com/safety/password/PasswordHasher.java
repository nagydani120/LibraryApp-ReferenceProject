package com.safety.password;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import at.favre.lib.crypto.bcrypt.BCrypt.Verifyer;

public class PasswordHasher {

	/*
	 * This method hashes the password using BCrypt with salt to store the passwords
	 * secured.
	 */
	public static String encryptPassword(char[] passwordToEncrypt) {
		Hasher hasher = BCrypt.withDefaults();
		String passHash = hasher.hashToString(4, passwordToEncrypt);

		return passHash;
	}

	/*
	 * Verifies the password using BCrypt verifier.
	 */
	public static boolean verifyPassword(char[] passwordToVerify, char[] theStoredPasswordHash) {
		Verifyer verifyer = BCrypt.verifyer();
		Result result = verifyer.verify(passwordToVerify, theStoredPasswordHash);
		return result.verified;
	}

}
