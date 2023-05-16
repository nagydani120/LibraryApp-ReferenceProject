package com.windows.change_member_window;

import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.abstraction.AbstractMemberWindow;
import com.entities.Person;
import com.table_handler.UserJTable;

/*  ----> The change button:
 *  -- fills the fields with the actual data got from the database
 *  -- via the this window is possible to change the values(just valid informations, the validator works here too)
 *  -- action listener (on click) updates the members data in database 
 * 
 * */

public class ChangeMemberWindow extends AbstractMemberWindow {

	private Person personToChange;

	public ChangeMemberWindow(JFrame mainFrame, UserJTable table) {
		super(mainFrame, "Change", table);
		this.personToChange = table.getSelectedElement();
		initialize();
	}

	private void initialize() {

		// filling the table with persons data
		fillTableWithPersonsData(personToChange);
		getFrame().getContentPane()
				.add(new ChangeMemberDataButton("Change", this, personToChange));

		
	}


	private void fillTableWithPersonsData(Person p) {
		getFirstNameField().setText(p.getFirstName());
		getLastNameField().setText(p.getLastName());
		getAdressField().setText(p.getAddress());
		getPhoneField().setText(p.getPhoneNumber());
		getEmailField().setText(p.getEmailAddress());
		setGenderField(p.getGender());
		getYearBox().setSelectedItem(p.getDateOfBirth()
				.getYear());
		getMonthBox().setSelectedItem(p.getDateOfBirth()
				.getMonthValue());
		getDayBox().setSelectedItem(p.getDateOfBirth()
				.getDayOfMonth());
	}

//selects the gender button to persons gender comparing the getActionCommand and the gender String 
	private void setGenderField(String gender) {
		Iterator<AbstractButton> genders = getGenders().getElements()
				.asIterator();
		AbstractButton butt = new JButton();
		while (genders.hasNext()) {
			if ((butt = genders.next()).getActionCommand()
					.equals(gender)) {
				getGenders().setSelected(butt.getModel(), true);
			}
		}

	}

}
