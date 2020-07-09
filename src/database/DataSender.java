package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class DataSender 
{

	public static void issueBook(Connection connection,int bookNumber, int studentNumber)
	{
		 Date date = new Date();
		 
		 @SuppressWarnings("deprecation")
		java.sql.Date sqldate = new java.sql.Date(date.getYear(), date.getMonth(), date.getYear());
	    
	    
	    final String ISSUE_QUERY = "INSERT INTO issue(book_id, student_id, issueDate)"
	    						  + "VALUES(" + bookNumber +", "  
	    		                  + studentNumber +", "  
	    		                  + "\'" + sqldate  + "\'" +  ");";
	    
	    final String UPDATE_BOOKS_QUERY = "UPDATE books "
	    		                        + "SET isIssued = 1 "
	    		                        + "WHERE bookNumber = " + bookNumber + " ;";
	    
	    try {
	    	
			Statement EXECUTE_ISSUE = connection.createStatement();
			EXECUTE_ISSUE.execute(ISSUE_QUERY);
			
			Statement EXECUTE_UPDATE = connection.createStatement();
			EXECUTE_UPDATE.execute(UPDATE_BOOKS_QUERY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	public static void returnBook(Connection connection, ToggleGroup group) throws SQLException
	{
		RadioButton selectedRadioButton =  (RadioButton) group.getSelectedToggle();
		String bookName = selectedRadioButton.getText();
		int bookID = DataReciever.getBookIDbyName(bookName, connection);
		
		Date date = new Date();
		 
		 @SuppressWarnings("deprecation")
		java.sql.Date sqldate = new java.sql.Date(date.getYear(), date.getMonth(), date.getYear());
		
		final String QUERY = "UPDATE issue "
				           + "SET returnDate = \'" + sqldate + "\' "
				           + "WHERE book_id = " + bookID +
				            " AND returnDate = null";
		
		Statement statement = connection.createStatement();
		
		statement.execute(QUERY);
		
		String QUERY_2 = "UPDATE books "
				        + "SET isIssued = 0 "
				        + "WHERE bookNumber = " + bookID;
		
		statement.execute(QUERY_2);
	}
	
}
