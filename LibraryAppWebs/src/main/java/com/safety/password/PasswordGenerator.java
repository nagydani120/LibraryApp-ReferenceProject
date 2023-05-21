package com.safety.password;

import java.util.Random;

public class PasswordGenerator {

	private static final char[] SPECIAL_CHARACTERS = new char[] { '!', '@', '+', '*', '?' };
	private static final char[] ALPHABETICAL_CHARACTERS = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] NUMERIC_CHARACTERS = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	private static Random rand = new Random();

	public static void main(String[] args) {
	}

	/*
	 *
	 * This generator returns a char array with the specified characters above this
	 * method, its possible to specify number of iterations for different character
	 * types.
	 * 
	 */

	/**
	 * 
	 * @param specialChars      -> number of special characters
	 * @param numericChars      -> number of numeric characters
	 * @param alphabeticalChars -> number of alphabetical characters
	 * @return -> generated password as char array
	 */
	public static char[] generatePassword(int specialChars, int numericChars, int alphabeticalChars) {

		char[] pass = new char[specialChars + numericChars + alphabeticalChars];

		pass = generateCharFrom(pass, specialChars, SPECIAL_CHARACTERS);
		pass = generateCharFrom(pass, numericChars, NUMERIC_CHARACTERS);
		pass = generateCharFrom(pass, alphabeticalChars, ALPHABETICAL_CHARACTERS);

		return pass;
	}

	private static char[] generateCharFrom(char[] pass, int charToGenerate, char[] generateFrom) {
		for (int i = 0; i < charToGenerate;) {
			int randomIndex = rand.nextInt(pass.length);
			if (pass[randomIndex] == '\u0000') {
				pass[randomIndex] = generateFrom[rand.nextInt(generateFrom.length)];
				i++;
			}

		}
		return pass;
	}

}
