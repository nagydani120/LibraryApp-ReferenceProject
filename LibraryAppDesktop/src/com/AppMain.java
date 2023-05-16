package com;

import com.database_manager.DatabaseInitializer;
import com.windows.loginwindow.LoginWindow;
import com.windows.registerwindow.AdminRegisterWindow;

// @formatter:off
/**
 * 
 *
 *
 *
 *							        			 Library App 1.0
 *
 *			The applications characterization:
 *			
 *			DESKTOP:
 *			The desktop application is created for managing (registering, deleting) the users, books
 *			and handle the borrows.	If the Library application runs for the first time, the Administrator Register window
 *			appears, the database and tables are created in MySQL and if there is a successful registration
 *			the login panel appears. From now when we start the application just the login panel appears.
 *			 (i didn't realized to have multiple administrators, i see no reason yet).
 *			If we are logged in the select menu appears, (Books or Users).
 *			In these two menu the library worker can handle the members, books, borrows, returns, etc... 	 
 *
 *			WEB SITE:
 *			If the library worker registers a member, the member gets a randomly generated password 
 *			via email, with this password and with specified email address can the member log in.
 *			In web site the member can check for actually borrowed books and deadlines, there is a possibility to
 *			extend the deadline 10 days each book, but just for once. (in future i can develop more functions, to 
 *			limit the books deadline renewal (for example 3 renewal/ month any book... or there should be a charge ..etc)
 *			In web site the member can use the ForgotPassword function to generate and send a new password to email address
 *
 *			REAL LIFE EXAMPLE:
 *  		The person comes to library, the library worker fills the persons data,
 *  		registers and a pass is sent to email address what just the member can know. 
 *  		(there is no function to change to custom password yet)
 *  		 The member chooses one or more books, the library worker realize the book borrow via the application.
 *  		The member goes home and he uses the web site to check for deadline or extend the deadline. 
 *			If the member comes back with the book, the library worker registers the return. 
 *
 *         The application is created by me as reference project and for
 *         practicing Java and English. I used first time some functions, so
 *         there was a lot of help and guide from web (ChatGPT,
 *         stackoverflow.com, random sites...), but i tried to not copy the
 *         codes, i want to understand what i write.
 *
 *         I met with some API's, functions and new languages for first time, it
 *         was a little bit hard but i enjoyed developing.
 * 
 *         I used: 	
 *         			-> Maven for project building using dependencies, pom.xml
 *         
 *         			-> Java Swing for the Desktop software, (i'm not familiar with JavaFX yet), 
 *         				the functions is done by ActionListeners (buttons,tables..) 
 *         
 *         			-> Data structures like ArrayList, TreeMap, arrays
 *         
 *         			-> DateTime, Resources(for messages, initializing the connection,images...),
 *         	  	  		POJO objects, regular expressions, File reading
 *         
 *         			-> Mail API for sending e-mail
 *         
 *         			-> BCrypt for hashing password with salt for security
 *         
 *         			-> Stream API for searching, collecting data and filling tables 
 *         
 *         			-> MySQL database: joining tables, searching, updating, inserting, deleting 
 *         
 *         			-> JDBC for connection to MySQL database
 *         			
 *         			-> Servlets for communication with website, AJAX to send and receive in web side 			
 * 
 *					-> Apache Tomcat server
 *
 *					-> little HTML, CSS, Javascript and JSP for creating the website
 *
 *					-> little bit of JUnit for testing 
 *
*					I tried to document and comment what i did in classes and sometimes i used 
 *					version control system (Github) for practicing.
 *					
 *					Total hours spent with this app: 70+hours 
 *					(i measured time with Learning Timer, that is an app made by me) 
 */
//@formatter:on

public class AppMain {

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		DatabaseInitializer init = new DatabaseInitializer();

		if (!DatabaseInitializer.isDatabaseExists()) {
			init.createDatabase();
		}
		if (init.isAdminRoleAssigned()) {
			new LoginWindow();
		} else {
			new AdminRegisterWindow();
		}
	}

}
