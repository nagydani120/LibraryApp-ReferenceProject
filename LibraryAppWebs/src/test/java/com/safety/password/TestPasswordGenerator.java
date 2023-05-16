package com.safety.password;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

public class TestPasswordGenerator {

	@Test
	public void ShouldReturnPassExcatlyAsLong_AsWasGiven() {
		char[] pass = PasswordGenerator.generatePassword(4, 4, 4);
		assertEquals(pass.length, 12);
	}

	@Test
	public void ShouldContaintExcatlyAsMuchSpecialCharacter_AsWasGiven() throws Exception {
		Field field = PasswordGenerator.class.getDeclaredField("SPECIAL_CHARACTERS");
		field.setAccessible(true);

		int specialCharacterCount = 8;

		char[] declared_special_characters = (char[]) field.get(null);
		char[] pass = PasswordGenerator.generatePassword(specialCharacterCount, 1, 1);

		int specialCharactersPresent = 0;
		for (int i = 0; i < pass.length; i++) {
			for (int j = 0; j < declared_special_characters.length; j++) {
				if (pass[i] == declared_special_characters[j]) {
					specialCharactersPresent++;
				}
			}
		}
		assertEquals(specialCharacterCount, specialCharactersPresent);
	}
}
