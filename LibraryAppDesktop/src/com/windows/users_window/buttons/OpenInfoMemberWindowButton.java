package com.windows.users_window.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.abstraction.AbstractWindowOpenerButton;
import com.table_handler.UserJTable;
import com.windows.users_window.InfoMemberWindow;

@SuppressWarnings("serial")
public class OpenInfoMemberWindowButton extends AbstractWindowOpenerButton {
	/**
	 * This class creates a new InfoMemberWindow on button click
	 */

	public OpenInfoMemberWindowButton(String buttonName, JFrame frame, UserJTable table) {
		super(buttonName, "memberInfoIcon.png", frame, table);
		this.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new InfoMemberWindow(getFrame(), (UserJTable) getTable());
	}
}
