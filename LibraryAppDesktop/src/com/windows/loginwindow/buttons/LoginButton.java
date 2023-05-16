package com.windows.loginwindow.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.database_manager.EntityDatabaseDataTransfer;
import com.windows.select_menu_window.SelectMenuWindow;

/*
 * This class handles the Login buttons function, the plan is to get the login data from SQLDatabase... 
 * */
@SuppressWarnings("serial")
public class LoginButton extends JButton implements ActionListener {

	private ResourceBundle res = ResourceBundle.getBundle("messages");
	private JPasswordField passField;
	private JFormattedTextField usernameField;
	private JTextField errorMessage;
	private  JFrame mainFrame;
	
	public LoginButton(String name, JFormattedTextField username, JPasswordField pass, JTextField errorMessage,JFrame mainFrame) {
		super(name);
		this.usernameField = username;
		this.passField = pass;
		this.errorMessage = errorMessage;
		this.addActionListener(this);
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (validateLogin()) {
			new SelectMenuWindow();
			mainFrame.dispose();
		}
	}

	private boolean validateLogin() {

		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();

		if (transfer.isUsernameExistsInDB(usernameField.getText())) {
			if (transfer.isAdminPasswordMatches(usernameField.getText(), passField.getPassword())) {
				return true;
			} else {
				errorMessage.setText(res.getString("err_invalid_pass"));
			}
		} else {
			errorMessage.setText(res.getString("err_username_not_exists"));
		}
		return false;
	}

	//

}
