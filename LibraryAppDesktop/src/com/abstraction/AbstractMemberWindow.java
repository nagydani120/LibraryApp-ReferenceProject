package com.abstraction;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.table_handler.UserJTable;

/*
 * This abstract class cares about the windows component, filling the Date of Birth field with the valid values,
 * creating the fields and visual components, everything is realized here what is a common function of member window, 
 * + this class realized some getters.
 * 
 * 
 * 
 * WHERE THE CLASS IS REUSED
 * 
 * The class is reused (ChangeDataWindow uses this table to load the data from the database, and after
 * changes in members field and sending the data is updated in database too.
 * 
 * 
 * 
 * VALIDATING
 * 
 * the logic of validating and the functions is in the buttons where the action listener is used to 
 * handle the processes and then finally sends to database
 * 
 * 
 * 
 * BUTTONS 
 *  
 * the button is not part of the class, need to explicitly 
 * add using getFrame().getContentPane().add(**the button with the function**)
 * 
 * 
 * WINDOW CLOSING
 * 
 * The window closing does the actual frame dispose, refreshing the main table and setting again to enabled 
 * 
 * */
public abstract class AbstractMemberWindow {

	private JFrame mainFrame;
	private JFrame frame;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField adressField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField errorMsg;
	private ButtonGroup genders;
	private JComboBox<Integer> yearBox;
	private JComboBox<Integer> monthBox;
	private JComboBox<Integer> dayBox;
	private UserJTable table;

	public AbstractMemberWindow(JFrame mainFrame, String frameTitle, UserJTable table) {
		initialize(frameTitle);
		this.table = table;
		this.mainFrame = mainFrame;
		this.mainFrame.setEnabled(false);
		frame.setVisible(true);
	}

	/*
	 * The getters created because the buttons
	 */
	public UserJTable getUserJTable() {
		return table;
	}

	public JFrame getFrame() {
		return frame;
	}

	public ButtonGroup getGenders() {
		return genders;
	}

	public JComboBox<Integer> getYearBox() {
		return yearBox;
	}

	public JComboBox<Integer> getMonthBox() {
		return monthBox;
	}

	public JComboBox<Integer> getDayBox() {
		return dayBox;
	}

	public JTextField getFirstNameField() {
		return firstNameField;
	}

	public JTextField getLastNameField() {
		return lastNameField;
	}

	public JTextField getAdressField() {
		return adressField;
	}

