package com.windows.users_window.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.abstraction.AbstractWindowOpenerButton;
import com.table_handler.UserJTable;
import com.windows.add_member_window.AddMemberWindow;

@SuppressWarnings("serial")
public class OpenAddMemberWindowButton extends AbstractWindowOpenerButton {
	/**
	 * This class creates a new AddMemberWindow on button click
	 */

	public OpenAddMemberWindowButton(String buttonName, JFrame frame, UserJTable table) {
		super(buttonName, "addMemberIcon.png", frame, table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new AddMemberWindow(getFrame(), (UserJTable) getTable());
	}
}
