<%@page import="com.database.BookDatabaseDataTransfer"%>
<%@page import="com.database.EntityDatabaseDataTransfer"%>
<%@page import="com.entities.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Period"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../styles/loggedInStyle.css">
<script type="text/javascript" src="../scripts/loggedIn.js"></script>
<title>Logged in</title>
</head>
<body>

	<%
	String email =(String) request.getSession().getAttribute("email_address");

			com.database.EntityDatabaseDataTransfer personTrans = new com.database.EntityDatabaseDataTransfer();

			Person p = personTrans.getPerson(email);

			Integer personId = p.getId();
			String firstName = p.getFirstName();
			String lastName = p.getLastName();

			BookDatabaseDataTransfer bookTrans = new BookDatabaseDataTransfer();
			ArrayList<Book> books = bookTrans.getPersonsBook(personId);
			Iterator<Book> iterator = books.iterator();
	%>
	<br>

	<div class="mainDiv">
		<br>

		<h1>
			Welcome
			<%=firstName%>
			<%=lastName%>! <br>
		</h1>
		<h2>
			ID(<%=personId%>)
		</h2>

		<a href="javascript:logout()">Log out</a>

		<div class="booksDiv">
			<h3>
				You have
				<%=books.size()%>
				books borrowed now.
			</h3>
			<br>
			<table>
				<tr class=head>
					<%out.print("<th>");%>
					<%out.print("Title");%>

					<%out.print("<th>");%>
					<%out.print("Author");%>

					<%out.print("<th>");%>
					<%out.print("Year");%>

					<%out.print("<th>");%>
					<%out.print("ISBN");%>

					<%out.print("<th>");%>
					<%out.print("Borrowed");%>

					<%out.print("<th>");%>
					<%out.print("Return deadline");%>

					<%out.print("<th>");%>
					<%out.print("Extend deadline");%>
				</tr>



				<%
				while (iterator.hasNext()) {
					int i = 0;
					Book b = iterator.next();
					Integer bookId = b.getBookId();
					String title = b.getBookTitle();
					String author = b.getBookAuthor();
					Integer year = b.getBookYearOfPublication();
					LocalDate borrowingDay = b.getBorrowingDay();
					LocalDate expectedReturnDay = b.getExpectedReturnDay();
					String isbn = b.getBookISBN();

					Period period = borrowingDay.until(expectedReturnDay);

					boolean isDeadlineOverMonth = period.getDays() >= 30 || period.getMonths() == 1;
					i++;
				%>
				<%out.print("<tr><th>");%>
				<%=title%>

				<%out.print("<th>");%>
				<%=author%>

				<%out.print("<th>");%>
				<%=year%>

				<%out.print("<th>");%>
				<%=isbn%>

				<%out.print("<th>");%>
				<%=borrowingDay%>

				<%out.print("<th>");%>
				<%=expectedReturnDay%>

				<%out.print("<th>");%>
				<%
				out.print("<button " + (isDeadlineOverMonth ? "disabled='disabled'" : "") + " onclick='send(" + personId + "," + bookId
						+ ")'>Extend*</button>");
				%>
				<%out.print("<tr>");%>


				<%
				}
				%>
			</table>


			<p class="comment">
				*The book basically is borrowed for 20 days, with extending you can
				get plus extra 10 days! Every book's deadline can be extended <b>just
					one</b> time.
			</p>
			<br>
		</div>
	</div>
</body>
</html>
