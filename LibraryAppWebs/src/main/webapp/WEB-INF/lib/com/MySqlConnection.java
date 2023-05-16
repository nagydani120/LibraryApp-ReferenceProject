package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySqlConnection {

	/**
	 * This class is used to create the connectivity between the database and java
	 * 
	 * All the parameters is red from dbconnectivity.properties file using
	 * ResourceBundle class
	 */

	private static final String USERNAME;
	private static final String PASSWORD;
	private static final String URL;
	private static final String DRIVER;

	private Connection conn;

	static {

		ResourceBundle res = ResourceBundle.getBundle("com/dbconnectivity");
		USERNAME = res.getString("username");
		PASSWORD = res.getString("password");
		URL = res.getString("URL");
		DRIVER = res.getString("driver");

	}
//
//	public static void main(String[] args) {
//		MySqlConnection conn = new MySqlConnection();
//
//	}

	// creates and initialize the Connection object
	public MySqlConnection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return conn;
	}

	/**
	 * This method is a little help to programmer in using PreparedStatement for
	 * MySQL data transfer. example for PreparedStatement: "INSERT INTO xy.xy
	 * (column_name1,column_name2) VALUES (?,?)" There we can use this method to
	 * represent the VALUES part.
	 * 
	 * @param valueCount -> the number of "?" characters between parenthesis
	 * @return a prepared String for the PreparedStatements command for example
	 *         valueCount = 3 then the returned String is "VALUES (?,?,?)" (gives
	 *         back exactly as much question mark as valueCount's value is)
	 */
	public static String getValuesString(int valueCount) {
		StringBuilder sb = new StringBuilder();
		sb.append("VALUES (");
		sb.append("?,".repeat(valueCount));
		// removing the last "," after the question mark
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}

}
