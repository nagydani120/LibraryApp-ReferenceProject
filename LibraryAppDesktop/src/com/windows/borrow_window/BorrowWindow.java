package com.windows.borrow_window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.entities.Person;
import com.table_handler.UserJTable;
import com.windows.borrow_window.panels.BorrowPanel;

public class BorrowWindow  {

	private JFrame frame;
	private Person person;
	private JFrame motherFrame;
	private JPanel panel;
	private JButton switchPanelButton;


	
	/**
	 * Create the application.
	 */
	public BorrowWindow() {
		initialize();
	}

	public BorrowWindow(JFrame motherFrame, UserJTable table) {
		this.person = table.getSelectedElement();
		this.motherFrame = motherFrame;
		initialize();
		frame.setVisible(true);
		motherFrame.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane()
				.setBackground(new Color(181, 131, 90));
		frame.setBounds(100, 100, 550, 400);
		frame.setResizable(false);
		// adding a custom window closing command
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				motherFrame.setEnabled(true);
				frame.dispose();
			}
		});

		// Showing the selected members id and name on the window
		JLabel lblSelectedMemberid = new JLabel("Selected Member: ID" + person.getId() + " (" + person.getFirstName()
				+ " " + person.getLastName() + ")");
		
		lblSelectedMemberid.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSelectedMemberid.setHorizontalAlignment(SwingConstants.CENTER);

		//basically the Borrow Panel shows up
		panel = new BorrowPanel(person);
		
		switchPanelButton = new SwitchPanelButton(frame,panel,person);


		frame.getContentPane()
				.add(panel, BorderLayout.CENTER);
		frame.getContentPane()
				.add(switchPanelButton, BorderLayout.SOUTH);
		frame.getContentPane()
				.add(lblSelectedMemberid, BorderLayout.NORTH);


	}
}
