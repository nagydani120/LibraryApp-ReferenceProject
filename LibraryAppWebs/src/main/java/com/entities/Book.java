package com.entities;

import java.time.LocalDate;

public class Book implements ArrangeableToTables{

	private Integer bookId;
	private Integer bookYearOfPublication;
	private String bookTitle;
	private String bookAuthor;
	private String bookISBN;
	private Genre bookGenre;
	private Integer timesBorrowed;
	private Integer actualBorrowerID;
	private boolean actuallyBorrowed;

	private LocalDate borrowingDay;
	private LocalDate expectedReturnDay;

	public Book(Integer bookYearOfPublication, String bookTitle, String bookAuthor, String bookISBN, Genre bookGenre) {
		this.bookYearOfPublication = bookYearOfPublication;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookISBN = bookISBN;
		this.bookGenre = bookGenre;
	}

	public Book(Integer bookYearOfPublication, String bookName, String bookAuthor, String bookISBN, Genre bookGenre,
			Integer timesBorrowed, Integer actualBorrowerID) {
		this.bookYearOfPublication = bookYearOfPublication;
		this.bookTitle = bookName;
		this.bookAuthor = bookAuthor;
		this.bookISBN = bookISBN;
		this.bookGenre = bookGenre;
		this.timesBorrowed = timesBorrowed;
		this.actualBorrowerID = actualBorrowerID;
	}

	public void setBorrowingDay(LocalDate day) {
		this.borrowingDay = day;
	}

	public LocalDate getBorrowingDay() {
		return borrowingDay;
	}

	public void setExpectedReturnDay(LocalDate day) {
		this.expectedReturnDay = day;
	}

	public LocalDate getExpectedReturnDay() {
		return expectedReturnDay;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBookYearOfPublication() {
		return bookYearOfPublication;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public Genre getBookGenre() {
		return bookGenre;
	}

	public Integer getTimesBorrowed() {
		return timesBorrowed;
	}

	public boolean isActuallyBorrowed() {
		return actuallyBorrowed;
	}

	public void setActuallyBorrowed(boolean actuallyBorrowed) {
		this.actuallyBorrowed = actuallyBorrowed;
	}

	public Integer getActualBorrower() {
		return actualBorrowerID;
	}

	// represents the found book row at the borrow list (Borrow Window)
	@Override
	public String toString() {
		return String.format("ID%-6s %s  (%s)", bookId, bookTitle, bookAuthor);
	}

}
