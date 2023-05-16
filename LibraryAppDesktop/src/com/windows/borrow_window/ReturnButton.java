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
import com.windows.borrow_window.panels.ReturnPanel;

@SuppressWarnings("serial")
public class ReturnButton extends JButton implements ActionListener {
	private JList<Book> books;
	private Person person;
	private ReturnPanel panel;

	public ReturnButton(String buttonName, JList<Book> books, Person person, ReturnPanel panel) {
		super(buttonName);
		this.setEnabled(false);
		this.setFocusPainted(false);
		this.addActionListener(this);
		this.setBounds(160, 182, 150, 25);
		this.books = books;
		this.person = person;
		this.panel = panel;
		
		//adding a listSelectionListener to set the button enabled when item is selected from the list
		books.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ReturnButton.this.setEnabled(true);
			}
		});
	}


	
	// process of book return 
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");
		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();

		Book bookToReturn = books.getSelectedValue();

		if (transfer.returnBook(person, bookToReturn)) {
			JOptionPane.showMessageDialog(null, res.getString("book_return_successful"));
			panel.fillListWithBooks();
			this.setEnabled(false);
		}
	}

}
