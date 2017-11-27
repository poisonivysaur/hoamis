package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database{
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hoamis";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";

	public static Connection getDBConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
			
			return conn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
