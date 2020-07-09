package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.ToggleGroup;

public class DatabaseManager 
{
	
	private Connection connection;
	
	public DatabaseManager() throws SQLException
	{
		connection = DatabaseConnector.connect();
	}
	
	// DataReciever methods
	
	public ResultSet getLibraryBooksData() throws SQLException
	{
		return DataReciever.getLibraryBooksData(connection);
	}
	
	public ResultSet getStudentsData() throws SQLException
	{
		return DataReciever.getStudentsData(connection);
	}
	public int getStudentNumber(String name) throws SQLException
	{
		return DataReciever.getStudentNumber(name, connection);
	}
	
	public int getBookIDbyName(String name) throws SQLException
	{
		return DataReciever.getBookIDbyName(name, connection);
	}
	
	// DataSender methods
	
	public void issueBook(int bookNumber, int studentNumber)
	{
		DataSender.issueBook(connection, bookNumber, studentNumber);
	}
	
	public void returnBook(ToggleGroup group) throws SQLException
	{
		DataSender.returnBook(connection, group);
	}
	
	
	

}
