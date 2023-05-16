package com.windows.books_window.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.database_manager.BookDatabaseDataTransfer;
import com.entities.Book;
import com.entities.Genre;
import com.validators.FilledBookDataValidator;
import com.windows.books_window.AddBookWindow;

@SuppressWarnings("serial")
public class AddBookButton extends JButton implements ActionListener {

	private AddBookWindow window;
	public AddBookButton(String buttonName, AddBookWindow window) {
		super(buttonName);
		this.window = window;
		this.addActionListener(this);
		this.setBounds(126, 208, 117, 25);
		this.setFocusPainted(false);
		
	}

	/**
	 * Adding process with validating, registering, closing the window
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (validateBookFields()) {
			if (registerBookToDatabase()) {
				ResourceBundle res = ResourceBundle.getBundle("messages");
				JOptionPane.showMessageDialog(null, res.getString("book_reg_successful"));

				window.getFrame()
						.getWindowListeners()[0].windowClosing(null);
			}
		}

	}

	/*
	 * getting the values from filled fields , creating a book object and sending to
	 * database using BookDatabaseTransfer
	 * 
	 */
	private boolean registerBookToDatabase() {
		BookDatabaseDataTransfer trasnfer = new BookDatabaseDataTransfer();

		String title = window.getTitleField()
				.getText();
		String author = window.getAuthorField()
				.getText();
		String isbn = window.getISBNField()
				.getText();
		Integer year = Integer.valueOf(window.getYearField()
				.getText());
		// casting back to Genre
		Genre genre = Genre.valueOf(window.getGenreBox()
				.getSelectedItem()
				.toString()
				.toUpperCase());

		Book bookToRegister = new Book(year, title, author, isbn, genre);
		return trasnfer.registerBook(bookToRegister);
	}

	/**
	 * getting the fields and sending to validator's method to check
	 * 
	 * @return -> true if everything is valid
	 */
	private boolean validateBookFields() {
		JTextField titleField = window.getTitleField();
		JTextField authorField = window.getAuthorField();
		JTextField yearField = window.getYearField();
		JTextField isbnField = window.getISBNField();
		JTextField txtErr = window.getErrorField();

		FilledBookDataValidator validator = new FilledBookDataValidator();
		return validator.checkForErrorsInFilledData(titleField, authorField, yearField, isbnField, txtErr);

	}

}
