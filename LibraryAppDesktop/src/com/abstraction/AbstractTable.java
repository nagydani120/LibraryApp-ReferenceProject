package com.abstraction;

import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * 
 * This abstract class is created because of the tables in LibraryApp has common
 * functions like the row sorting, the cells are not editable directly, the
 * frames buttons are depends on table,so that is implemented here too...
 * 
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractTable extends JTable implements ManipulableTable, FocusListener {

	private ArrayList<JButton> buttonsThatDependsOnAvailability = new ArrayList<>();

	// the model is protected to able to use in subclass(different package)
	protected DefaultTableModel model;

	/**
	 * Constructor
	 */
	public AbstractTable(String[] columns) {
		initialize(columns);
		fillTableWithData();
		setCustomRowSorter();
	}

	public void refreshTableAndDisableButtons() {
		fillTableWithData();
		setButtonsAvailability(false);
	}

	private void initialize(String[] columnNames) {

		/*
		 * When creating the model i override the isCellEditable to disable the direct
		 * editing in table, editing is just able via button
		 */

		model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.setModel(model);
		this.addFocusListener(this);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Just 1 row is selectable at the same time

	}

	/**
	 * disabling the buttons what is not enabled when there is no selected row,
	 * alias no element selected
	 */

	protected void setButtonsAvailability(boolean enabled) {
		Iterator<JButton> iterator = buttonsThatDependsOnAvailability.iterator();
		while (iterator.hasNext()) {
			iterator.next()
					.setEnabled(enabled);
		}
	}

	private void setCustomRowSorter() {
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
		sorter.setComparator(0, (a, b) -> {
			return Integer.compare(Integer.valueOf(a.toString()), Integer.valueOf(b.toString()));
		});
		this.setRowSorter(sorter);
	}

	/**
	 * This method is used by the main frames to add the buttons, which availability
	 * depends that there is selected entity in table or not. if there is no
	 * selected, after adding the mentioned buttons the setButtonsAvailiabilty
	 * method sets the all of them to disabled.
	 */
	public ArrayList<JButton> getButtonsList() {
		return buttonsThatDependsOnAvailability;
	}

	/**
	 * the extended class must realize how the table(or model) is filled with the
	 * data
	 */
	protected abstract void fillTableWithData();

}
