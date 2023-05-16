package com.send_email;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	private static final ResourceBundle RES = ResourceBundle.getBundle("email_sender");

	private static final String EMAIL_FROM;
	private static final String HOST;
	private static final Properties PROP = System.getProperties();
	// initializing the properties from the resource file
	static {

		EMAIL_FROM = RES.getString("email_from");
		HOST = RES.getString("email_host");

		PROP.put("mail.smtp.host", HOST);
		PROP.put("mail.smtp.port", RES.getString("email_port"));
		PROP.put("mail.smtp.starttls.enable", RES.getString("starttls"));
		PROP.put("mail.smtp.auth", RES.getString("auth"));
	}

	/**
	 * Generates and sends a new password
	 * 
	 * @param emailAddress ->users email address where the email is gonna be sent
	 * @param pass         -> users pass
	 * @return -> true if successful
	 */
	public boolean sendNewPassEmail(String emailAddress, char[] pass) {
		String subject = RES.getString("new_pass_subject");
		String text = RES.getString("new_pass_text");
		return sendEmail(emailAddress, null, pass, subject, text);
	}

	/**
	 * Generates and sends a password in welcome message
	 * 
	 * @param name         -> Users name (First name + last name)
	 * @param emailAddress ->users email address where the email is gonna be sent
	 * @param pass         -> users pass
	 * @return -> true if successful
	 */
	public boolean sendRegisterEmail(String emailAddress, String name, char[] pass) {
		String subject = RES.getString("welcome_email_subject");
		String text = RES.getString("reg_email_text");
		return sendEmail(emailAddress, name, pass, subject, text);
	}

	/*
	 * Sending email with the randomly generated password to registered member,
	 * returns true if everything went well. This method is reused in this class.
	 */
	private boolean sendEmail(String emailAdress, String name, char[] pass, String subjectMsgResource,
			String textMsgResource) {

		Authenticator ant = new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_FROM, "testingIT");
			}
		};
		Session sess = Session.getInstance(PROP, ant);

		MimeMessage msg = new MimeMessage(sess);
		try {

			msg.setFrom(new InternetAddress(EMAIL_FROM));
			// set address to :
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAdress));
			msg.setSubject(subjectMsgResource);
			// if name == null then thewelcome name text doasn't show
			msg.setText(name == null ? "" : ("Welcome " + name + "!<br>") + textMsgResource + String.valueOf(pass),
					"utf-16", "html");

			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
