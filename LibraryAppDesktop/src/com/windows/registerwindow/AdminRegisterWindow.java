package com.windows.registerwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.windows.registerwindow.buttons.RegisterAdminButton;

/**
 * 
 * This class is used to register the libraries administrator. This window shows
 * up if it is a first time running (if there is no one registered in MySQL admin's
 * database)
 */
public class AdminRegisterWindow {

	private ResourceBundle resources = ResourceBundle.getBundle("messages");
	private JFrame frame;
	private JTextField txtUsername;
	private JTextField usernameField;
	private JTextField txtPassword;
	private JPasswordField passwordField;
	private JTextField txtConfirmPassword;
	private JPasswordField passwordConfirmField;
	private JTextField txtLibraryAdminRegistration;
	private JTextArea errorField;
	private JTextField txtEmail;
	private JTextField emailField;

	/**
	 * Create the application.
	 */
	public AdminRegisterWindow() {
		initialize();
		JOptionPane.showMessageDialog(null, resources.getString("first_time_run_message"));
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Border zeroBorder = BorderFactory.createEmptyBorder();

		frame = new JFrame();
		frame.setBounds(100, 100, 378, 284);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane()
				.setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(205, 171, 143));

		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(15);

		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(205, 171, 143));
		txtUsername.setText("Username:");
		txtUsername.setColumns(11);
		txtUsername.setBorder(zeroBorder);

		usernameField = new JTextField();
		usernameField.setColumns(16);

		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(205, 171, 143));
		txtEmail.setText("Email adress:");
		txtEmail.setColumns(11);
		txtEmail.setBorder(zeroBorder);

		emailField = new JTextField("@");
		emailField.setColumns(16);

		txtPassword = new JTextField();
		txtPassword.setBackground(new Color(205, 171, 143));
		txtPassword.setText("Password:");
		txtPassword.setColumns(11);
		txtPassword.setBorder(zeroBorder);

		passwordField = new JPasswordField();
		passwordField.setColumns(16);

		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setBackground(new Color(205, 171, 143));
		txtConfirmPassword.setText("Confirm password:");

		txtConfirmPassword.setColumns(11);
		txtConfirmPassword.setBorder(zeroBorder);

		passwordConfirmField = new JPasswordField();
		passwordConfirmField.setColumns(16);

		errorField = new JTextArea();
		errorField.setWrapStyleWord(true);
		errorField.setRows(2);
		errorField.setLineWrap(true);
		errorField.setBackground(new Color(205, 171, 143));
		errorField.setForeground(new Color(224, 27, 36));
		errorField.setBorder(zeroBorder);
		errorField.setColumns(30);

		txtLibraryAdminRegistration = new JTextField();
		txtLibraryAdminRegistration.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		txtLibraryAdminRegistration.setBackground(new Color(181, 131, 90));
		txtLibraryAdminRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		txtLibraryAdminRegistration.setText(resources.getString("admin_reg_welcome_msg"));
		txtLibraryAdminRegistration.setEditable(false);
		txtLibraryAdminRegistration.setEditable(false);
		txtLibraryAdminRegistration.setFocusable(false);
		txtLibraryAdminRegistration.setBorder(BorderFactory.createLineBorder(new Color(181, 131, 90), 8, true));

		txtUsername.setEditable(false);
		txtUsername.setFocusable(false);
		txtConfirmPassword.setEditable(false);
		txtConfirmPassword.setFocusable(false);
		txtPassword.setEditable(false);
		txtPassword.setFocusable(false);
		txtEmail.setEditable(false);
		txtEmail.setFocusable(false);
		errorField.setFocusable(false);
		errorField.setEditable(false);

		panel.add(txtUsername);
		panel.add(usernameField);
		panel.add(txtEmail);
		panel.add(emailField);
		panel.add(txtPassword);
		panel.add(passwordField);
		panel.add(txtConfirmPassword);
		panel.add(passwordConfirmField);
		panel.add(errorField);

		RegisterAdminButton btnRegister = new RegisterAdminButton("Register", usernameField, emailField, passwordField,
				passwordConfirmField, errorField, frame);

		frame.getContentPane()
				.add(btnRegister, BorderLayout.SOUTH);
		frame.getContentPane()
				.add(txtLibraryAdminRegistration, BorderLayout.NORTH);
		frame.getContentPane()
				.add(panel, BorderLayout.CENTER);

	}

}
