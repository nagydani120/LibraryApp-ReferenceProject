package com.windows.books_window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.database_manager.EntityDatabaseDataTransfer;
import com.entities.Book;
import com.entities.Person;
import com.table_handler.BookJTable;

@SuppressWarnings("serial")
public class BookInfoWindow {

	private JFrame frame;
	private JFrame mainFrame;
	private JTextPane bookInfoText;
	private Book book;

	/**
	 * Create the application. The class creates a window that shows informations
	 * about the selected book.
	 */
	public BookInfoWindow() {
		initialize();
	}

	public BookInfoWindow(JFrame mainFrame, BookJTable table) {
		initialize();
		this.book = table.getSelectedElement();
		this.mainFrame = mainFrame;
		writeInfo();
		frame.setVisible(true);
		mainFrame.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 400);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mainFrame.setEnabled(true);
				frame.dispose();
			}
		});

		/*
		 * Painting the image as background before the text appears.
		 */
		bookInfoText = new JTextPane() {
			@Override
			protected void paintComponent(Graphics g) {
				Image img = new ImageIcon(getClass().getClassLoader().getResource("images/backg.jpg")).getImage();
				g.drawImage(img, 0, 0, this);
				super.paintComponent(g);
			}
		};
		bookInfoText.setOpaque(false);
		bookInfoText.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
		bookInfoText.setEditable(false);
		bookInfoText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));

		/**
		 * This code is from stackOverFlow, I don't really know these settings so i
		 * created mine based on that code the code aligns the text to center
		 */

		StyledDocument docStyle = bookInfoText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		docStyle.setParagraphAttributes(0, docStyle.getLength(), center, false);

		frame.getContentPane()
				.add(bookInfoText, BorderLayout.CENTER);
	}

	/*
	 * The info is represented as String, using StringBuilder the books info is
	 * displayed
	 */
	private void writeInfo() {
		Person borrower = book.getActualBorrower() != 0 ? getBorrower(book.getActualBorrower()) : null;

		StringBuilder sb = new StringBuilder();
		sb.append("\n" + book.getBookTitle());
		sb.append("\n" + book.getBookAuthor());
		sb.append("\n(" + book.getBookGenre() + " , " + book.getBookYearOfPublication() + ")");

		if (borrower != null) {
			sb.append("\n\n Actual borrower: \n" + borrower.getFirstName() + " " + borrower.getLastName());
			sb.append("\n(" + borrower.getEmailAddress() + ")");
			sb.append("\nBorrowed: " + book.getBorrowingDay() + "     Deadline: " + book.getExpectedReturnDay());
		} else {
			sb.append("\n\n Actual borrower: \n No one");
		}

		sb.append("\n\n Borrowed " + book.getTimesBorrowed() + " times");
		sb.append("\n\nISBN:\t" + book.getBookISBN());

		bookInfoText.setText(sb.toString());
	}

	/*
	 * getting the borrower from id
	 */
	private Person getBorrower(Integer id) {
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();
		return transfer.getPerson(id);
	}

}
