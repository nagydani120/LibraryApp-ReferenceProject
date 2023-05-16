package com.abstraction;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;

@SuppressWarnings("serial")
public abstract class AbstractFindButton extends JButton implements ActionListener {
	protected JTable table;
/**
 * The class is reused in UserJTable and BookJTable, the icon is basically set to search icon.
 * The extended class must realize the action listener, that makes the functions base.  
 * 
 * @param buttonName -> the buttons name 
 * @param table -> the table where is used the find function
 * 
 */
	public AbstractFindButton(String buttonName, JTable table) {
		super(buttonName);
		this.table = table;
		this.setFocusPainted(false);
		setIcon();
		this.addActionListener(this);
	}

	private void setIcon() {
		Image findImage = new ImageIcon(getClass().getClassLoader().getResource("images/icons/searchIcon.png")).getImage();
		Image scaledFindImg = findImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon findIcon = new ImageIcon(scaledFindImg);
		this.setIcon(findIcon);
	}

	
}
