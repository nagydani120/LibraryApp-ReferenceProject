# ReferenceProjects
Reference projects made by me
			        			 Library App 1.0
 
 			The applications characterization:
 # DEMO VIDEO AND DEMO ACCOUNTS
 https://vimeo.com/829100232?share=copy

There is a pre-registered account for LibraryApp(Desktop login)

**Username: administrator <br>
Password: Administrator1**

And pre-registered account for login on website:

**Email: nagydani120@gmail.com <br>
Password:ot88i@m+nu** 

*Note: These demo accounts works only if application is runned with prepared docker compose, 
       that initializes the MySQL and Apache Tomcat server. 
       
       Instructions: docker-compose folder -> readme file* 
		
 # DESKTOP:
 The desktop application is created for managing (registering, deleting) the users, books
 and handle the borrows.If the Library application runs for the first time, the Administrator Register window
 appears, the database and tables are created in MySQL, and if there is a successful registration
 the login panel appears. From now when we start the application just the login panel appears.
 (I didn't realize to have multiple administrators, I see no reason yet).
 If we log in, the select menu appears (Books or Users).
 In these two menus the library worker can handle the members, books, borrows, returns, etc... 	 
 
 # WEB SITE:
 If the library worker registers a member, the member gets a randomly generated password 
 via email. With this password and with the specified email address can the member login.
 On web site the member can check the actually borrowed books and deadlines, there is a possibility to
 extend the deadline to 10 days for each book, but just for once. (in the future I can develop more functions, to 
 limit the books deadline renewal (for example 3 renewal/ month for any book... or there should be a charge ..etc)
 On web site the member can use the ForgotPassword function to generate and send a new password to an  email address
 
 # REAL-LIFE EXAMPLE:
 The person comes to the library, the library worker fills in the persons data,
 registers and a pass is sent to the email address that just the member can know. 
 (there is no function to change to a custom password yet)
 The member chooses one or more books, and the library worker realizes the book borrow via the application.
 The member goes home and uses the website to check for a deadline or extend the deadline. 
 If the member comes back with the book, the library worker registers the return. 
 
 # ABOUT DEVELOPING
 The application is created by me as a reference project and for
 practicing Java and English. I used first time some functions, so
 there was a lot of help and guidance from web (ChatGPT,
 stackoverflow.com, random sites...), but I tried to not copy the
 codes, I want to understand what I write.
 
 I met with some APIs, functions, and new languages for the first time, it
 was a little bit hard but I enjoyed developing.
  
 I used: 	
 	-> Maven for project building using dependencies, pom.xml
          
	-> Java Swing for the Desktop software, (I'm not familiar with JavaFX yet), 
     		the functions are done by ActionListeners (buttons, tables..) 
          
       	-> Data structures like ArrayList, TreeMap, arrays
          
       	-> DateTime, Resources(for messages, initializing the connection,images...),
       		POJO objects, regular expressions, File reading
          
        -> Mail API for sending e-mail
          
        -> BCrypt for hashing the password with salt for security
          
        -> Stream API for searching, collecting data, and filling tables 
          
        -> MySQL database: joining tables, searching, updating, inserting, deleting 
          
        -> JDBC for connection to MySQL database
          			
        -> Servlets for communication with the website, AJAX to send and receive on web site 	
	 
 	-> Apache Tomcat server
 	
	-> little HTML, CSS, Javascript, and JSP for creating the website
 
 	-> little bit of JUnit for testing 
 
I tried to document and comment on what I did in classes and sometimes I used 
version control system (Github) for practicing.
 					
Total hours spent with this app: 80+hours 
(I measured time with Learning Timer, which is an app made by me) 


# DOCKER INITIALIZE
The docker-compose folder is created for initializing the Apache Tomcat and the MySQL server (there are some pre-filled data, the server is initialized by a mysql dump file).

To run these servers just navigate to this folder and run via terminal "docker compose up" and wait till it gets initialized.
If the MySQL welcome text appears, the Desktop application (LibraryAppDesktop.jar) can be run.
I tested this in Windows and Ubuntu too, worked for me.
