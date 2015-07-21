package com.keane.infra.dao;

public class DAOUtil {
	private static final DAOUtil instance = new DAOUtil();
	
	final private String _path="jdbc:mysql://localhost:3306/servicerequest";
	final private String sqlUsername = "root";
	final private String sqlPassword = "password";
	
	private DAOUtil() {}

	public static DAOUtil getInstance() {
		return instance;
	}

	public String get_path() {
		return _path;
	}

	public String getSqlUsername() {
		return sqlUsername;
	}

	public String getSqlPassword() {
		return sqlPassword;
	}
	
	
	
	
	
}
