package com.windows.books_window.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.abstraction.AbstractWindowOpenerButton;
import com.table_handler.BookJTable;
import com.windows.books_window.BookInfoWindow;
@SuppressWarnings("serial")
public class OpenBookInfoWindowButton extends AbstractWindowOpenerButton{

	/*
	 * Opens a new BookInfoWindow on click
	 */
	
	public OpenBookInfoWindowButton(String buttonName,JFrame frame,BookJTable table) {
		super(buttonName,"bookInfoIcon.png",frame,table);
		this.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new BookInfoWindow(getFrame(), (BookJTable) getTable());		
	}
}
