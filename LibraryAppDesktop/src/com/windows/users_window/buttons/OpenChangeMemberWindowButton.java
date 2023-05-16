package com.windows.users_window.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.abstraction.AbstractWindowOpenerButton;
import com.table_handler.UserJTable;
import com.windows.change_member_window.ChangeMemberWindow;
@SuppressWarnings("serial")
public class OpenChangeMemberWindowButton extends AbstractWindowOpenerButton {
	/**
	 * This class creates a new ChangeMemberWindow on button click
	 */

	public OpenChangeMemberWindowButton(String buttonName,JFrame frame,UserJTable table) {
		super(buttonName,"changeMemberDataIcon.png",frame,table);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		new ChangeMemberWindow(getFrame(), (UserJTable)getTable());		
	}
}
