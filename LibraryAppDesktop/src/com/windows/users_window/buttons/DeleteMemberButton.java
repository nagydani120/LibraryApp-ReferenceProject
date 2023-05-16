package com.windows.users_window.buttons;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.database_manager.EntityDatabaseDataTransfer;
import com.database_manager.UnableToDeleteException;
import com.table_handler.UserJTable;

@SuppressWarnings("serial")
public class DeleteMemberButton extends JButton implements ActionListener {

	private UserJTable table;

	public DeleteMemberButton(String buttonName, UserJTable table) {
		super(buttonName);
		this.table = table;
		this.addActionListener(this);
		this.setFocusPainted(false);
		setIcon();
	}

	// deleting process
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();
		Integer id = table.getSelectedElement()
				.getId();

		try {
			if (confirmDelete(id)) {
				if (transfer.deletePerson(id)) {
					JOptionPane.showMessageDialog(null, res.getString("member_delete_successful"));
				}
			}
		} catch (UnableToDeleteException de) {
			JOptionPane.showMessageDialog(null, res.getString("member_delete_error"), null, JOptionPane.ERROR_MESSAGE);

		}
		// refresh, the focus is no longer on table, thats why is needed to disable buttons,
		// after the table gets focus the buttons are available again
		table.refreshTableAndDisableButtons();
		
	}

// confirmation message about delete
	private boolean confirmDelete(Integer id) {
		ResourceBundle res = ResourceBundle.getBundle("messages");

		// getting the ID what is used in database as PRIMARY_KEY

		int answer = JOptionPane.showConfirmDialog(table, res.getString("member_delete_confirm") + id + "?",
				"Delete Member", JOptionPane.YES_NO_OPTION);

		return answer == 0;
	}

	private void setIcon() {
		Image deleteMemberImage = new ImageIcon(getClass().getClassLoader().getResource("images/icons/deleteMemberIcon.png")).getImage();
		Image scaledDeleteMemberImg = deleteMemberImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon deleteMemberIcon = new ImageIcon(scaledDeleteMemberImg);
		this.setIcon(deleteMemberIcon);
	}

}
