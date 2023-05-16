package com.windows.users_window;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * This button is implemented in User and Book Window. The classes role is to
 * set the "mother" frame visible again and dispose the actual, this means we go
 * back to previous window.
 */
@SuppressWarnings("serial")
public class BackButton extends JButton implements ActionListener {

	JFrame mainFrame;
	JFrame actualFrame;

	public BackButton(JFrame mainFrame, JFrame actualFrame) {
		super("Back");
		this.mainFrame = mainFrame;
		this.actualFrame = actualFrame;
		this.setFocusPainted(false);
		setIcon();
		this.addActionListener(this);
	}

	private void setIcon() {
		Image backImage = new ImageIcon(getClass().getClassLoader().getResource("images/icons/backIcon.png")).getImage();
		Image scaledBackImg = backImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon backIcon = new ImageIcon(scaledBackImg);
		this.setIcon(backIcon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.setVisible(true);
		actualFrame.dispose();

	}

}
