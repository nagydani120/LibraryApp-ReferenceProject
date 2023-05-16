package com.windows.borrow_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import com.database_manager.BookDatabaseDataTransfer;
import com.entities.Book;

@SuppressWarnings("serial")
public class SearchBookButton extends JButton implements ActionListener {

	private JTextField searchTextField;
	private JList<Book> bookList;
	
	public SearchBookButton(String bookName, JTextField searchTextField, JList<Book> bookList) {
		super(bookName);
		this.searchTextField = searchTextField;
		this.bookList = bookList;
		this.setFocusable(false);
		this.addActionListener(this);
		this.setBounds(233, 12, 155, 25);
		setBookList(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setBookList();
	}

	/**
	 * fills the bookList with the matching books what is actually not borrowed
	 * the method is package private to be able for borrow panel to use this method after borrowing
	 */
	void setBookList() {
		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
		String searchText = searchTextField.getText();

		Map<Integer, Book> matchingNotBorrowedBooks = transfer.checkForMatch(searchText)
				.entrySet()
				.stream()
				.filter(b -> b.getValue()
						.getActualBorrower() == 0)
				.collect(Collectors.toMap(id -> id.getKey(), b -> b.getValue()));

		if (matchingNotBorrowedBooks.size() != 0) {

			bookList.setModel(new AbstractListModel<Book>() {

				Book[] books = matchingNotBorrowedBooks.values()
						.toArray(new Book[0]);

				public int getSize() {
					return books.length;
				}

				public Book getElementAt(int index) {
					return books[index];
				}

			});
		}
	}

}
