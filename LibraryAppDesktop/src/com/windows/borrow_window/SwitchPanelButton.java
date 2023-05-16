package com.windows.borrow_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.entities.Person;
import com.windows.borrow_window.panels.BorrowPanel;
import com.windows.borrow_window.panels.ReturnPanel;

@SuppressWarnings("serial")
public class SwitchPanelButton extends JButton implements ActionListener{

	
	// if the borrow panel is true the borrow panel shows, if false the return panel
	// is changed to return panel
	private boolean switchedToBorrowPanel = true;
	private JFrame frame;
	private JPanel panel;
	private Person person;
	
	/*
	 * creating the panel, basically the borrow menu shows up
	 */
	public SwitchPanelButton(JFrame frame,JPanel panel,Person person) {
	super("Switch to Return Books Menu");
	this.frame = frame;
	this.panel = panel;
	this.person = person;
	this.setFocusPainted(false);
	this.addActionListener(this);
	}
	
	/**
	 * Changing the text on button when the panel is switched
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switchedToBorrowPanel = !switchedToBorrowPanel;
		frame.remove(panel);
		if (switchedToBorrowPanel) {
			this.setText("Switch to Return Books Menu");
			frame.add(panel = new BorrowPanel(person));
		} else {
			this.setText("Switch to Borrow Books Menu");
			frame.add(panel = new ReturnPanel(person));
		}
		frame.revalidate();
	}
	

}
