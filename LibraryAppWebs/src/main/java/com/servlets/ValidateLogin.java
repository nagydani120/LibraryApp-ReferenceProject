package com.servlets;
import java.io.IOException;

import com.database.EntityDatabaseDataTransfer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateLogin
 */

//@WebServlet("/validateLogin")
public class ValidateLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("pages/loggedIn.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();

		String email = request.getParameter("email_address");
		char[] pass = request.getParameter("password")
				.toCharArray();
		if (!transfer.isEmailExistsInDB(email)) {
			request.setAttribute("errorMsg", "This email address is not registered!");
		} else if (!transfer.isMemberPasswordMatches(email, pass)) {
			request.setAttribute("errorMsg", "Invalid password!");
		} else {

			request.getSession()
					.setAttribute("email_address", email);
			response.sendRedirect("pages/loggedIn.jsp");

			return;
		}

		// refill the "new" .jsp data with the email what was passed
// (without this after pressing the login button the input fields got cleared)
		request.setAttribute("emailValue", email);
		request.getRequestDispatcher("pages/index.jsp")
				.include(request, response);

	}
//1*wke?ti3q
}
