package com.abstraction;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 * 
 * This abstract class is for creating the basic for the buttons what has Icon
 * and the main function is to create/open a new JFrame Window (this is realized
 * in ActionListener)
 * 
 */

@SuppressWarnings("serial")
public abstract class AbstractWindowOpenerButton extends JButton implements ActionListener {

	private JFrame frame;
	private JTable table;

	/**
	 * 
	 * 
	 * 
	 * @param buttonName   -> the buttons Text AS name
	 * @param iconPathName -> the icons filename (the class uses basically the
	 *                     src/main/java/images/icons/ +iconPathName.fileextension)
	 * @param frame        -> this frames mother frame (AS the frame what called
	 *                     this frame)
	 * @param table        -> the table (UserJTable or BookJTable)
	 */
	public AbstractWindowOpenerButton(String buttonName, String iconPathName, JFrame frame, JTable table) {
		super(buttonName);
		this.frame = frame;
		this.table = table;
		setIcon(iconPathName);
		this.setFocusPainted(false);
		this.addActionListener(this);
	}

	private void setIcon(String iconPathName) {
		Image changeMemberDataImage = new ImageIcon(getClass().getClassLoader().getResource("images/icons/" + iconPathName)).getImage();
		Image scaledChangeMemberDataImg = changeMemberDataImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon changeMemberDataIcon = new ImageIcon(scaledChangeMemberDataImg);
		this.setIcon(changeMemberDataIcon);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTable getTable() {
		return table;
	}
}
