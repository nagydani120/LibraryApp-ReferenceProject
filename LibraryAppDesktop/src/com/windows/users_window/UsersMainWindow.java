package com.windows.users_window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.table_handler.UserJTable;
import com.windows.users_window.buttons.DeleteMemberButton;
import com.windows.users_window.buttons.FindMemberButton;
import com.windows.users_window.buttons.OpenAddMemberWindowButton;
import com.windows.users_window.buttons.OpenBorrowWindowButton;
import com.windows.users_window.buttons.OpenChangeMemberWindowButton;
import com.windows.users_window.buttons.OpenInfoMemberWindowButton;

/**
 * 
 * This class is created to hold all the components, the table and buttons of
 * the Users window.
 *
 */
public class UsersMainWindow {

	private JFrame mainFrame;
	private JFrame frame;
	private JButton btnFindMember;
	private JButton btnAddMember;
	private DeleteMemberButton btnDeleteMember;
	private JButton btnInfo;
	private JButton btnChangeData;
	private JButton btnBack;
	private UserJTable table;
	private JLabel lblMembers;
	private JButton btnBorrow;

	/**
	 * Create the application.
	 */

	public UsersMainWindow(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initialize();
		this.mainFrame.setVisible(false);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Members");
		frame.getContentPane()
				.setBackground(new Color(205, 171, 143));
		frame.setBounds(100, 100, 770, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(181, 131, 90));
		buttonPanel.setLayout(new GridLayout(8, 0, 0, 15));

		JScrollPane scrollPane = new JScrollPane();
		table = new UserJTable();
		scrollPane.setViewportView(table);

		btnAddMember = new OpenAddMemberWindowButton("Add member", frame, table);
		btnDeleteMember = new DeleteMemberButton("Delete member", table);
		btnFindMember = new FindMemberButton("Find member", table);
		btnInfo = new OpenInfoMemberWindowButton("Member info", frame, table);
		btnChangeData = new OpenChangeMemberWindowButton("Change data", frame, table);
		btnBorrow = new OpenBorrowWindowButton("Borrow/Return", frame, table);
		btnBack = new BackButton(mainFrame, frame);

		// giving to the table buttons reference
		table.getButtonsList().add(btnChangeData);
		table.getButtonsList().add(btnDeleteMember);
		table.getButtonsList().add(btnInfo);
		table.getButtonsList().add(btnBorrow);

		lblMembers = new JLabel("Members");
		lblMembers.setHorizontalAlignment(SwingConstants.CENTER);

		buttonPanel.add(lblMembers);
		buttonPanel.add(btnAddMember);
		buttonPanel.add(btnDeleteMember);
		buttonPanel.add(btnFindMember);
		buttonPanel.add(btnInfo);
		buttonPanel.add(btnChangeData);
		buttonPanel.add(btnBorrow);
		buttonPanel.add(btnBack);

		frame.getContentPane()
				.add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane()
				.add(buttonPanel, BorderLayout.WEST);
		table.refreshTableAndDisableButtons();
	}

}
