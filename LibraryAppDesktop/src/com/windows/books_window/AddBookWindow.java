package com.windows.books_window;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.entities.Genre;
import com.table_handler.BookJTable;
import com.windows.books_window.buttons.AddBookButton;

public class AddBookWindow {

	private JFrame frame;
	private JTextField titleField;
	private JTextField authorField;
	private JComboBox<String> genre;
	private JTextField ISBNfield;
	private JTextField yearField;
	private JTextField txtErr;
	private JFrame motherFrame;
	private BookJTable bookTable;

	/**
	 * Create the application. This class is created for showing a window for adding
	 * a new book to database
	 */
	public AddBookWindow() {
		initialize();
	}

	public AddBookWindow(JFrame booksWindow, BookJTable bookTable) {
		initialize();
		this.frame.setVisible(true);
		this.bookTable = bookTable;
		this.motherFrame = booksWindow;
		motherFrame.setEnabled(false);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTitleField() {
		return titleField;
	}

	public JTextField getErrorField() {
		return txtErr;
	}

	public JTextField getAuthorField() {
		return authorField;
	}

	public JTextField getYearField() {
		return yearField;
	}

	public JTextField getISBNField() {
		return ISBNfield;
	}

	public JComboBox<String> getGenreBox() {
		return genre;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				motherFrame.setEnabled(true);
				bookTable.refreshTableAndDisableButtons();
				frame.dispose();

			}
		});

		frame.setResizable(false);
		frame.getContentPane()
				.setBackground(new Color(181, 131, 90));
		frame.getContentPane()
				.setLayout(null);

		JLabel lblTitle = new JLabel("Title");
		JLabel lblNewLabel = new JLabel("Author");
		JLabel lblIsbn = new JLabel("ISBN");
		JLabel lblGenre = new JLabel("Genre");
		JLabel lblPublicationYear = new JLabel("Publication year");

		lblTitle.setBounds(28, 33, 70, 15);
		lblNewLabel.setBounds(28, 60, 70, 15);
		lblPublicationYear.setBounds(28, 119, 124, 15);
		lblIsbn.setBounds(28, 150, 70, 15);
		lblGenre.setBounds(28, 92, 70, 15);

		frame.getContentPane()
				.add(lblGenre);
		frame.getContentPane()
				.add(lblTitle);
		frame.getContentPane()
				.add(lblNewLabel);
		frame.getContentPane()
				.add(lblPublicationYear);
		frame.getContentPane()
				.add(lblIsbn);

		AddBookButton btnAddBook = new AddBookButton("Add Book", this);
		frame.getContentPane()
				.add(btnAddBook);

		titleField = new JTextField();
		authorField = new JTextField();
		genre = new JComboBox<>();
		ISBNfield = new JTextField();
		yearField = new JTextField();
		txtErr = new JTextField();

		titleField.setBounds(152, 31, 207, 19);
		authorField.setBounds(152, 58, 207, 19);
		ISBNfield.setBounds(152, 148, 144, 19);
		genre.setBounds(152, 87, 110, 24);
		yearField.setBounds(152, 117, 56, 19);
		txtErr.setBounds(28, 179, 331, 19);
		frame.setBounds(100, 100, 383, 288);

		yearField.setColumns(10);
		titleField.setColumns(10);
		ISBNfield.setColumns(10);
		authorField.setColumns(10);
		txtErr.setColumns(10);

		txtErr.setBorder(BorderFactory.createEmptyBorder());
		txtErr.setForeground(new Color(224, 27, 36));
		txtErr.setEditable(false);
		txtErr.setBackground(new Color(181, 131, 90));
		txtErr.setHorizontalAlignment(SwingConstants.CENTER);

		frame.getContentPane()
				.add(ISBNfield);
		frame.getContentPane()
				.add(genre);
		frame.getContentPane()
				.add(authorField);
		frame.getContentPane()
				.add(titleField);
		frame.getContentPane()
				.add(yearField);
		frame.getContentPane()
				.add(txtErr);

		fillComboBoxWithGenres(genre);

	}

	/**
	 * Adding all Genre to ComboBox one by one
	 */
	private void fillComboBoxWithGenres(JComboBox<String> genres) {
		ArrayList<Genre> genreList = Genre.getGenres();
		genreList.forEach(g -> {
			genres.addItem(g.toString());
		});
	}

}
