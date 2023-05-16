package com.windows.borrow_window.panels;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.database_manager.BookDatabaseDataTransfer;
import com.entities.Book;
import com.entities.Person;
import com.windows.borrow_window.ReturnButton;

@SuppressWarnings("serial")
public class ReturnPanel extends JPanel {
	private Person person;
	private JList<Book> list;

	public ReturnPanel(Person person) {
		this.person = person;
		initialize();
		fillListWithBooks();
	}

	private void initialize() {
		this.setLayout(null);
		this.setBackground(new Color(181, 131, 90));
		
		JScrollPane scrollPane = new JScrollPane();
		
		list = new JList<>();
		list.setBackground(new Color(205, 171, 143));

		scrollPane.setViewportView(list);
		scrollPane.setBounds(30, 12, 433, 147);

		
		JButton returnBookButton = new ReturnButton("Return Book", list, person, this);
		
		
		this.add(returnBookButton);
		this.add(scrollPane);
	}

	/**
	 * fill the list with the persons actually borrowed books
	 */
	public void fillListWithBooks() {
		list.setModel(new AbstractListModel<Book>() {
			BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
			Book[] books = transfer.getAllBook()
					.values()
					.stream()
					.filter(b -> b.getActualBorrower()
							.equals(person.getId()) && b.isActuallyBorrowed())
					.toList()
					.toArray(new Book[0]);

			@Override
			public int getSize() {
				return books.length;
			}

			@Override
			public Book getElementAt(int index) {
				return books[index];
			}

		});
	}

}
