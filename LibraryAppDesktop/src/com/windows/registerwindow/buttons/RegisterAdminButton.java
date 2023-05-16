package com.windows.registerwindow.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.database_manager.EntityDatabaseDataTransfer;
import com.entities.Admin;
import com.safety.password.PasswordHasher;
import com.validators.FilledUserDataValidator;
import com.windows.loginwindow.LoginWindow;

@SuppressWarnings("serial")
public class RegisterAdminButton extends JButton implements ActionListener {

	private JTextField username;
	private JTextField email;
	private JPasswordField password;
	private JPasswordField confirmPassword;
	private JTextArea errorMessage;
	private JFrame mainFrame;

	public RegisterAdminButton(String buttonName, JTextField username, JTextField email, JPasswordField password,
			JPasswordField confirmPass, JTextArea errorMessage, JFrame mainFrame) {
		super(buttonName);
		this.addActionListener(this);
		this.setFocusable(false);
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPass;
		this.errorMessage = errorMessage;
		this.mainFrame = mainFrame;
	}

// registering process
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");

		if (validateFilledData()) {

			if (registerAdminToDatabase()) {
				JOptionPane.showMessageDialog(null, res.getString("admin_reg_successful"));
				new LoginWindow();
				mainFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, res.getString("reg_error"));

			}
		}
	}

	// gives back a boolean value if everything went fine
	private boolean registerAdminToDatabase() {
		Admin admin = new Admin(username.getText(), email.getText());
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();
		String encryptedPassword = PasswordHasher.encryptPassword(password.getPassword());
		transfer.registerEntity(encryptedPassword, admin);

		return true;
	}

	/*
	 * Returns true if every filled field is correct, else false and the validator
	 * class changes the error message.
	 */
	private boolean validateFilledData() {
		FilledUserDataValidator validator = new FilledUserDataValidator();
		return validator.checkForErrorsInFilledData(username, email, password, confirmPassword, errorMessage);

	}

}
