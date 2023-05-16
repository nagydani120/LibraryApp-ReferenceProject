package com.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JTextField;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFilledBookDataValidator {

	private static FilledBookDataValidator validator;

	@BeforeClass
	public static void setUp() {
		validator = new FilledBookDataValidator();
	}

	@Test
	public void ShouldReturnFalse_WhenThereIsAtLeastOneEmptyField() {
		JTextField title = new JTextField("Book title");
		JTextField author = new JTextField("Book author");
		JTextField pubYear = new JTextField("2011");
		JTextField ISBN = new JTextField();// this should be the ISBN number
		JTextField errorMsg = new JTextField();// this one isn't checked, the error message gets back in this field

		assertFalse(validator.checkForErrorsInFilledData(title, author, pubYear, ISBN, errorMsg));

	}

	@Test
	public void ShouldReturnTrue_WhenEveryFieldIsValid() {
		JTextField title = new JTextField("Book's title");
		JTextField author = new JTextField("Book's author");
		JTextField pubYear = new JTextField("2011");// this should be the ISBN number
		JTextField ISBN = new JTextField("14-2447-247-5286");
		JTextField errorMsg = new JTextField();// this one isn't checked, the error message gets back in this field

		assertTrue(validator.checkForErrorsInFilledData(title, author, pubYear, ISBN, errorMsg));

	}

	@Test
	public void ShouldChangeTheErrorField_WhenThereIsAnInvalidField() {
		JTextField title = new JTextField("Book's title");
		JTextField author = new JTextField("Book's author");
		JTextField pubYear = new JTextField("20a1"); //non digit character in year field
		JTextField ISBN = new JTextField("14-2447-247-5286");
		JTextField errorMsg = new JTextField();

		String errorMsgBefore = errorMsg.getText();
		validator.checkForErrorsInFilledData(title, author, pubYear, ISBN, errorMsg);
		String errorMsgAfter = errorMsg.getText();

		assertNotEquals(errorMsgBefore, errorMsgAfter);
	}

	@Test
	public void ShouldReturnTrue_WhenISBN_IsValid() {
		// with or without "-" mark
		assertTrue(validator.isISBNValid("22-1457-4474-145"));
		assertTrue(validator.isISBNValid("2214574474145"));
		assertTrue(validator.isISBNValid("841-154-24-114-2"));
	}
	@Test
	public void ShouldReturnFalse_WhenISBN_IsNotValid() {
		assertFalse(validator.isISBNValid("245"));
		assertFalse(validator.isISBNValid("2?485?151?#$@!41"));
		assertFalse(validator.isISBNValid("as142vvfd4dsa"));
		assertFalse(validator.isISBNValid("2-201-4-04-404-04-40--5"));
		assertFalse(validator.isISBNValid("285-24x-296-256-15"));
		assertFalse(validator.isISBNValid("abcds"));
	}

}
