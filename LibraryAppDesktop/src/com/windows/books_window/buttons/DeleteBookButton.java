package com.windows.books_window.buttons;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.database_manager.BookDatabaseDataTransfer;
import com.table_handler.BookJTable;

@SuppressWarnings("serial")
public class DeleteBookButton extends JButton implements ActionListener {

	private BookJTable bookTable;

	public DeleteBookButton(String buttonName, BookJTable bookTable) {
		super(buttonName);
		this.setEnabled(false);
		this.addActionListener(this);
		this.setFocusPainted(false);
		this.bookTable = bookTable;
		setIcon();
	}
/*
 * Delete process with confirm message first
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceBundle res = ResourceBundle.getBundle("messages");

		int answer = JOptionPane.showConfirmDialog(null, res.getString("book_delete_confirm"), null,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		// if yes
		if (answer == 0) {
			BookDatabaseDataTransfer transfer = new BookDatabaseDataTransfer();
			transfer.deleteBook(bookTable.getSelectedElement().getBookId());
			bookTable.refreshTableAndDisableButtons();
		}

	}
/*
 * Setting the delete buttons icon
 */
	private void setIcon() {
		Image deleteBookImage = new ImageIcon(getClass().getClassLoader().getResource("images/icons/deleteBookIcon.png")).getImage();
		Image scaledDeleteBookImg = deleteBookImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon deleteBookIcon = new ImageIcon(scaledDeleteBookImg);
		this.setIcon(deleteBookIcon);
	}
}
