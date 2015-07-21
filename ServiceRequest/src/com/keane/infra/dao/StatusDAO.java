package com.keane.infra.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import com.keane.infra.domain.Status;

public class StatusDAO {
	
	/**
	 * getStatusDetails
	 * Purpose:
	 *	 Execute a query which selects relevant records in the status table based on ROLE_ID & STATUS_ID 
	 *	and return that list of records.
	 * @param roleid
	 * @param statusid
	 * @return
	 * @throws InfraDAOException
	 */
	public List<Status> getStatusDetails( final int roleid, final int statusid) throws InfraDAOException{
		List<Status> statusList = new LinkedList<Status>();
		
		Connection conn = null;
		DAOUtil util = DAOUtil.getInstance();

		String select = "SELECT * "
				+ "FROM status "
				+ "WHERE role_id = ? AND status_id = ?";
		
		try {
			conn = DriverManager.getConnection(util.get_path(), util.getSqlUsername(), util.getSqlPassword());
			
			PreparedStatement stmt = conn.prepareStatement(select);
			stmt.setInt(1, roleid);
			stmt.setInt(2, statusid);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				String statusdesc = rs.getString("stsdesc");
				Status status = new Status(statusid, statusdesc);
				
				statusList.add(status);
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
		
		if (statusList == null){
			throw new InfraDAOException("There are no entries in the table with roleid: " 
											+ roleid + " and statusid " + statusid);
		}
		
		return statusList;
	}

}
