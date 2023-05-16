package com.windows.add_member_window;

import javax.swing.JFrame;

import com.abstraction.AbstractMemberWindow;
import com.table_handler.UserJTable;

public class AddMemberWindow extends AbstractMemberWindow {

	/**
	 *creating the member window with custom button 
	 */
	public AddMemberWindow(JFrame mainFrame, UserJTable table) {
		super(mainFrame, "Add member", table);
		getFrame().getContentPane()
				.add(new AddMemberButton("Register", this));

	}

}
