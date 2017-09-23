package com.ey.application.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBManager {

	// --- Globals
	Statement stmt;
	PreparedStatement prpstmt;
	Connection conn;

	public DBManager() throws Exception {
		String sDriverName = "com.mysql.jdbc.Driver";
		// now we set up a set of fairly basic string variables to use in the
		// body of the code proper
		String sDb 		= readProperties("proj.properties","sKaikei");
		String sJdbc 	= "jdbc:mysql";
		String sHost 	= "localhost";
		String sPort 	= "3306";
		//String sDbUrl 	= sJdbc + "://" + sHost + ":" + sPort + "/" + sDb;
		String sDbUrl 	= sJdbc + "://" + sHost + ":" + sPort + "/" + sDb+"?autoReconnect=true&useSSL=false";
		
		String sUserName = readProperties("proj.properties","dbUserName");
		String sPassWord = readProperties("proj.properties","dbPass");
		// which will produce a legitimate Url for mySQL JDBC :
		// "jdbc:mysql://localhost:3306/EY_db"
		int iTimeout = 30;
		// String sMakeTable = "CREATE TABLE dummy (id numeric, response text)";
		// String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the
		// database')";
		// String sMakeSelect = "SELECT response from dummy";

		try {
			// register the driver
			Class.forName(sDriverName);
			// create a database connection
			conn = DriverManager.getConnection(sDbUrl,sUserName,sPassWord);
			stmt = conn.createStatement();
			
			/*
			 * try { stmt.setQueryTimeout(iTimeout); stmt.executeUpdate(
			 * sMakeTable ); stmt.executeUpdate( sMakeInsert ); ResultSet rs =
			 * stmt.executeQuery(sMakeSelect); try { while(rs.next()) { String
			 * sResult = rs.getString("response"); System.out.println(sResult);
			 * } } finally { try { rs.close(); } catch (Exception ignore) {} } }
			 * finally { try { stmt.close(); } catch (Exception ignore) {} }
			 */
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver Not Found: " + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("Error Connecting database: " + sqle.toString());
		}
		/*
		 * finally { try { conn.close(); } catch (Exception ignore) {} }
		 */
	}

	// ***********************
	// Public Methods
	// ***********************

	/*
	 ** Returns ResultSet Object obtianed from executing query
	 **
	 ** @param strSql Query String to execute
	 ** 
	 * @return ResultSet
	 */
	public ResultSet getRecord(String strSql) throws SQLException {
		return stmt.executeQuery(strSql);
	}

	/*
	 ** Execute modification query and returns true if success, false if failure
	 **
	 ** @param strSql Query String to upadate/insert/delete
	 ** 
	 * @return boolean, state of success
	 */
	public boolean doQuery(String strSql) throws SQLException {
		return stmt.executeUpdate(strSql) > 0;
	}
	
	public PreparedStatement getPreparStamt(String sql)
	{
		try {
			prpstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prpstmt;
	}

	/*
	 ** close Sataement & Connection cleanup code
	 **
	 */
	public void close() throws SQLException {
		stmt.close();
		conn.close();
		conn = null;
		stmt = null;
		
	}
	
	public String readProperties(String filename,String propName)
	{
		String value="";
		try (InputStream in = new FileInputStream(filename)) {
			Properties prop = new Properties();
			prop.load(in);
			
			value = prop.getProperty(propName);
			//System.out.println(value);
			/*for (String property : prop.stringPropertyNames()) {
				String value = prop.getProperty(property);
				System.out.println(property + "=" + value);
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return value;
	}
	public Connection getConnection() {
		return conn;
	}
}
