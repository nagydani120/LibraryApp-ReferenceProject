
package com.windows.loginwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.windows.loginwindow.buttons.LoginButton;
/**
 *Login window and components 
 *
 */
public class LoginWindow {

	private JFrame frame;
	private JTextField txtWelcomeInLibrary;
	private JPasswordField passwordField;
	private JTextField txtErr;
	private ResourceBundle rb = ResourceBundle.getBundle("messages");

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		txtErr = new JTextField();
		passwordField = new JPasswordField();

		JPanel loginPanel = new JPanel();
		JFormattedTextField usernameField = new JFormattedTextField();
		LoginButton btnLogin = new LoginButton("Login", usernameField, passwordField, txtErr,frame);
		JTextArea txtrPassword = new JTextArea();
		JTextArea txtrUsername = new JTextArea();

		frame.setResizable(false);
		frame.getContentPane()
				.setBackground(new Color(181, 131, 90));
		
		txtWelcomeInLibrary = new JTextField();
		txtWelcomeInLibrary.setFont(new Font("Waree", Font.BOLD, 14));
		txtWelcomeInLibrary.setBackground(new Color(205, 171, 143));
		txtWelcomeInLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		txtWelcomeInLibrary.setText(rb.getString("login_welcome_message"));
		txtWelcomeInLibrary.setEditable(false);
		txtWelcomeInLibrary.setColumns(10);

		loginPanel.setBackground(new Color(181, 131, 90));
		loginPanel.setLayout(null);

		usernameField.setBounds(195, 25, 178, 28);

		passwordField.setBounds(196, 60, 178, 28);

		btnLogin.setBounds(154, 105, 117, 25);
		btnLogin.setFocusable(false);

		txtrPassword.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		txtrPassword.setBackground(new Color(181, 131, 90));
		txtrPassword.setEditable(false);
		txtrPassword.setText("Password:");
		txtrPassword.setBounds(67, 65, 117, 28);

		txtrUsername.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		txtrUsername.setText("Username:");
		txtrUsername.setEditable(false);
		txtrUsername.setBackground(new Color(181, 131, 90));
		txtrUsername.setBounds(67, 30, 117, 17);

		txtErr.setForeground(new Color(224, 27, 36));
		txtErr.setEditable(false);
		txtErr.setHorizontalAlignment(SwingConstants.CENTER);
		txtErr.setBackground(new Color(181, 131, 90));
		txtErr.setBorder(BorderFactory.createEmptyBorder());
		txtErr.setBounds(42, 134, 370, 28);
		txtErr.setColumns(20);

		loginPanel.add(txtrPassword);
		loginPanel.add(txtrUsername);
		loginPanel.add(usernameField);
		loginPanel.add(btnLogin);
		loginPanel.add(passwordField);
		loginPanel.add(txtErr);

		frame.getContentPane()
				.add(loginPanel, BorderLayout.CENTER);
		frame.setBounds(100, 100, 450, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane()
				.add(txtWelcomeInLibrary, BorderLayout.NORTH);
		frame.setLocation(500, 300);
		
	}
}
