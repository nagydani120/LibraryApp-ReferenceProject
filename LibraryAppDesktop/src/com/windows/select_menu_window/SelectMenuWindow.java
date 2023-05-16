package com.windows.select_menu_window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.windows.books_window.BooksWindow;
import com.windows.users_window.UsersMainWindow;

/**
 * 
 * This window is where the library applications user can choose between User or
 * Book window. The action listener creates the window which was selected.
 */
public class SelectMenuWindow implements ActionListener {

	private JFrame frame;
	private JButton btnMembers;
	private JButton btnBooks;
	private JLabel lblSelectMenu;
	private JPanel buttonPanel;

	/**
	 * Create the application.
	 */
	public SelectMenuWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane()
				.setBackground(new Color(205, 171, 143));
		frame.setBounds(100, 100, 330, 123);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(500, 300);
		frame.getContentPane()
				.setLayout(new BorderLayout(0, 0));

		lblSelectMenu = new JLabel("Select Menu:");
		lblSelectMenu.setFont(new Font("Liberation Sans", Font.BOLD, 15));
		lblSelectMenu.setHorizontalAlignment(SwingConstants.CENTER);

		frame.getContentPane()
				.add(lblSelectMenu, BorderLayout.NORTH);

		buttonPanel = new JPanel();
		frame.getContentPane()
				.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(1, 0, 5, 0));

		btnMembers = new JButton("Members");
		btnBooks = new JButton("Books");

		btnMembers.addActionListener(this);
		btnBooks.addActionListener(this);

		btnBooks.setBackground(new Color(152, 106, 68));
		btnMembers.setBackground(new Color(152, 106, 68));
		btnMembers.setFocusable(false);
		btnBooks.setFocusable(false);

		setImageIcons();

		buttonPanel.add(btnMembers);
		buttonPanel.add(btnBooks);

		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMembers) {
			new UsersMainWindow(frame);
		} else if (e.getSource() == btnBooks) {
			new BooksWindow(frame);
		}

	}

	// setting the icons
	private void setImageIcons() {
		Image bookImage = new ImageIcon(getClass().getClassLoader().getResource("images/icons/bookIcon.png")).getImage();
		Image scaledBookImg = bookImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon bookIcon = new ImageIcon(scaledBookImg);
		btnBooks.setIcon(bookIcon);

		Image memberImage = new ImageIcon(getClass().getClassLoader().getResource("images/icons/memberIcon.png")).getImage();
		Image scaledMemberImg = memberImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon memberIcon = new ImageIcon(scaledMemberImg);
		btnMembers.setIcon(memberIcon);

	}

}
