package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataReciever {

	public static ResultSet getLibraryBooksData(Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		
		final String QUERY = 
				"select b.bookNumber,b.bookName, a.authorName,b.isIssued from books b, authors a " + 
		        "where b.author_id = a.author_id Order BY b.bookNumber;";
		
		return statement.executeQuery(QUERY);
		

	}
	
	public static ResultSet getStudentsData(Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		
		final String QUERY = "SELECT * FROM students";
		
		return statement.executeQuery(QUERY);
	}
	
	public static int getStudentNumber(String name, Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		
		final String QUERY = "SELECT student_id FROM students WHERE studentName = \'" + name + "\';";
		ResultSet resultSet = statement.executeQuery(QUERY);
		
		resultSet.next();
		
		int studentNumber = resultSet.getInt(1);
	
		
		return studentNumber;
		
	}
	
	public static int getBookIDbyName(String name, Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		
		final String QUERY = "SELECT bookNumber FROM books WHERE bookName = \'" + name + "\';";
		
		ResultSet resultSet =  statement.executeQuery(QUERY);
		
		resultSet.next();
		
		return resultSet.getInt(1);
	}
	
}
