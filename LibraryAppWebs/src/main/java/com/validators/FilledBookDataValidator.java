package com.validators;

import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class FilledBookDataValidator {
	/**
	 * 
	 * @param fields -> fields to check
	 * @return -> true if all field contains at least 1 character
	 */
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
	 * 
	 * @param titleField           -> books title
	 * @param authorField          -> books author
	 * @param publicationYearField -> the publication year of the book
	 * @param ISBNField            -> the books accurate ISBN number
	 * @param errorMessage         -> the error message what is changed if error
	 *                             occurs during validating
	 * @return-> true if everything is fine, else false
	 */
	public boolean checkForErrorsInFilledData(JTextField titleField, JTextField authorField,
			JTextField publicationYearField, JTextField ISBNField, JTextField errorMessage) {

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
		if (!isAllFieldFilled(titleField, authorField, publicationYearField, ISBNField)) {
			errorMessage.setText(src.getString("err_field_is_null"));
			return false;
		}
		String author = authorField.getText();
		String publicationYear = publicationYearField.getText();
		String ISBN = ISBNField.getText();

		/*
		 * The author and the title is not validated, that can be anything except empty
		 * field
		 */
		if (!isAuthorValid(author)) {
			errorMessage.setText(src.getString("err_book_author_invalid"));
		}

		if (!isYearValid(publicationYear)) {
			errorMessage.setText(src.getString("err_book_publication_year_invalid"));
			return false;
		}

		if (!isISBNValid(ISBN)) {
			errorMessage.setText(src.getString("err_book_ISBN_invalid"));
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param ISBN -> String represents the books ISBN
	 * @return true if the ISBN contains just numbers or numbers and "-" character
	 *         and length is between 10 and 18 character ,else false
	 * 
	 */
	// its public because its reused validating books ISBN number in FindBook class
	public boolean isISBNValid(String ISBN) {
		if (ISBN.length() > 10) {
			if (Pattern.compile("^[0-9-]{9,18}$")
					.matcher(ISBN)
					.find()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param year -> the year of the publication
	 * @return true if the String contains just numbers and its between 1000 and the
	 *         actual year
	 */
	private boolean isYearValid(String year) {
//if contains a non-digit character
		if (Pattern.compile("[\\D]")
				.matcher(year)
				.find()) {
			return false;
		}
// if the number isn't between 1000 and actual year 
		Integer value = Integer.valueOf(year);
		if (value < 1000 || value > LocalDate.now()
				.getYear()) {
			return false;
		}
		return true;
	}

//just to avoid some special characters in name
	private boolean isAuthorValid(String author) {
		if (Pattern.compile("[<>?)(*&^%$$#@!~]-+/")
				.matcher(author)
				.find()) {
			return false;
		}
		return true;
	}

}
