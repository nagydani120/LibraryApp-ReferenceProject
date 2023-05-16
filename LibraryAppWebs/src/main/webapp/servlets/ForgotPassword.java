package servlets;
import java.io.IOException;

import com.EntityDatabaseDataTransfer;
import com.validators.FilledUserDataValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @formatter:off
/**
 * Servlet implementation class ForgotPassword.
 * 
 * The servlet gets a parameter (emailAddress) what is validated here (checks
 * that exists the email in DB,and is it a valid email) and makes a response:
 * 
 * "invalid" -> if the email address is not valid 
 * "false" 	 -> if email address is not present in DB 
 * "true"	-> if everything is valid and a new password has been set to user
 */
//@formatter:on

public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parameter = request.getParameter("emailAddress");

		response.setContentType("text/plain");

		FilledUserDataValidator validator = new FilledUserDataValidator();

		/*
		 * If the email is not valid (using regex pattern) then a message is sent to
		 * user
		 */
		boolean emailValid = validator.isEmailValid(parameter);
		if (!emailValid) {
			response.getWriter()
					.write("invalid");
			return;
		}

		/*
		 * I reused the email validator from the validator class, if the email is free
		 * than i return a message with error that this email is not existing
		 */
		boolean emailFree = validator.isEmailFree(parameter);
		if (emailFree) {
			response.getWriter()
					.write("false");
			return;
		}
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();
		transfer.setNewGeneratedPasswordForUser(parameter);
		response.getWriter()
				.write("true");
	}

}
