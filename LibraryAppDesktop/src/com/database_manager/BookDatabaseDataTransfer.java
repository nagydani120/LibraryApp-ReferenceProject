package com.database_manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.entities.Book;
import com.entities.Genre;
import com.entities.Person;
import com.validators.FilledBookDataValidator;

/**
 *
 * This class is used to connect the program with the database, including some
 * basic commands like get,"getall",delete,register and some specific.
 * 
 * The class uses the MySqlConnection class to connect and get ResultSet from
 * the MySql database.
 * 
 */
public class BookDatabaseDataTransfer {

	/**
	 * 
	 * @param person -> person who is returning the book
	 * @param book   -> the book what is returned
	 * @return -> true if no exception
	 */

	public boolean returnBook(Person person, Book book) {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			PreparedStatement returnStatement = conn
					.prepareStatement("UPDATE libapp.borrows SET return_date = '" + Date.valueOf(LocalDate.now())
							+ "' WHERE member_id = " + person.getId() + " AND book_id = " + book.getBookId());
			PreparedStatement updateBorrowerId = conn.prepareStatement(
					"UPDATE libapp.books SET actually_borrowed = false WHERE id = " + book.getBookId());

			returnStatement.executeUpdate();
			updateBorrowerId.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 
	 * @param person -> person who is going to borrow the book
	 * @param book   -> the book what is borrowed
	 * @return -> true if no exception
	 */
	public boolean borrowBook(Person person, Book book) {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			PreparedStatement insertStatement = conn
					.prepareStatement("INSERT INTO libapp.borrows (member_id,book_id,borrowing_day,expected_return) "
							+ MySqlConnection.getValuesString(4));
			insertStatement.setInt(1, person.getId());
			insertStatement.setInt(2, book.getBookId());
			insertStatement.setDate(3, Date.valueOf(LocalDate.now()));
			insertStatement.setDate(4, Date.valueOf(LocalDate.now()
					.plusDays(20)));

			PreparedStatement updateStatement = conn.prepareStatement(
					"UPDATE libapp.books SET actually_borrowed = true,times_borrowed = times_borrowed + 1  WHERE id="
							+ book.getBookId());

			insertStatement.executeUpdate();
			updateStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * This method gets all the books from the database
	 *
	 * @return -> returns all of the books what is not deleted, if exception occurs
	 *         returns null
	 */
	public Map<Integer, Book> getAllBook() {
		Map<Integer, Book> books = new TreeMap<>();

		MySqlConnection connector = new MySqlConnection();
		try (Connection conn = connector.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(
					"SELECT * FROM libapp.books LEFT JOIN libapp.borrows ON books.id = borrows.book_id");
			ResultSet result = statement.executeQuery();

			// the deleted books are skipped
			while (result.next()) {
				if (result.getBoolean("deleted")) {
					continue;
				}
				Book book = getDataFromResult(result);

				books.put(book.getBookId(), book);

			}
			return books;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the book from database using the books ID as parameter.
	 * 
	 * @param bookID -> the books ID
	 * @return -> the book matching with ID or null if exception occurs
	 */
	public Book getBook(Integer bookID) {

		MySqlConnection connector = new MySqlConnection();
		try (Connection conn = connector.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(
					"SELECT * FROM libapp.books LEFT JOIN libapp.borrows ON books.id = borrows.book_id WHERE ID = "
							+ bookID);
			ResultSet result = statement.executeQuery();
			result.next();
			return getDataFromResult(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the persons book using the Person ID as parameter.
	 * 
	 * @param ID -> the Persons ID
	 * @return -> an ArrayList with the persons actually borrowed books, an empty
	 *         ArrayList if there is no borrowed book
	 */
	public ArrayList<Book> getPersonsBook(Integer ID) {
		ArrayList<Book> books = new ArrayList<>();

		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(
					"SELECT * FROM libapp.books JOIN libapp.borrows ON books.id = book_id WHERE return_date IS NULL AND member_id = "
							+ ID + " AND deleted = false");

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				Book book = getDataFromResult(results);
				books.add(book);
			}
			return books;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	/**
	 * The parameter and return is the same as in the BookJTable's setSelectedBook,
	 * both returns boolean, the differ is that this method checks for match in
	 * database, while the BookJTables's method sets the matching books row as
	 * selected
	 * 
	 * The check is based on validating ISBN number or if no match for ISBN then
	 * checks for match in title (same as .contains() just it is case insensitive)
	 * 
	 * @param bookToFind -> String represents the books accurate ISBN or title
	 * @return -> the found book's ID, if no match return null
	 */
	public Map<Integer, Book> checkForMatch(String bookToFind) {

		Map<Integer, Book> allBook = getAllBook();

		FilledBookDataValidator validator = new FilledBookDataValidator();

		// if it is a valid ISBN number
		if (validator.isISBNValid(bookToFind)) {
			Map<Integer, Book> foundBooks = allBook.entrySet()
					.stream()
					.filter(b -> b.getValue()
							.getBookISBN()
							.equals(bookToFind))
					.collect(Collectors.toMap(id -> id.getKey(), b -> b.getValue()));

			if (foundBooks.size() != 0) {
				return foundBooks;
			}
		}

		// if ISBN is not valid, check for title match
		Map<Integer, Book> foundBooks = allBook.entrySet()
				.stream()
				// Filtering as CASE INSENSITIVE using Pattern class (the function is same as
				// .contains() just in case insensitive mode
				.filter(b -> Pattern.compile(bookToFind, Pattern.CASE_INSENSITIVE)
						.matcher(b.getValue()
								.getBookTitle())
						.find())
				.collect(Collectors.toMap(id -> id.getKey(), b -> b.getValue()));
		return foundBooks;
		// if no match
	}

	/**
	 * This method is used on web site to extend the books deadline.
	 * 
	 * 
	 * @param personId -> The persons id who extends the books return deadline
	 * @param bookId   -> the extended books id
	 * @param interval -> plus interval added to actual deadline
	 * @return -> true if the update is successful 
	 */
	public boolean extendDeadline(Integer personId, Integer bookId, Integer interval) {

		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			PreparedStatement statement = conn
					.prepareStatement("UPDATE libapp.borrows SET expected_return =DATE_ADD(expected_return, INTERVAL "
							+ interval + " DAY) WHERE member_id = " + personId + " && book_id=" + bookId);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Register a book to MySql Database
	 * 
	 * 
	 * @param book -> the book to register
	 * @return true if no exception occurs and the books data row successfully
	 *         inserted to mySQL database
	 */
	public boolean registerBook(Book book) {
		MySqlConnection connector = new MySqlConnection();

		try (Connection conn = connector.getConnection()) {

			String valuesString = MySqlConnection.getValuesString(5);
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO libapp.books (title,publication_year,author,isbn,genre) " + valuesString);

			statement.setString(1, book.getBookTitle());
			statement.setInt(2, book.getBookYearOfPublication());
			statement.setString(3, book.getBookAuthor());
			statement.setString(4, book.getBookISBN());
			statement.setString(5, book.getBookGenre()
					.name());
			statement.execute();
			// return true if no exception
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This method logically deletes the book, but it will be in the database
	 * 
	 * @param ID -> the books ID
	 * @return -> true if deleted ("deleted" columns value set to 1, alias true) successfully
	 */
	public boolean deleteBook(Integer ID) {
		MySqlConnection connector = new MySqlConnection();
		try (Connection conn = connector.getConnection()) {

			PreparedStatement deleteStatement = conn
					.prepareStatement("UPDATE libapp.books SET deleted = true WHERE id = " + ID);
			deleteStatement.executeUpdate();

			// return true if no exception
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 
	 * @param result -> the result, from this set we got the information for
	 *               creating the Book object
	 * @return -> the Book object
	 * @throws SQLException
	 */
	private Book getDataFromResult(ResultSet result) throws SQLException {
		Integer id = result.getInt("id");
		String title = result.getString("title");
		Integer publicationYear = result.getInt("publication_year");
		String author = result.getString("author");
		String ISBN = result.getString("isbn");
		Genre genre = Genre.valueOf(result.getString("genre"));
		Integer timesBorrowed = result.getInt("times_borrowed");
		Boolean actuallyBorrowed = result.getBoolean("actually_borrowed");

		Integer actualBorrowerID = actuallyBorrowed ? result.getInt("member_id") : 0;

		Book book = new Book(publicationYear, title, author, ISBN, genre, timesBorrowed, actualBorrowerID);
		book.setBookId(id);
		book.setActuallyBorrowed(actuallyBorrowed);

		if (book.isActuallyBorrowed()) {

			book.setBorrowingDay(result.getDate("borrowing_day")
					.toLocalDate());
			book.setExpectedReturnDay(result.getDate("expected_return")
					.toLocalDate());
		}

		return book;
	}

}
