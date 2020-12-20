package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USER = "scott";
	private final String PASSWORD = "1234";
	
	Connection conn = null;
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}


}
