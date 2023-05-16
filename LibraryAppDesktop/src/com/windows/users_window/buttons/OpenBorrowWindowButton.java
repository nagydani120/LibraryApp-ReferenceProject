package com.windows.users_window.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.abstraction.AbstractWindowOpenerButton;
import com.table_handler.UserJTable;
import com.windows.borrow_window.BorrowWindow;
@SuppressWarnings("serial")
public class OpenBorrowWindowButton extends AbstractWindowOpenerButton {
	/**
	 * This class creates a new BorrowWindow on button click
	 */

	public OpenBorrowWindowButton(String buttonName,JFrame frame,UserJTable table) {
		super(buttonName,"borrowReturnIcon.png",frame,table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new BorrowWindow(getFrame(), (UserJTable)getTable());		
	}
}
