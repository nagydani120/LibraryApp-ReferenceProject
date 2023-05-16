package com.safety.password;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPasswordCheck {

	private static PasswordCheck check;

	@BeforeClass
	public static void setUp() {
		check = new PasswordCheck();
	}

	@Test
	public void ShouldReturnFalse_WhenPasswordIsNull() {
		assertFalse(check.isRegistrationPassValid(null));
	}

	@Test
	public void ShouldReturnFalse_WhenPasswordLenghtIsLessThan8CharLong() {
		assertFalse(check.isRegistrationPassValid("Abcd1ab".toCharArray()));
	}

	@Test
	public void ShouldReturnFalse_WhenPasswordDoasntContainLetterAndBigLetterAndNumber() {
		assertFalse(check.isRegistrationPassValid("12345678".toCharArray()));
		assertFalse(check.isRegistrationPassValid("a1b2c3d4".toCharArray()));
		assertFalse(check.isRegistrationPassValid("A1B2C3D4".toCharArray()));
	}

	@Test
	public void ShouldReturnTrue_WhenPasswordIsValid() {
		assertTrue(check.isRegistrationPassValid("aBcd123*4x/".toCharArray()));
	}

	@Test
	public void ShouldReturnTrue_WhenConfirmationPasswordMatches() {
		assertTrue(check.isConfirmationPassMatches("aBcdx12R".toCharArray(), "aBcdx12R".toCharArray()));
	}

	@Test
	public void ShouldReturnFalse_WhenConfirmationPasswordDoasntMatches() {
		assertFalse(check.isConfirmationPassMatches("aBcdx12R".toCharArray(), "XTx14RX".toCharArray()));
	}

}
