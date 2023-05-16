package com.table_handler;

import java.awt.event.FocusEvent;
import java.util.Map;

import com.abstraction.AbstractTable;
import com.database_manager.EntityDatabaseDataTransfer;
import com.entities.Person;

@SuppressWarnings("serial")
public class UserJTable extends AbstractTable {

	/*
	 * This table is used in UsersMainWindow to show the actual members and infos
	 *
	 * -The focus listener is needed for manipulating with members via the table
	 * using buttons (delete, change data...)
	 */
	private static final String[] COLUMN_NAMES = new String[] { "ID", "First name", "Last name", "E-mail address" };

	public UserJTable() {
		super(COLUMN_NAMES);
		setCustomColumnWidth();
	}

	/**
	 * fills the UserJTable with active members
	 */
	@Override
	public void fillTableWithData() {
		// deleting the previous rows if i use the refreshing function
		model.setRowCount(0);

		EntityDatabaseDataTransfer trans = new EntityDatabaseDataTransfer();
		Map<Integer, Person> allMember = trans.getAllMember();

		allMember.entrySet()
				.stream()
				.filter(i -> i.getValue()
						.isActive())
				.forEach((id) -> model.addRow(new String[] { id.getKey()
						.toString(),
						id.getValue()
								.getFirstName(),
						id.getValue()
								.getLastName(),
						id.getValue()
								.getEmailAddress() }));
	}

	/*
	 * Sets the column width to make look better
	 */
	private void setCustomColumnWidth() {
		this.getColumnModel() // ID column
				.getColumn(0)
				.setMaxWidth(60);
		this.getColumnModel() // ID column
				.getColumn(0)
				.setMinWidth(20);
		this.getColumnModel() // email columns
				.getColumn(3)
				.setPreferredWidth(160);

	}

	/**
	 * 
	 * @param userToFind -> String represents the users accurate ID or email address
	 * @return -> true if the user found and the matching users row has been set,
	 *         else false
	 */
	public boolean setSelectedElement(String userToFind) {
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();

		// pre-check, and getting the persons ID if found
		Integer checkForMatch = transfer.checkForMatch(userToFind);
		//

		if (checkForMatch != null) {
			for (int i = 0; i < this.getRowCount(); i++) {
				if (Integer.valueOf(this.getModel()
						.getValueAt(convertRowIndexToModel(i), 0)
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

	public void refreshTableAndDisableButtons() {
		fillTableWithData();
		setButtonsAvailability(false);
	}

	/**
	 * 
	 * @return -> the selected person from UserJTable
	 */
	public Person getSelectedElement() {
		Integer selectedPersonID = Integer.valueOf(model.getValueAt(this.convertRowIndexToModel(getSelectedRow()), 0)
				.toString());
		// have to use the converter here because if the table is sorted, the basic
		// getSelectedRow returns the row what was initialized, not that what is there
		// actually
		EntityDatabaseDataTransfer transfer = new EntityDatabaseDataTransfer();
		return transfer.getPerson(selectedPersonID);
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
