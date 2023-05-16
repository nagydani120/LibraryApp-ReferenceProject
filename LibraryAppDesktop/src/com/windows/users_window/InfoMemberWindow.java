package com.windows.users_window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.database_manager.BookDatabaseDataTransfer;
import com.entities.Book;
import com.entities.Person;
import com.table_handler.UserJTable;

/**
 * 
 * This class is used to show up the members informations in a new Frame.
 */
@SuppressWarnings("serial")
public class InfoMemberWindow {

	private JFrame frame;
	private JFrame mainFrame;
	private Person person;
	private JTextPane personInfoText;

	/**
	 * Create the application.
	 */
	public InfoMemberWindow(JFrame mainFrame, UserJTable table) {
		this.person = table.getSelectedElement();
		this.mainFrame = mainFrame;
		initialize();
		writeInfo();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 650, 600);
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
		personInfoText = new JTextPane() {
			@Override
			protected void paintComponent(Graphics g) {
				Image img = new ImageIcon(getClass().getClassLoader().getResource("images/backg.jpg")).getImage();
				g.drawImage(img, 0, 0, this);
				super.paintComponent(g);
			}
		};
		personInfoText.setOpaque(false);
		personInfoText.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
		personInfoText.setEditable(false);
		personInfoText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));

		/**
		 * This code is from stackOverFlow, I don't really know these settings so i
		 * created mine based on that code the code aligns the text to center
		 */

		StyledDocument docStyle = personInfoText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		docStyle.setParagraphAttributes(0, docStyle.getLength(), center, false);
		/**
		 * 
		 */
		frame.getContentPane()
				.add(personInfoText, BorderLayout.CENTER);
	}

	/**
	 * The info is represented as String, so StringBuilder is used to write out the
	 * informations about the member
	 */
	private void writeInfo() {

		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
		ArrayList<Book> personsBook = transfer.getPersonsBook(person.getId());

		StringBuilder sb = new StringBuilder();
		sb.append("\n" + person.getFirstName() + " " + person.getLastName());
		sb.append(" (" + "ID: " + person.getId() + ")");
		sb.append("\n" + "Date of Birth: " + person.getDateOfBirth());
		sb.append(" (" + calculateAge(person.getDateOfBirth()) + " years old)");
		sb.append("\n\nAddress:\n" + person.getAddress());
		sb.append("\n\nPhone number:\n" + person.getPhoneNumber());
		sb.append("\n\n" + "Actual borrowed books: ");
		sb.append(personsBook.size() == 0 ? "\nNo borrowed books actually" : "");

		for (int i = 0; i < personsBook.size(); i++) {
			sb.append("\n" + (i + 1) + ". " + personsBook.get(i)
					.getBookTitle());
			sb.append(" (" + personsBook.get(i)
					.getBookAuthor() + ")\n");
		}

		personInfoText.setText(sb.toString());
	}

	// this method could be at Person class and get the age via getter (for example
	// getPersonsAge)
	// but I use this method just once, i choose to let here this method now
	private int calculateAge(LocalDate birthDay) {
		Period period = birthDay.until(LocalDate.now());

		return period.getYears();
	}

}
