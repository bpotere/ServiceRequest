package com.keane.infra.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import com.keane.infra.domain.RequestDetails;

public class RequestDAO {

	/**
	 * Purpose:
	 * 	Insert the details of a Request to the database and return the reqID.
	 * @param objSR
	 * @return
	 * @throws InfraDAOException
	 */
	public int createNewSR(RequestDetails objSR) throws InfraDAOException{

		int reqId = -1;
		
		String insert = "INSERT INTO sr_details "
				+ "(req_id, user_id, location, cubicle_no, department, "
				+ "required_by_date, req_typeid, rejection_reason, cancellation_reason, requested_date, "
				+ "assigned_date, committed_date, completion_date, justification, status_id) "
				+ "VALUES(default, ?, ?, ?, ?, "
				+ "			 ?, ?, ?, ?, ?, "
				+ "			 ?, ?, ?, ?, ?) ";
		
		Connection conn = null;
		
		DAOUtil util = DAOUtil.getInstance();
		try {
			conn = DriverManager.getConnection(util.get_path(), util.getSqlUsername(), util.getSqlPassword());
			
			//Create the sql statement
			PreparedStatement stmt = conn.prepareStatement(insert);
			
			stmt.setInt(1, objSR.getUserId());
			stmt.setString(2, objSR.getLocation());
			stmt.setInt(3, objSR.getCublicleNo());
			stmt.setString(4, objSR.getDepartment());
			
			stmt.setDate(5, objSR.getRequiredByDate());
			stmt.setInt(6, objSR.getReqTypeId());
			stmt.setString(7, objSR.getRejectionReason());
			stmt.setString(8, objSR.getCancellationReason());
			stmt.setDate(9, objSR.getRequestedDate());
			
			stmt.setDate(10, objSR.getAssignedDate());
			stmt.setDate(11, objSR.getCommittedDate());
			stmt.setDate(12, objSR.getCompletedDate());
			stmt.setString(13, objSR.getJustification());
			stmt.setInt(14,	objSR.getStatusId());
			
			stmt.executeUpdate();
			
			//Get the ID from the auto increment
			String select = "SELECT AUTO_INCREMENT "
					+ "FROM information_schema.tables "
					+ "WHERE table_name = 'sr_details'"
					+ "AND table_shcema = DATABASE()";
			stmt = conn.prepareStatement(select);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				reqId = rs.getInt("AUTO_INCREMENT");
			}
			
			
			stmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (reqId == -1){
			throw new InfraDAOException("Could not add request " + objSR);
		}
		
		return reqId;
	}
	/**
	 * Purpose:
	 * 		Execute a query which selects the SR_Details table for the given req_id 
	 * 	and returns the RequestDetails object.
	 * @param req_id
	 * @return
	 * @throws InfraDAOException
	 */
	public RequestDetails getSRDetails(final int req_id) throws InfraDAOException {
		RequestDetails rd = null;
		
		Connection conn = null;
		DAOUtil util = DAOUtil.getInstance();

		String select = "SELECT * "
				+ "FROM sr_details "
				+ "WHERE req_id=?";
		
		try {
			conn = DriverManager.getConnection(util.get_path(), util.getSqlUsername(), util.getSqlPassword());
			PreparedStatement stmt = conn.prepareStatement(select);
			stmt.setInt(1, req_id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				int reqId = rs.getInt("req_id");
				String location = rs.getString("location");
				int cubicle_no = rs.getInt("cubicle_no");
				String department = rs.getString("department");
				Date required_by_date = rs.getDate("required_by_date");
				int req_typeid = rs.getInt("req_typeid");
				String rejection_reason = rs.getString("rejection_reason");
				String cancellation_reason = rs.getString("cancellation_reason");
				Date committed_date = rs.getDate("committed_date");
				String justification = rs.getString("justification");
				int status_id = rs.getInt("status_id");
				rd = new RequestDetails(reqId, cubicle_no, department, location, required_by_date, req_typeid, 
						justification, rejection_reason, cancellation_reason, status_id, committed_date);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InfraDAOException("Something went wrong with the sql");
		} finally {
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (rd == null){
			throw new InfraDAOException("Could not find request id: " + req_id);
		}

		return rd;
	}
	
	/**
	 * Purpose:
	 * 		Execute a query which selects the following in SR_Details table.
	 * 	REQ_ID, REQ_TYPEID, REQUESTED_DATE, ASSIGNED_DATE, COMMITTED_DATE, COMPLETED_DATE,
	 * 	STATUS_ID and returns the list of RequestDetails object.
	 * @return
	 * @throws InfraDAOException
	 */
	public List<RequestDetails> getAllRequests() throws InfraDAOException{
		List<RequestDetails> requestsList = new LinkedList<RequestDetails>();
		
		Connection conn = null;
		DAOUtil util = DAOUtil.getInstance();

		String select = "SELECT REQ_ID, REQ_TYPEID, REQUESTED_DATE, ASSIGNED_DATE, COMMITTED_DATE, "
				+ "COMPLETED_DATE, STATUS_ID, FROM sr_details";
		
		try {
			conn = DriverManager.getConnection(util.get_path(), util.getSqlUsername(), util.getSqlPassword());
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(select);
			
			while(rs.next()){
				
				int req_id = rs.getInt("req_id");
				int req_typeid = rs.getInt("req_typeid");
				Date requested_date = rs.getDate("requested_date");
				Date assigned_date = rs.getDate("assigned_date");
				Date committed_date = rs.getDate("committed_date");
				Date completed_date = rs.getDate("completed_date");
				int status_id = rs.getInt("status_id");
				
				RequestDetails rsTemp = new RequestDetails(req_id, status_id, assigned_date, 
						requested_date, committed_date, completed_date);
				rsTemp.setReqTypeId(req_typeid);
				
				requestsList.add(rsTemp);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (requestsList.size() < 1){
			throw new InfraDAOException("There are no requests in the database.");
		}
		
		return requestsList;
		
	}
	
	/**
	 * getRequestByCreator
	 * Purpose:
	 * 	Returns a list of requests in the database table rs_details for a given user.
	 * @param userId
	 * @return
	 * @throws InfraDAOException
	 */
	public List<RequestDetails> getRequestByCreator(final int userId) throws InfraDAOException{
List<RequestDetails> requestsList = new LinkedList<RequestDetails>();
		
		Connection conn = null;
		DAOUtil util = DAOUtil.getInstance();

		String select = "SELECT REQ_ID, REQ_TYPEID, "
				+ "REQUESTED_DATE, ASSIGNED_DATE, COMMITTED_DATE, COMPLETED_DATE, STATUS_ID, "
				+ "FROM sr_details "
				+ "WHERE user_id=?";
		
		try {
			conn = DriverManager.getConnection(util.get_path(), util.getSqlUsername(), util.getSqlPassword());
			PreparedStatement stmt = conn.prepareStatement(select);
			stmt.setInt(1, userId);
			
			
			ResultSet rs = stmt.executeQuery(select);
			
			while(rs.next()){
				
				int req_id = rs.getInt("req_id");
				Date requested_date = rs.getDate("requested_date");
				Date assigned_date = rs.getDate("assigned_date");
				Date committed_date = rs.getDate("committed_date");
				Date completed_date = rs.getDate("completed_date");
				int status_id = rs.getInt("status_id");
				
				RequestDetails rsTemp = new RequestDetails(req_id, status_id, assigned_date, 
						requested_date, committed_date, completed_date);
				
				requestsList.add(rsTemp);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (requestsList.size() < 1){
			throw new InfraDAOException("There are no requests in the database for user_id: " + userId);
		}
		
		return requestsList;
	}
	
	/**
	 * savesSRDetails
	 * Purpose:
	 * 	Updates the details of request.  Based on the STATUS_ID.  Returns true on successful
	 * update.
	 * @param objSR
	 * @return
	 * @throws InfraDAOException
	 */
	public boolean saveSRDetails(final RequestDetails objSR) throws InfraDAOException{
		boolean succeeded = false;
		
		Connection conn = null;
		DAOUtil util = DAOUtil.getInstance();
		
		int updated;
		
		String update = "UPDATE sr_details SET "
				+ "user_id = ?, location = 'Louisville', cubicle_no = ?, department = ?, "
				+ "required_by_date = ?, req_typeid = ?, rejection_reason = ?, cancellation_reason = ?, requested_date = ?, "
				+ "assigned_date = ?, committed_date = ?, completion_date = ?, justification = ?, status_id = ? "
				+ "WHERE req_id = ?";
		
		try {
			conn = DriverManager.getConnection(util.get_path(), util.getSqlUsername(), util.getSqlPassword());
			PreparedStatement stmt = conn.prepareStatement(update);
			
			stmt.setInt(1, objSR.getUserId());
			stmt.setString(2, objSR.getLocation());
			stmt.setInt(3, objSR.getCublicleNo());
			stmt.setString(4, objSR.getDepartment());
			
			stmt.setDate(5, objSR.getRequiredByDate());
			stmt.setInt(6, objSR.getReqTypeId());
			stmt.setString(7, objSR.getRejectionReason());
			stmt.setString(8, objSR.getCancellationReason());
			stmt.setDate(9, objSR.getRequestedDate());
			
			stmt.setDate(10, objSR.getAssignedDate());
			stmt.setDate(11, objSR.getCommittedDate());
			stmt.setDate(12, objSR.getCompletedDate());
			stmt.setString(13, objSR.getJustification());
			stmt.setInt(14,	objSR.getStatusId());
			
			stmt.setInt(15, objSR.getReqId());
			
			updated = stmt.executeUpdate();
			
			if (updated > 0){
				succeeded = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return succeeded;
	}
}
