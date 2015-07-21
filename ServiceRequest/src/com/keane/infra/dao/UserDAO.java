package com.keane.infra.dao;

import com.keane.infra.domain.User;

import java.sql.*;

public class UserDAO {
	
	
	
	public UserDAO(){}
	
	public User validateUser(int userid, String passwd) throws InfraDAOException{
		User user = null;
		
		Connection conn = null;
		try {
			DAOUtil util = DAOUtil.getInstance();
			conn = DriverManager.getConnection(util.get_path(), util.getSqlUsername(), util.getSqlPassword());
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE user_Id=? AND password=?");
			stmt.setInt(1, userid);
			stmt.setString(2, passwd);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				user = new User(rs.getInt("user_id"), rs.getString("password"), rs.getString("name"), rs.getInt("role_id"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (user == null){
			throw new InfraDAOException("Could not find user: "+ userid + " in database");
		}
		
		return user;
	}

}
