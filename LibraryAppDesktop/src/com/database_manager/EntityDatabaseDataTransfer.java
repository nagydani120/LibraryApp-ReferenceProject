package com.database_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.entities.Admin;
import com.entities.Book;
import com.entities.Person;
import com.safety.password.PasswordGenerator;
import com.safety.password.PasswordHasher;

/**
 * This class is created for registering,updating,getting the entity (Admin or
 * User) to/from database, and to avoid duplicate code There is 2 overloaded
 * methods, both calls the private method to send the data to database.
 */

public class EntityDatabaseDataTransfer {
	/**
	 * Puts all the data from database to a map,(ID - Person key/value pair)
	 * 
	 * @return -> a Map contains all members, empty map if no member found
	 */
	public Map<Integer, Person> getAllMember() {
		MySqlConnection connector = new MySqlConnection();

		Map<Integer, Person> members = new TreeMap<>();

		try (Connection connection = connector.getConnection()) {
			ResultSet result = connection.createStatement()
					.executeQuery("SELECT * FROM libapp.libuser");

			while (result.next()) {

				Integer personId = result.getInt("id");

				members.put(personId, getDataFromResult(result));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	/**
	 * 
	 * @param encryptedPassword -> the encrypted password (there is a separate class
	 *                          created under PasswordHasher name to encrypt the
	 *                          password)
	 * @param person            -> person to register
	 * @return -> true if successful
	 */
	public boolean registerEntity(String encryptedPassword, Person person) {

		return registerToDatabase("libuser (first_name,last_name,date_of_birth,gender,address,phone,email,password)",
				encryptedPassword, person.getFirstName(), person.getLastName(), person.getDateOfBirth()
						.toString(),
				person.getGender(), person.getAddress(), person.getPhoneNumber(), person.getEmailAddress());
	}

	/**
	 * 
	 * @param enrcyptedPassword-> the encrypted password (there is a separate class
	 *                            created under PasswordHasher name to encrypt the
	 *                            password)
	 * @param admin               -> admin to register
	 * @return -> true if successful
	 */
	public boolean registerEntity(String enrcyptedPassword, Admin admin) {
		return registerToDatabase("libadmin (username,email,password)", enrcyptedPassword, admin.getUsername(),
				admin.getEmail());
	}

	private boolean registerToDatabase(String columns, String password, String... entityFields) {
		MySqlConnection connector = new MySqlConnection();
		int numberOfFields = entityFields.length + 1; // the +1 is the password field

		// Try with resources cares about closing the resource
		try (Connection connection = connector.getConnection()) {

			// Passing all the values to the database (MUST BE IN ORDER) when this
			// method is called, the String columns
			// defines the table name + the columns between parenthesis, the for loop adds
			// the Strings as value, and finally the MySQLConnection.getValuesString() puts
			// exactly as many
			// fields as required

			String valuesString = MySqlConnection.getValuesString(numberOfFields);
			PreparedStatement statement = connection.prepareStatement("INSERT INTO libapp." + columns + valuesString);
			// setting the columns value with a for cycle
			for (int i = 1; i <= entityFields.length; i++) {
				statement.setString(i, entityFields[i - 1]);
			}

			// Passing the password as last item.
			statement.setString(entityFields.length + 1, password);

			// Executing the statement
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param emailAddress -> the users registered email address what need a new
	 *                     password
	 * @return -> true if the password successfully generated and sent via email
	 */
	public boolean setNewGeneratedPasswordForUser(String emailAddress) {

		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			char[] generatedPass = PasswordGenerator.generatePassword(2, 2, 6);
			String encryptedPassword = PasswordHasher.encryptPassword(generatedPass);

			System.out.println(String.valueOf(generatedPass));
			PreparedStatement statement = conn.prepareStatement("UPDATE libapp.libuser SET password ='"
					+ encryptedPassword + "' WHERE email= '" + emailAddress + "'");
			statement.execute();

			// Commented out, in final test i test this function too!!!!!!!!!
//			EmailSender email = new EmailSender();
//			email.sendNewPassEmail(emailAddress, generatedPass);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * With delete the member is just logically deleted, the 'active' column is set
	 * to false, the member wont show up in table of members... this is for storing
	 * the data for example statistics or maybe i create a function that makes
	 * possible to re-activate the member
	 */

	/**
	 * This method logically deletes the person setting the "active" columns value
	 * to false. Its unable to delete a person who has borrowed books now.
	 * 
	 * 
	 * @param personId -> the persons id
	 * @return -> true if successfully deleted
	 * @throws UnableToDeleteException -> if the person has borrowed books now
	 */
	public boolean deletePerson(Integer personId) throws UnableToDeleteException {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
			ArrayList<Book> personsBook = transfer.getPersonsBook(personId);
			if (personsBook.size() > 0) {
				throw new UnableToDeleteException();
			}

			PreparedStatement statement = conn
					.prepareStatement("UPDATE libapp.libuser SET active = FALSE WHERE id = " + personId);
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Gets the person from the database with the matching ID
	 * 
	 * @param id -> the Persons ID
	 * @return the Person object or null if there is no Person under this ID or
	 *         something wrong with the connection.
	 */
	public Person getPerson(Integer id) {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM libapp.libuser WHERE id = " + id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {

				Integer personId = result.getInt("id");

				Person person = getDataFromResult(result);
				person.setId(personId);

				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Gets the person from the database with the matching email address
	 * 
	 * @param emailAddress -> the Persons email address
	 * @return the Person object or null if there is no Person under this email
	 *         address or something wrong with the connection.
	 */
	public Person getPerson(String emailAddress) {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM libapp.libuser WHERE email = '" + emailAddress + "'");
			ResultSet result = statement.executeQuery();
			if (result.next()) {

				Person person = getDataFromResult(result);
				person.setId(result.getInt("id"));
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param p -> Person to update. The person from database is chose based on
	 *          Persons id and updated all fields (if some field is changed via
	 *          setters)
	 * @return true if updated successfully
	 */
	public boolean updatePerson(Person p) {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			// @formatter:off
			PreparedStatement statement = conn.prepareStatement("UPDATE libapp.libuser SET "
					+ "first_name = '" 	+ 	p.getFirstName() 
					+ "',last_name = '"	+ 	p.getLastName()
					+ "',date_of_birth = '"+ p.getDateOfBirth() 
					+ "',gender = '"	+	p.getGender()
					+ "',address = '" 	+ 	p.getAddress()
					+ "',phone = '" 	+ 	p.getPhoneNumber()
					+ "',email = '" 	+	p.getEmailAddress()
					+  "' WHERE id = "	+	p.getId());
			// @formatter:on
			statement.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * The parameter and return is the same as in the UserJTable's setSelectedUser,
	 * both returns boolean, the differ is that this method checks for match in
	 * database, while the UserJTable's method sets the matching users row as
	 * selected
	 * 
	 * @param userToFind -> the users email address or ID
	 * @return the matching active(!) users ID from database or null if there is no match
	 */
	public Integer checkForMatch(String userToFind) {
		if (userToFind == null || userToFind.length() < 1) {
			return null;
		}

		Map<Integer, Person> allMember = getAllMember();

		// if the input is just number returns the input ID if the Map got from database
		// contains the ID else the input is processed as email
		if (userToFind.matches("[0-9]+")) {

			Integer inputID = Integer.valueOf(userToFind);
			if (allMember.containsKey(inputID)) {
				return inputID;
			}
			// else it is processed as email address
		} else {
			Optional<Integer> foundID = allMember.entrySet()
					.stream()
					.filter(p -> p.getValue()
							.getEmailAddress()
							.equals(userToFind))
					.map(p -> p.getKey())
					.findFirst();

			return foundID.isPresent() ? foundID.get() : null;
		}
		return null;
	}

	/*
	 * These methods above checks that password matches with the Database's saved
	 * password under that name using BCrypt validator.
	 * 
	 * (Its reused. The method does the admin's and the members validating.)
	 * 
	 * If i'm right, i can store the hashed password what i got from DB in String, i
	 * just have to not store the original password because of security issues
	 * (String pool, immutability...)
	 */

	/**
	 * This method is used for login the member in the web. Don't mash up with the
	 * isAdminPasswordMatches, that method uses this verification kind of
	 * verification too, just for the admin's to log in in desktop app!
	 * 
	 * @param usernameField     -> the administator's email address
	 * @param passwordFromField -> the administrator's password
	 * @return true if the login is successful (password and username matching)
	 */

	public boolean isMemberPasswordMatches(String emailAddress, char[] password) {
		return isPasswordMatches(emailAddress, password, "libuser WHERE email = ");
	}

	/**
	 * This method is used for login the administrator in the desktop application.
	 * Don't mash up with the isMemberPasswordMatches, that method uses this
	 * verification kind of verification too, just for the members to log in in web!
	 * 
	 * @param usernameField     -> the administator's username
	 * @param passwordFromField -> the administrator's password
	 * @return true if the login is successful (pass and username matching)
	 */
	public boolean isAdminPasswordMatches(String usernameField, char[] passwordFromField) {
		return isPasswordMatches(usernameField, passwordFromField, "libadmin WHERE username = ");
	}

	public boolean isUsernameExistsInDB(String username) {
		return isFieldExistsInDB(username, "SELECT username FROM libapp.libadmin WHERE username = '");
	}

	public boolean isEmailExistsInDB(String emailAddress) {
		return isFieldExistsInDB(emailAddress, "SELECT email FROM libapp.libuser WHERE email = '");
	}

	/**
	 * The method is just created to avoid duplicate code, just the String changes
	 * so i reused the method
	 * 
	 * @param fieldToFind   -> the value what is searched in database
	 * @param dbCommandPart -> the SELECT MySQL statement in String format
	 *                      represents place where to look for the value and the
	 *                      column name example : SELECT field FROM database.table
	 *                      WHERE field = '
	 * 
	 * @return true if the field exists
	 */
	private boolean isFieldExistsInDB(String fieldToFind, String dbCommandPart) {
		MySqlConnection connector = new MySqlConnection();
		try (Connection conn = connector.getConnection()) {

			PreparedStatement stmuser = conn.prepareStatement(dbCommandPart + fieldToFind + "'");
			ResultSet usernameResult = stmuser.executeQuery();

			return usernameResult.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Processes the Result set, the output is a Person object
	 *
	 */
	private Person getDataFromResult(ResultSet gotData) throws SQLException {

		String firstName = gotData.getString("first_name");
		String lastName = gotData.getString("last_name");
		LocalDate dateOfBirth = gotData.getDate("date_of_birth")
				.toLocalDate();
		String gender = gotData.getString("gender");
		String address = gotData.getString("address");
		String phone = gotData.getString("phone");
		String email = gotData.getString("email");
		boolean active = gotData.getBoolean("active");
		return new Person(firstName, lastName, dateOfBirth, gender, address, phone, email, active);
	}

	/**
	 * Validates the password, the method is reused in admin and person validating
	 * too
	 */
	private boolean isPasswordMatches(String username, char[] password, String dbCommandPart) {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {
			PreparedStatement stmpass = conn
					.prepareStatement("SELECT password FROM libapp." + dbCommandPart + " '" + username + "'");
			ResultSet passResult = stmpass.executeQuery();

			if (passResult.next()) {

				char[] pass = passResult.getString(1)
						.toCharArray();
				return PasswordHasher.verifyPassword(password, pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
