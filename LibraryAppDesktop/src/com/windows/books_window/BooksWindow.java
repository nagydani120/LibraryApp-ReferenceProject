
package com.windows.books_window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.table_handler.BookJTable;
import com.windows.books_window.buttons.DeleteBookButton;
import com.windows.books_window.buttons.FindBookButton;
import com.windows.books_window.buttons.OpenAddBookWindowButton;
import com.windows.books_window.buttons.OpenBookInfoWindowButton;
import com.windows.users_window.BackButton;

public class BooksWindow {
	private BookJTable table;
	private JFrame frame;
	private JFrame menuWindowFrame;

	/*
	 * Just a simple window class with components,showing the custom table and the buttons which gives the functions
	 */
	
	/**
	 * Create the application.
	 */
	public BooksWindow(JFrame menuWindowFrame) {
		this.menuWindowFrame = menuWindowFrame;
		initialize();
		frame.setVisible(true);
		menuWindowFrame.setVisible(false);

	}

	public BooksWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		table = new BookJTable();
		frame.getContentPane()
				.setBackground(new Color(181, 131, 90));
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblBooks = new JLabel("Books");
		lblBooks.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		lblBooks.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(181, 131, 90));
		panel.setLayout(new GridLayout(8, 1, 0, 10));

		/*
		 * Buttons
		 */

		JButton btnAddBook = new OpenAddBookWindowButton("Add Book",frame,table);
		JButton btnBack = new BackButton(menuWindowFrame, frame);
		JButton btnFindBook = new FindBookButton("Find Book", table);
		JButton btnBookInfo = new OpenBookInfoWindowButton("Book Info", frame, table);
		JButton btnDeleteBook = new DeleteBookButton("Delete Book", table);



		/**
		 * these buttons are just blindButtons, they are not visible, it is just created
		 * for back button to be in bottom of window,this layout can't accept setting
		 * the position for the button.. these buttons can be used in future for some function
		 */
		JButton blindSpot1 = new JButton();
		JButton blindSpot2 = new JButton();
		JButton blindSpot3 = new JButton();
		blindSpot1.setVisible(false);
		blindSpot2.setVisible(false);
		blindSpot3.setVisible(false);

		panel.add(btnAddBook);
		panel.add(btnDeleteBook);
		panel.add(btnFindBook);
		panel.add(btnBookInfo);
		panel.add(blindSpot1);
		panel.add(blindSpot2);
		panel.add(blindSpot3);
		panel.add(btnBack);

		JScrollPane scrollPane = new JScrollPane();

		table.getButtonsList().add(btnBookInfo);
		table.getButtonsList().add(btnDeleteBook);
		scrollPane.setViewportView(table);

		frame.getContentPane()
				.add(panel, BorderLayout.WEST);
		frame.getContentPane()
				.add(lblBooks, BorderLayout.NORTH);
		frame.getContentPane()
				.add(scrollPane, BorderLayout.CENTER);
	}

	
	
}
