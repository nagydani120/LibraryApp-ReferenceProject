package com.windows.borrow_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.database_manager.BookDatabaseDataTransfer;
import com.entities.Book;
import com.entities.Person;

@SuppressWarnings("serial")

public class BorrowButton extends JButton implements ActionListener {

	private Person person;
	private JList<Book> books;
	private SearchBookButton searchBookButton;
	
	// the search button is needed after borrowing to fill the list with books again with setBookList()
	public BorrowButton(String buttonName, JList<Book> books, Person person,JButton searchBookButton) {
		super(buttonName);
		this.books = books;
		this.person = person;
		this.searchBookButton = (SearchBookButton)searchBookButton;
		initialize();
	}

	private void initialize() {
		this.addActionListener(this);
		this.setFocusPainted(false);
		this.setBounds(141, 182, 203, 25);
		this.setEnabled(false);

		// adding a list selection listener to enable the button when item is selected
		books.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				BorrowButton.this.setEnabled(true);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");

		Book bookToBorrow = books.getSelectedValue();

		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
		if (transfer.borrowBook(person, bookToBorrow)) {
			JOptionPane.showMessageDialog(null, res.getString("borrow_successful"));
			books.setListData(new Book[0]);
			this.setEnabled(false);
			searchBookButton.setBookList();
		}

	}

}
