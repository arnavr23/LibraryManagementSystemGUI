package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	// URL to connect to the database
	final static private String URL = "jdbc:mysql://127.0.0.1:3306/java";
	
	public static Connection connect() throws SQLException
	{
		Connection connection = DriverManager.getConnection(URL, "root", "1234");
			return connection;
		
	}
	

}
