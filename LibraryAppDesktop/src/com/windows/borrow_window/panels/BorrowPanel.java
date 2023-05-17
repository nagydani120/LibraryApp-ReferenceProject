package com.windows.borrow_window.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.entities.Book;
import com.entities.Person;
import com.windows.borrow_window.BorrowButton;
import com.windows.borrow_window.SearchBookButton;

@SuppressWarnings("serial")
public class BorrowPanel extends JPanel {
	private JTextField textField;
	private Person person;

	public BorrowPanel(Person person) {
		this.person = person;
		initialize();
	}

	private void initialize() {
		ResourceBundle res = ResourceBundle.getBundle("messages");
		this.setBackground(new Color(181, 131, 90));
		this.setLayout(new BorderLayout());

		textField = new JTextField();
		textField.setToolTipText(res.getString("borrow_search_field_tooltip"));
		textField.setColumns(15);
		
		
		JScrollPane scrollPane = new JScrollPane();

		
		JList<Book> list = new JList<>();
		scrollPane.setViewportView(list);
		list.setBackground(new Color(205, 171, 143));
		
		
		JButton btnSearchForBook = new SearchBookButton("Search for book", textField, list);
		JButton btnBorrow = new BorrowButton("Borrow", list, person,btnSearchForBook);
		
		JPanel searchPanel = new JPanel();
				searchPanel.add(textField);
		searchPanel.add(btnSearchForBook);
		searchPanel.setBackground(new Color(205, 171, 143));
		
		this.add(btnBorrow,BorderLayout.SOUTH);
		this.add(searchPanel,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
	}
}
