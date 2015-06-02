package com.bijesh.donateblood.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DataStore {
	private static final String dbDriver = "com.mysql.jdbc.Driver";

	// TODO: Pick this up from a config file
	private static final String dbName = "bharaths_donateblood";
	private static final String dbUser = "bharaths_donate";
	private static final String dbPassword = "donateblood";


	private Connection dbcon = null;
	private String dbUrl = null;
	private PreparedStatement prepStatement = null;
	private ResultSet rs =null;

	public DataStore()  {
		dbUrl = "jdbc:mysql://localhost:3306/";
	}

	protected void dbConnect() throws SQLException {
		// Load the driver. Seems to be a workaround for some versions of java
		try {
			Class.forName(dbDriver).newInstance();
		} catch (Exception e) {
			throw new SQLException("DBConnect failed", e);
		}

		// Get the connection object
		dbcon = DriverManager.getConnection(dbUrl+dbName, dbUser , dbPassword);
    }

	protected ResultSet dbQuery(String query) throws SQLException {
		System.out.println("in DataStore dbQuery: "+query);
		Statement st = dbcon.createStatement();
		rs = st.executeQuery(query);
		return rs;
	}

	protected int dbExecute(String query) throws SQLException {
		Statement st = dbcon.createStatement();
		return st.executeUpdate(query);
	}

	protected PreparedStatement getPrepStatement(String query) throws SQLException {
		prepStatement = dbcon.prepareStatement(query);
		return prepStatement;
	}

	protected PreparedStatement getPrepStatement(String query,int autoIncNo) throws SQLException {
		prepStatement = dbcon.prepareStatement(query,autoIncNo);
		return prepStatement;
	}

	protected int dbExecutePrep() throws SQLException {
		int count = prepStatement.executeUpdate();
		return count;
	}

	protected void dbClose() {
		try {
			if(rs != null)
				rs.close();
			if(prepStatement != null)
				prepStatement.close();
			if(dbcon != null)
				dbcon.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




}
