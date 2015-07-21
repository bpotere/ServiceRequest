package com.keane.infra.driver;

import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

import com.keane.infra.domain.RequestDetails;
import com.keane.infra.dao.*;

@SuppressWarnings("serial")
public class EmployeeEditRequests extends JInternalFrame {
	private JLabel lblTitle;
	private JTextField textFieldReqID;
	private JTextField textFieldUserID;
	private JTextField textFieldLocation;
	private JTextField textFieldCubicleNo;
	private JTextField textFieldDepartment;
	private JTextField textFieldRequiredDate;
	private JTextField textFieldReqTypeID;
	private JTextField textFieldRequestedDate;
	private JTextField textFieldAssignedDate;
	private JTextField textFieldCommittedDate;
	private JTextField textFieldCompletionDate;
	private JTextField textFieldStatusID;
	
	private int userID;
	List<RequestDetails> requestsList;
	private JLabel lblDateFormat;
	private JButton btnExit;
	private JButton btnSave;
	private JTextPane textPaneJustification;
	private JTextPane textPaneCancellationReason;
	private JLabel lblStatusError;
	/**
	 * Requires the user id number.
	 */
	public EmployeeEditRequests(int userID) {
		initComponents(userID);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean inputsCorrect = true;
				
				//Get request ID
				int reqId;
				try{
					reqId = Integer.parseInt(textFieldReqID.getText());
				} catch (NumberFormatException e){
					textFieldReqID.setText(e.getMessage());
					reqId = -1;
					inputsCorrect = false;
				}
				
				//Get location
				String location = textFieldLocation.getText();
				
				//Get cubicle number
				int CubicleNo;
				try{
					CubicleNo = Integer.parseInt(textFieldCubicleNo.getText());
				} catch (NumberFormatException e){
					textFieldCubicleNo.setText(e.getMessage());
					CubicleNo = -1;
					inputsCorrect = false;
				}
				String department = textFieldDepartment.getText();
				
				//Get Required Date
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				java.util.Date date;
				java.sql.Date sqlRequiredDate = null;
				try {
					date = sdf.parse(textFieldRequiredDate.getText());
					sqlRequiredDate = new Date(date.getTime()); 
				} catch (ParseException e) {
					//e.printStackTrace();
					textFieldRequiredDate.setText(e.getMessage());
					inputsCorrect = false;
				}
				
				//Get required type id
				int RequiredTypeID;
				try {
					RequiredTypeID = Integer.parseInt(textFieldReqTypeID.getText());
				} catch (NumberFormatException e){
					textFieldReqTypeID.setText(e.getMessage());
					RequiredTypeID = -1;
					inputsCorrect = false;
				}
				
				//Get cancellation reason
				String cancellation = textPaneCancellationReason.getText();
				
				int statusId;
				try{
					statusId = Integer.parseInt(textFieldStatusID.getText());
					lblStatusError.setText("");
				} catch (NumberFormatException e){
					lblStatusError.setText(e.getMessage());
					statusId = -1;
					inputsCorrect = false;
				}
				
				//Get the justification
				String justification = textPaneJustification.getText();
				
				if (inputsCorrect){
					RequestDetails service_details = new RequestDetails(
							reqId, CubicleNo, department, location, sqlRequiredDate, RequiredTypeID, justification, null, cancellation, statusId, null);
					RequestDAO rDAO = new RequestDAO();
					try {
						rDAO.saveSRDetails(service_details);
					} catch (InfraDAOException e) {
						lblTitle.setText(e.getMessage());
						//e.printStackTrace();
					}
				}
				
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 4;
		gbc_btnSave.gridy = 19;
		getContentPane().add(btnSave, gbc_btnSave);
		
	}
	private void initComponents(int userID) {
		this.setUserID(userID);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblTitle = new JLabel("Edit a Service Request");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 3;
		gbc_lblTitle.gridy = 1;
		getContentPane().add(lblTitle, gbc_lblTitle);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 1;
		getContentPane().add(btnExit, gbc_btnExit);
		
		JLabel lblreq_id = new JLabel("Request ID");
		GridBagConstraints gbc_lblreq_id = new GridBagConstraints();
		gbc_lblreq_id.insets = new Insets(0, 0, 5, 5);
		gbc_lblreq_id.gridx = 1;
		gbc_lblreq_id.gridy = 2;
		getContentPane().add(lblreq_id, gbc_lblreq_id);
		
		textFieldReqID = new JTextField();
		textFieldReqID.setEditable(false);
		GridBagConstraints gbc_textFieldReqID = new GridBagConstraints();
		gbc_textFieldReqID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldReqID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldReqID.gridx = 3;
		gbc_textFieldReqID.gridy = 2;
		getContentPane().add(textFieldReqID, gbc_textFieldReqID);
		textFieldReqID.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID");
		GridBagConstraints gbc_lblUserId = new GridBagConstraints();
		gbc_lblUserId.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserId.gridx = 1;
		gbc_lblUserId.gridy = 3;
		getContentPane().add(lblUserId, gbc_lblUserId);
		
		textFieldUserID = new JTextField();
		textFieldUserID.setEditable(false);
		GridBagConstraints gbc_textFieldUserID = new GridBagConstraints();
		gbc_textFieldUserID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUserID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUserID.gridx = 3;
		gbc_textFieldUserID.gridy = 3;
		getContentPane().add(textFieldUserID, gbc_textFieldUserID);
		textFieldUserID.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 1;
		gbc_lblLocation.gridy = 4;
		getContentPane().add(lblLocation, gbc_lblLocation);
		
		textFieldLocation = new JTextField();
		GridBagConstraints gbc_textFieldLocation = new GridBagConstraints();
		gbc_textFieldLocation.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLocation.gridx = 3;
		gbc_textFieldLocation.gridy = 4;
		getContentPane().add(textFieldLocation, gbc_textFieldLocation);
		textFieldLocation.setColumns(10);
		
		JLabel lblCubicleNo = new JLabel("Cubicle No.");
		GridBagConstraints gbc_lblCubicleNo = new GridBagConstraints();
		gbc_lblCubicleNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCubicleNo.gridx = 1;
		gbc_lblCubicleNo.gridy = 5;
		getContentPane().add(lblCubicleNo, gbc_lblCubicleNo);
		
		textFieldCubicleNo = new JTextField();
		GridBagConstraints gbc_textFieldCubicleNo = new GridBagConstraints();
		gbc_textFieldCubicleNo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCubicleNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCubicleNo.gridx = 3;
		gbc_textFieldCubicleNo.gridy = 5;
		getContentPane().add(textFieldCubicleNo, gbc_textFieldCubicleNo);
		textFieldCubicleNo.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 1;
		gbc_lblDepartment.gridy = 6;
		getContentPane().add(lblDepartment, gbc_lblDepartment);
		
		textFieldDepartment = new JTextField();
		GridBagConstraints gbc_textFieldDepartment = new GridBagConstraints();
		gbc_textFieldDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartment.gridx = 3;
		gbc_textFieldDepartment.gridy = 6;
		getContentPane().add(textFieldDepartment, gbc_textFieldDepartment);
		textFieldDepartment.setColumns(10);
		
		lblDateFormat = new JLabel("Use format yyyy-mm-dd");
		GridBagConstraints gbc_lblDateFormat = new GridBagConstraints();
		gbc_lblDateFormat.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateFormat.gridx = 3;
		gbc_lblDateFormat.gridy = 7;
		getContentPane().add(lblDateFormat, gbc_lblDateFormat);
		
		JLabel lblRequiredByDate = new JLabel("Required by Date");
		GridBagConstraints gbc_lblRequiredByDate = new GridBagConstraints();
		gbc_lblRequiredByDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequiredByDate.gridx = 1;
		gbc_lblRequiredByDate.gridy = 8;
		getContentPane().add(lblRequiredByDate, gbc_lblRequiredByDate);
		
		textFieldRequiredDate = new JTextField();
		GridBagConstraints gbc_textFieldRequiredDate = new GridBagConstraints();
		gbc_textFieldRequiredDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRequiredDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRequiredDate.gridx = 3;
		gbc_textFieldRequiredDate.gridy = 8;
		getContentPane().add(textFieldRequiredDate, gbc_textFieldRequiredDate);
		textFieldRequiredDate.setColumns(10);
		
		JLabel lblRequiredTypeId = new JLabel("Required Type ID");
		GridBagConstraints gbc_lblRequiredTypeId = new GridBagConstraints();
		gbc_lblRequiredTypeId.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequiredTypeId.gridx = 1;
		gbc_lblRequiredTypeId.gridy = 9;
		getContentPane().add(lblRequiredTypeId, gbc_lblRequiredTypeId);
		
		textFieldReqTypeID = new JTextField();
		GridBagConstraints gbc_textFieldReqTypeID = new GridBagConstraints();
		gbc_textFieldReqTypeID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldReqTypeID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldReqTypeID.gridx = 3;
		gbc_textFieldReqTypeID.gridy = 9;
		getContentPane().add(textFieldReqTypeID, gbc_textFieldReqTypeID);
		textFieldReqTypeID.setColumns(10);
		
		JLabel lblRejectionReason = new JLabel("Rejection Reason");
		GridBagConstraints gbc_lblRejectionReason = new GridBagConstraints();
		gbc_lblRejectionReason.insets = new Insets(0, 0, 5, 5);
		gbc_lblRejectionReason.gridx = 1;
		gbc_lblRejectionReason.gridy = 10;
		getContentPane().add(lblRejectionReason, gbc_lblRejectionReason);
		
		JTextPane textPaneRejectionReason = new JTextPane();
		textPaneRejectionReason.setEditable(false);
		GridBagConstraints gbc_textPaneRejectionReason = new GridBagConstraints();
		gbc_textPaneRejectionReason.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneRejectionReason.fill = GridBagConstraints.BOTH;
		gbc_textPaneRejectionReason.gridx = 3;
		gbc_textPaneRejectionReason.gridy = 10;
		getContentPane().add(textPaneRejectionReason, gbc_textPaneRejectionReason);
		
		JLabel lblCancellationReason = new JLabel("Cancellation Reason");
		GridBagConstraints gbc_lblCancellationReason = new GridBagConstraints();
		gbc_lblCancellationReason.insets = new Insets(0, 0, 5, 5);
		gbc_lblCancellationReason.gridx = 1;
		gbc_lblCancellationReason.gridy = 12;
		getContentPane().add(lblCancellationReason, gbc_lblCancellationReason);
		
		textPaneCancellationReason = new JTextPane();
		GridBagConstraints gbc_textPaneCancellationReason = new GridBagConstraints();
		gbc_textPaneCancellationReason.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneCancellationReason.fill = GridBagConstraints.BOTH;
		gbc_textPaneCancellationReason.gridx = 3;
		gbc_textPaneCancellationReason.gridy = 12;
		getContentPane().add(textPaneCancellationReason, gbc_textPaneCancellationReason);
		
		JLabel lblRequestedDate = new JLabel("Requested Date");
		GridBagConstraints gbc_lblRequestedDate = new GridBagConstraints();
		gbc_lblRequestedDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequestedDate.gridx = 1;
		gbc_lblRequestedDate.gridy = 14;
		getContentPane().add(lblRequestedDate, gbc_lblRequestedDate);
		
		textFieldRequestedDate = new JTextField();
		textFieldRequestedDate.setEditable(false);
		GridBagConstraints gbc_textFieldRequestedDate = new GridBagConstraints();
		gbc_textFieldRequestedDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRequestedDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRequestedDate.gridx = 3;
		gbc_textFieldRequestedDate.gridy = 14;
		getContentPane().add(textFieldRequestedDate, gbc_textFieldRequestedDate);
		textFieldRequestedDate.setColumns(10);
		
		JLabel lblAssignedDate = new JLabel("Assigned Date");
		GridBagConstraints gbc_lblAssignedDate = new GridBagConstraints();
		gbc_lblAssignedDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssignedDate.gridx = 1;
		gbc_lblAssignedDate.gridy = 15;
		getContentPane().add(lblAssignedDate, gbc_lblAssignedDate);
		
		textFieldAssignedDate = new JTextField();
		textFieldAssignedDate.setEditable(false);
		GridBagConstraints gbc_textFieldAssignedDate = new GridBagConstraints();
		gbc_textFieldAssignedDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAssignedDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAssignedDate.gridx = 3;
		gbc_textFieldAssignedDate.gridy = 15;
		getContentPane().add(textFieldAssignedDate, gbc_textFieldAssignedDate);
		textFieldAssignedDate.setColumns(10);
		
		JLabel lblCommittedDate = new JLabel("Committed Date");
		GridBagConstraints gbc_lblCommittedDate = new GridBagConstraints();
		gbc_lblCommittedDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblCommittedDate.gridx = 1;
		gbc_lblCommittedDate.gridy = 16;
		getContentPane().add(lblCommittedDate, gbc_lblCommittedDate);
		
		textFieldCommittedDate = new JTextField();
		textFieldCommittedDate.setEditable(false);
		GridBagConstraints gbc_textFieldCommittedDate = new GridBagConstraints();
		gbc_textFieldCommittedDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCommittedDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCommittedDate.gridx = 3;
		gbc_textFieldCommittedDate.gridy = 16;
		getContentPane().add(textFieldCommittedDate, gbc_textFieldCommittedDate);
		textFieldCommittedDate.setColumns(10);
		
		JLabel lblCompletionDate = new JLabel("Completion Date");
		GridBagConstraints gbc_lblCompletionDate = new GridBagConstraints();
		gbc_lblCompletionDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompletionDate.gridx = 1;
		gbc_lblCompletionDate.gridy = 17;
		getContentPane().add(lblCompletionDate, gbc_lblCompletionDate);
		
		textFieldCompletionDate = new JTextField();
		textFieldCompletionDate.setEditable(false);
		GridBagConstraints gbc_textFieldCompletionDate = new GridBagConstraints();
		gbc_textFieldCompletionDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCompletionDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCompletionDate.gridx = 3;
		gbc_textFieldCompletionDate.gridy = 17;
		getContentPane().add(textFieldCompletionDate, gbc_textFieldCompletionDate);
		textFieldCompletionDate.setColumns(10);
		
		JLabel lblStatusId = new JLabel("Status ID");
		GridBagConstraints gbc_lblStatusId = new GridBagConstraints();
		gbc_lblStatusId.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusId.gridx = 1;
		gbc_lblStatusId.gridy = 18;
		getContentPane().add(lblStatusId, gbc_lblStatusId);
		
		textFieldStatusID = new JTextField();
		textFieldStatusID.setEditable(false);
		GridBagConstraints gbc_textFieldStatusID = new GridBagConstraints();
		gbc_textFieldStatusID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldStatusID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldStatusID.gridx = 3;
		gbc_textFieldStatusID.gridy = 18;
		getContentPane().add(textFieldStatusID, gbc_textFieldStatusID);
		textFieldStatusID.setColumns(10);
		
		lblStatusError = new JLabel("");
		GridBagConstraints gbc_lblStatusError = new GridBagConstraints();
		gbc_lblStatusError.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusError.gridx = 4;
		gbc_lblStatusError.gridy = 18;
		getContentPane().add(lblStatusError, gbc_lblStatusError);
		
		JLabel lblJustification = new JLabel("Justification");
		GridBagConstraints gbc_lblJustification = new GridBagConstraints();
		gbc_lblJustification.insets = new Insets(0, 0, 0, 5);
		gbc_lblJustification.gridx = 1;
		gbc_lblJustification.gridy = 19;
		getContentPane().add(lblJustification, gbc_lblJustification);
		
		textPaneJustification = new JTextPane();
		GridBagConstraints gbc_textPaneJustification = new GridBagConstraints();
		gbc_textPaneJustification.insets = new Insets(0, 0, 0, 5);
		gbc_textPaneJustification.fill = GridBagConstraints.BOTH;
		gbc_textPaneJustification.gridx = 3;
		gbc_textPaneJustification.gridy = 19;
		getContentPane().add(textPaneJustification, gbc_textPaneJustification);
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}



}
