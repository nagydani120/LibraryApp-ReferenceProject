package com.table_handler;

import java.awt.event.FocusEvent;
import java.util.Map;

import javax.swing.JTable;

import com.abstraction.AbstractTable;
import com.database_manager.BookDatabaseDataTransfer;
import com.entities.Book;

@SuppressWarnings("serial")
public class BookJTable extends AbstractTable {
	/*
	 * This table is used in UsersMainWindow to show the actual members and infos
	 *
	 * -The focus listener is needed for manipulating with members via the table
	 * using buttons (delete, change data...)
	 */
	private static final String[] COLUMN_NAMES = new String[] { "ID", "Book name", "Author", "Genre", "ISBN",
			"Borrowed" };


	public BookJTable() {
		super(COLUMN_NAMES);
		setCustomColumnWidth(this);
	}


	@Override
	protected void fillTableWithData() {
		// remove the rows added before if updating
		model.setRowCount(0);
		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
		Map<Integer, Book> allBook = transfer.getAllBook();

		allBook.entrySet()
				.stream()
				.forEach(b -> model.addRow(new String[] { b.getKey()
						.toString(),
						b.getValue()
								.getBookTitle(),
						b.getValue()
								.getBookAuthor(),
						b.getValue()
								.getBookGenre()
								.toString(),
						b.getValue()
								.getBookISBN(),
						b.getValue()
								.getActualBorrower() != 0 ? "Yes" : "No" }));
	}

	/*
	 * Sets the column width to make graphically acceptable
	 */
	private void setCustomColumnWidth(JTable table) {
		table.getColumnModel() // id columns
				.getColumn(0)
				.setMaxWidth(60);
		table.getColumnModel()
				.getColumn(0)
				.setMinWidth(20);
		table.getColumnModel() // author column
				.getColumn(1)
				.setPreferredWidth(160);
		table.getColumnModel() // genre column
				.getColumn(3)
				.setMaxWidth(70);
		table.getColumnModel() // Borrowed column
				.getColumn(5)
				.setMaxWidth(70);

	}

	/**
	 * 
	 * @param bookToSet -> String represents the books accurate ID or title
	 * @return -> true if the book was found and the matching book's row has been
	 *         set as selected
	 */
	@Override
	public boolean setSelectedElement(String bookToSet) {
		// some pre-check, to find book the searching text must be at least 2 character
		// long
		if (bookToSet.length() < 2) {
			return false;
		}

		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();

		// returns the first match (at ISBN the match can be just 1 because at ISBN
		// validating is unique, but at title search the first match is set as
		// selected
		Map<Integer, Book> matchingBooks = transfer.checkForMatch(bookToSet);

		if (matchingBooks.size() != 0) {
			Integer checkForMatch = matchingBooks.keySet() // getting the first matches ID
					.iterator()
					.next();

			for (int i = 0; i < model.getRowCount(); i++) {
				if (Integer.valueOf(model.getValueAt(convertRowIndexToModel(i), 0)
						// have to use the converter here because if the table is sorted, the basic
						// getSelectedRow returns
						// the row what was initialized, not that what is there actually
						.toString())
						.equals(checkForMatch)) {
					this.changeSelection(i, 1, false, false);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @return the selected books id from the JTable, if there is no selected, the
	 *         returned value is -1
	 */
	@Override
	public Book getSelectedElement() {
		String idString = model.getValueAt(this.convertRowIndexToModel(getSelectedRow()), 0)
				// have to use the converter here because if the table is sorted, the basic
				// getSelectedRow returns
				// the row what was initialized, not that what is there actually

				.toString();
		BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
		return transfer.getBook(Integer.valueOf(idString));

	}

	@Override
	public void focusGained(FocusEvent e) {
//this is used for regain the focus after next click
		this.setFocusable(false);
		this.setFocusable(true);
		setButtonsAvailability(true);
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

}
