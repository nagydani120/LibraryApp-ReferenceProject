package com.database_manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

/**
 * This class initializes the MySQL database if its not created yet and checks
 * for that the database admin role exists
 * 
 */
public class DatabaseInitializer {

	/**
	 * This method creates the database using the declared commands from
	 * src/main/java/create.sql file
	 */
	public void createDatabase() {
		MySqlConnection connector = new MySqlConnection();
		Connection connection = connector.getConnection();

		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/create.sql"))) {
			Statement statement = connection.createStatement();

			Stream<String> lines = reader.lines();

			lines.forEach(l -> {
				try {
					statement.execute(l);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * If there is no one registered in MySQL admin's database returns false, else
	 * true. The try with resources handles the .close(), with this the resource
	 * leak is avoided
	 */
	public boolean isAdminRoleAssigned() {
		MySqlConnection connector = new MySqlConnection();

		try (Connection connection = connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT id FROM libapp.libadmin");
			ResultSet result = statement.executeQuery();

			// returns true if at least one administrator already exists in database
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Checks that 'libapp' database exists or not. return -> true if exists
	 */
	public static boolean isDatabaseExists() {
		MySqlConnection connector = new MySqlConnection();
		Connection connection = connector.getConnection();

		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SHOW DATABASES LIKE 'libapp'");
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
