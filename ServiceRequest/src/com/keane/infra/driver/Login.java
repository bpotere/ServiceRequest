package com.keane.infra.driver;

import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.keane.infra.dao.*;
import com.keane.infra.domain.User;

@SuppressWarnings("serial")
public class Login extends JInternalFrame{
	private JTextField textFieldUserID;
	private JPasswordField passwordField;
	
	private Driver driver;
	private JMenu mnLogin;
	private JMenu mnEmployee;
	private JMenu mnAdmin;

	public Login(JMenu mnLogin, JMenu mnEmployee, JMenu mnAdmin, Driver driver){
		this.driver = driver;
		this.mnLogin = mnLogin;
		this.mnEmployee = mnEmployee;
		this.mnAdmin = mnAdmin;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {44, 46, 69, 213, 40};
		gridBagLayout.rowHeights = new int[]{60, 20, 30, 20, 38, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel labelMessage = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 3;
		gbc_label.gridy = 0;
		getContentPane().add(labelMessage, gbc_label);
		
		JLabel lblUserId = new JLabel("User ID");
		GridBagConstraints gbc_lblUserId = new GridBagConstraints();
		gbc_lblUserId.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblUserId.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserId.gridx = 1;
		gbc_lblUserId.gridy = 1;
		getContentPane().add(lblUserId, gbc_lblUserId);
		
		textFieldUserID = new JTextField();
		GridBagConstraints gbc_textFieldUserID = new GridBagConstraints();
		gbc_textFieldUserID.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUserID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUserID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUserID.gridx = 3;
		gbc_textFieldUserID.gridy = 1;
		getContentPane().add(textFieldUserID, gbc_textFieldUserID);
		textFieldUserID.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 3;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 3;
		getContentPane().add(passwordField, gbc_passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDAO userdao = new UserDAO();
				try {
					
					//Gets the user from the database
					@SuppressWarnings("deprecation")
					User user = userdao.validateUser(Integer.parseInt(textFieldUserID.getText()), passwordField.getText());
					labelMessage.setText("Welcome " + user.getName() + "!");
					
					saveUser(user);
					
					mnLogin.setVisible(false);
					
					if (user.getRoleId() == 1){
						mnEmployee.setVisible(true);
						mnAdmin.setVisible(true);
					}
					else if (user.getRoleId() == 2){
						mnEmployee.setVisible(true);
					}
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					labelMessage.setText(e1.getMessage());
				} catch (InfraDAOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					labelMessage.setText(e1.getMessage());
				}
				
			}
		});
		
		JButton btnCloseForm = new JButton("Close Form");
		btnCloseForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCloseForm = new GridBagConstraints();
		gbc_btnCloseForm.insets = new Insets(0, 0, 0, 5);
		gbc_btnCloseForm.gridx = 1;
		gbc_btnCloseForm.gridy = 5;
		getContentPane().add(btnCloseForm, gbc_btnCloseForm);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 5;
		getContentPane().add(btnLogin, gbc_btnLogin);
		
	}
	
	private void saveUser(User user){
		user = new User(user.getId(), user.getPasswd(), user.getName(), user.getRoleId());
		driver.setUser(user);
	}

	public JTextField getTextFieldUserID() {
		return textFieldUserID;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JMenu getMnLogin() {
		return mnLogin;
	}

	public JMenu getMnEmployee() {
		return mnEmployee;
	}

	public JMenu getMnAdmin() {
		return mnAdmin;
	}

}
