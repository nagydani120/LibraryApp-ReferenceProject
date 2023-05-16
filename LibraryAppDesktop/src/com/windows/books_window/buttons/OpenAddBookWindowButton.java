package com.windows.books_window.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.abstraction.AbstractWindowOpenerButton;
import com.table_handler.BookJTable;
import com.windows.books_window.AddBookWindow;

@SuppressWarnings("serial")
public class OpenAddBookWindowButton extends AbstractWindowOpenerButton {

	/*
	 * Opens a new AddBookWindow on click
	 */
	public OpenAddBookWindowButton(String buttonName, JFrame frame, BookJTable table) {
		super(buttonName, "addBookIcon.png", frame, table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new AddBookWindow(getFrame(), (BookJTable) getTable());
	}
}
