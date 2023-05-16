package com.windows.books_window.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.abstraction.AbstractFindButton;
import com.table_handler.BookJTable;

@SuppressWarnings("serial")
public class FindBookButton extends AbstractFindButton implements ActionListener {

	public FindBookButton(String buttonName, BookJTable bookTable) {
		super(buttonName, bookTable);
	}

	/*
	 * finding book using input dialog
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");
//check that book to find is null
		String bookToFind = JOptionPane.showInputDialog(res.getString("find_book"));
		if (bookToFind == null) {
			return;
		}
//if book to find length is less than 2 return a warning message		
		if (bookToFind.length() < 2) {
			JOptionPane.showMessageDialog(null, res.getString("book_to_find_too_short"));
			return;
		}
//if there was no error, the setSelectedBook tries to find the given book, shows a message if no match
		boolean foundAndSet = ((BookJTable) table).setSelectedElement(bookToFind);
		if (!foundAndSet) {
			JOptionPane.showMessageDialog(null, "No match");
		}

	}

}
