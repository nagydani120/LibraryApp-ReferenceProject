package com.windows.users_window.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.abstraction.AbstractFindButton;
import com.table_handler.UserJTable;

@SuppressWarnings("serial")
public class FindMemberButton extends AbstractFindButton implements ActionListener {

	public FindMemberButton(String buttonName, UserJTable table) {
		super(buttonName, table);
	}

	// finding the member, the searched value is given via input dialog
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");
		String userToFind = JOptionPane.showInputDialog(res.getString("find_user"));

		// if the user cancels the finding or exits from JOptionPane InputDialog
		if (userToFind == null) {
			return;
		}

		boolean foundAndSelected = ((UserJTable) table).setSelectedElement(userToFind);
// if the user is not found (ALIAS not selected)
		if (!foundAndSelected) {
			JOptionPane.showMessageDialog(null, "No match!", null, JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
