package com.keane.infra.driver;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;

import com.keane.infra.domain.RequestDetails;
import com.keane.infra.dao.InfraDAOException;
import com.keane.infra.dao.RequestDAO;



@SuppressWarnings("serial")
public class ViewMyRequests extends JInternalFrame {
	Vector<String> columnNames;
	Vector<RequestDetails> data;
	
	private JTable table;
	public ViewMyRequests(final int userID) {
		//Get column names for the table display
		initColumnNames();
		
		//Get the table data
		initData(userID);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		
		
		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
		
		
		
	}
	
	private void initColumnNames(){
		columnNames = new Vector<String>();
		
		columnNames.addElement("req_id");
		columnNames.addElement("user_id");
		columnNames.addElement("location_id");
		columnNames.addElement("cubicle_no");
		columnNames.addElement("department");
		
		columnNames.addElement("required_by_date");
		columnNames.addElement("req_typeid");
		columnNames.addElement("rejection_reason");
		columnNames.addElement("cancellation_reason");
		columnNames.addElement("requested_date");
		
		columnNames.addElement("assigned_date");
		columnNames.addElement("committed_date");
		columnNames.addElement("completion_date");
		columnNames.addElement("justification");
		columnNames.addElement("status_id");
	}
	
	private void initData(final int userID){
		
		List<RequestDetails> requestList;
		
		RequestDAO rDAO = new RequestDAO();
		try {
			requestList = rDAO.getRequestByCreator(userID);
		} catch (InfraDAOException e) {
			requestList = new LinkedList<RequestDetails>();
			e.printStackTrace();
		}
	
		for (RequestDetails rd : requestList){
			data.addElement(rd);
		}
		
	}


}
