package com.keane.infra.driver;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;

import com.keane.infra.domain.User;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;



@SuppressWarnings("serial")
public class Driver extends javax.swing.JFrame {
	private User user;
	private JMenu mnLogin;
	private JMenu mnEmployee;
	private JMenu mnAdmin;
	private JMenuItem mntmCreateNewRequest;
	private JMenuItem mntmViewYourRequests;
	private JMenuItem mntmViewAllRequests;
	private JDesktopPane jDesktopPaneHome;
	public Driver() {
		
		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar(menuBar);
		
		mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mnLogin.add(mntmLogin);
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login(mnLogin, mnEmployee, mnAdmin, (Driver) getParent());
				jDesktopPaneHome.add(login);
				login.setVisible(true);
			}
		});
	
		
		
		
		
		mnEmployee = new JMenu("Employee");
		menuBar.add(mnEmployee);
		mnEmployee.setVisible(false);
		
		mntmCreateNewRequest = new JMenuItem("Create New Request");
		mnEmployee.add(mntmCreateNewRequest);
		
		mntmViewYourRequests = new JMenuItem("View My Requests");
		mntmViewYourRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewMyRequests vmr = new ViewMyRequests(user.getId());
				jDesktopPaneHome.add(vmr);
				vmr.setVisible(true);
				
			}
		});
		mnEmployee.add(mntmViewYourRequests);
		
		mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);
		mnAdmin.setVisible(false);
		
		mntmViewAllRequests = new JMenuItem("View All Requests");
		mnAdmin.add(mntmViewAllRequests);
		
		jDesktopPaneHome = new JDesktopPane();
		getContentPane().add(jDesktopPaneHome, BorderLayout.CENTER);
		jDesktopPaneHome.setLayout(new GridLayout(1, 0, 0, 0));
		jDesktopPaneHome.setSize(500, 500);
	}

	

	public static void main(String[] args) {
		
		/* Create and display the form */
	    java.awt.EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            new Driver().setVisible(true);
	        }
	    });
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

	public JMenuItem getMntmCreateNewRequest() {
		return mntmCreateNewRequest;
	}

	public JMenuItem getMntmViewYourRequests() {
		return mntmViewYourRequests;
	}

	public JMenuItem getMntmViewAllRequests() {
		return mntmViewAllRequests;
	}

	public JDesktopPane getjDesktopPaneHome() {
		return jDesktopPaneHome;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