	public JTextField getPhoneField() {
		return phoneField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JTextField getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.getContentPane()
				.setBackground(new Color(205, 171, 143));
		frame.setBounds(100, 100, 390, 350);

		// this method is called when user clicks on EXIT or when the button is
		// used(called from the buttons action listener)
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mainFrame.setEnabled(true);
				getUserJTable().refreshTableAndDisableButtons();
				frame.dispose();

			}
		});
		frame.getContentPane()
				.setLayout(null);

		JLabel lblFirstName = new JLabel("First name:");
		JLabel lblLastName = new JLabel("Last name:");
		JLabel lblDateOfBirth = new JLabel("Date of birth:");
		JLabel lblSex = new JLabel("Sex:");
		JLabel lblAddress = new JLabel("Address:");
		JLabel lblEmail = new JLabel("Email:");
		JLabel lblPhone = new JLabel("Phone:");

		lblLastName.setBounds(34, 50, 104, 15);
		lblFirstName.setBounds(34, 23, 104, 15);
		lblDateOfBirth.setBounds(34, 77, 104, 15);
		lblSex.setBounds(34, 113, 70, 15);
		lblAddress.setBounds(34, 153, 70, 15);
		lblEmail.setBounds(34, 207, 70, 15);
		lblPhone.setBounds(34, 180, 70, 15);

		firstNameField = new JTextField();
		lastNameField = new JTextField();
		adressField = new JTextField();
		emailField = new JTextField();
		errorMsg = new JTextField();
		phoneField = new JTextField();

		firstNameField.setBounds(156, 21, 171, 19);
		lastNameField.setBounds(156, 48, 171, 19);
		adressField.setBounds(156, 151, 171, 19);
		emailField.setBounds(156, 205, 171, 19);
		phoneField.setBounds(156, 178, 171, 19);
		errorMsg.setBounds(34, 222, 330, 19);

		firstNameField.setColumns(10);
		lastNameField.setColumns(10);
		adressField.setColumns(10);
		phoneField.setColumns(10);
		emailField.setColumns(10);
		emailField.setText("@");
		errorMsg.setColumns(10);
		errorMsg.setBorder(BorderFactory.createEmptyBorder());
		errorMsg.setBackground(new Color(205, 171, 143));
		errorMsg.setForeground(new Color(224, 27, 36));
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setFocusable(false);
		/*
		 * JCombo boxes for selecting the date of birth
		 */

		yearBox = new JComboBox<>();
		monthBox = new JComboBox<>();
		dayBox = new JComboBox<>();

		yearBox.setBounds(156, 72, 76, 24);
		monthBox.setBounds(244, 72, 54, 24);
		dayBox.setBounds(310, 72, 54, 24);
		fillDateOfBirthComboBoxes(yearBox, monthBox, dayBox);

		/*
		 * Action listeners to watch for changes in dates what can have effect to days
		 * of February (28 or 29)
		 */
		monthBox.addActionListener(m -> fillDayBox(yearBox, monthBox, dayBox));
		yearBox.addActionListener(m -> fillDayBox(yearBox, monthBox, dayBox));

		/*
		 * JRadio Button for selecting the sex
		 */

		genders = new ButtonGroup();
		JRadioButton rdbtnMale = new JRadioButton("Male");

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnMale.setActionCommand("Male");
		rdbtnFemale.setActionCommand("Female");
		rdbtnFemale.setFocusable(false);
		rdbtnMale.setFocusable(false);

		genders.add(rdbtnFemale);
		genders.add(rdbtnMale);
		genders.setSelected(rdbtnMale.getModel(), true);

		rdbtnMale.setBackground(new Color(205, 171, 143));
		rdbtnFemale.setBackground(new Color(205, 171, 143));

		rdbtnMale.setBounds(131, 109, 70, 23);
		rdbtnFemale.setBounds(209, 109, 89, 23);

		/*
		 * Adding all components to the formula panel
		 */

		frame.getContentPane()
				.add(rdbtnMale);
		frame.getContentPane()
				.add(rdbtnFemale);

		frame.getContentPane()
				.add(adressField);
		frame.getContentPane()
				.add(phoneField);
		frame.getContentPane()
				.add(firstNameField);
		frame.getContentPane()
				.add(lastNameField);
		frame.getContentPane()
				.add(emailField);
		frame.getContentPane()
				.add(errorMsg);

		frame.getContentPane()
				.add(yearBox);
		frame.getContentPane()
				.add(monthBox);
		frame.getContentPane()
				.add(dayBox);

		frame.getContentPane()
				.add(lblFirstName);
		frame.getContentPane()
				.add(lblLastName);
		frame.getContentPane()
				.add(lblDateOfBirth);
		frame.getContentPane()
				.add(lblSex);
		frame.getContentPane()
				.add(lblAddress);
		frame.getContentPane()
				.add(lblEmail);
		frame.getContentPane()
				.add(lblPhone);

		frame.setResizable(false);
	}

	private void fillDateOfBirthComboBoxes(JComboBox<Integer> year, JComboBox<Integer> month, JComboBox<Integer> day) {
		fillYearBox(year);
		fillMonthBox(month);
		fillDayBox(year, month, day);
	}

	/*
	 * This method is used by the action listener, if another month is selected the
	 * method automatically refills the comboboxes previous values with the selected
	 * months values
	 */
	private int getSelectedMonthsDaySum(JComboBox<Integer> year, JComboBox<Integer> month) {
		int selectedYear = (Integer) year.getSelectedItem();
		int selectedMonth = (Integer) month.getSelectedItem();

		return switch (selectedMonth) {
		case 1, 3, 5, 7, 8, 10, 12 -> 31;
		case 4, 6, 9, 11 -> 30;
		case 2 -> isLeapYear(selectedYear) ? 29 : 28;
		default -> 0;
		};

	}

	private void fillDayBox(JComboBox<Integer> year, JComboBox<Integer> month, JComboBox<Integer> day) {
		day.removeAllItems();
		int days = getSelectedMonthsDaySum(year, month);
		for (int i = 1; i <= days; i++) {
			day.addItem(i);
		}
	}

	private void fillMonthBox(JComboBox<Integer> month) {
		for (int i = 1; i <= 12; i++) {
			month.addItem(i);
		}
	}

	/*
	 * Fills the combobox with range -> actual year and actual year-100
	 */

	private void fillYearBox(JComboBox<Integer> year) {
		int actualYear = LocalDate.now()
				.getYear();
		for (int i = actualYear; i >= actualYear - 100; i--) {
			year.addItem(i);
		}
	}

	/*
	 * Leap year if year is divisible by 4 but can't be divided by 100 except if is
	 * divisible with 400 -> (without remaining)
	 */

	private boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}

}
