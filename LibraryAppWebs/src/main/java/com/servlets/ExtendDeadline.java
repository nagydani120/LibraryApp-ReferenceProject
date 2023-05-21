package com.servlets;

import java.io.IOException;

import com.database.BookDatabaseDataTransfer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExtendDeadline. The class extends the books
 * deadline (which has been clicked) by (now its a fixed value) 10 day.
 * 
 * 
 */
public class ExtendDeadline extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hmmm???");
		Integer bookId = Integer.valueOf(request.getParameter("bookId"));
		Integer personId = Integer.valueOf(request.getParameter("personId"));

		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
		transfer.extendDeadline(personId, bookId, 10);

	}

}
