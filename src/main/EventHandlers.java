package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseManager;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import windows.DialogWindow;

public class EventHandlers {
	
	private DatabaseManager databaseManager;
	
	public EventHandlers(DatabaseManager databaseManager)
	{
		this.databaseManager = databaseManager;
	}
	
	
	public EventHandler<MouseEvent> getIssueEventHandler(int bookNumberToBeIssued, ToggleGroup students,
														 Label author, RadioButton selectedButton) 
	{
		
		EventHandler<MouseEvent> issueEvent = new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				
				try {
					// TODO Auto-generated method stub
					RadioButton selectedStudentRadio = (RadioButton) students.getSelectedToggle();
					String studentName = selectedStudentRadio.getText();
					int studentID = databaseManager.getStudentNumber(studentName);
					
					databaseManager.issueBook(bookNumberToBeIssued, studentID);
					
					author.setTextFill(Color.GREY);
					selectedButton.setTextFill(Color.GREY);
					
					@SuppressWarnings("unused")
					DialogWindow dw = new DialogWindow("Book was issued successfully"," Confirmation Dialog Box");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		};
		
		return issueEvent;
		
	}
	
	public EventHandler<MouseEvent> getNextButtonEventHandler(GridPane gridPane, ToggleGroup group)
	{
		EventHandler<MouseEvent> nextButtonEventHandler = new EventHandler<MouseEvent>() {
			
				@Override
				public void handle(MouseEvent event) {
					Stage studentsWindow = new Stage();
					
				RadioButton selectedButton =(RadioButton) group.getSelectedToggle();
				Label LabelAuth = (Label) getNodeByRowColumnIndex(group.getToggles().lastIndexOf(selectedButton), 1, gridPane);
				
				
				
				if(LabelAuth.getTextFill() == Color.GREY && selectedButton.getTextFill() == Color.GREY)
					return;
				

				
				try {
					GridPane pane2 = new GridPane();
					pane2.setAlignment(Pos.CENTER);
					pane2.setPadding(new Insets(8,8,8,8));
					pane2.setVgap(8);
					pane2.setHgap(8);
					
					ResultSet result = databaseManager.getStudentsData();
					
					String bookName = ((RadioButton) group.getSelectedToggle()).getText();
					int bookNumberToBeIssued = databaseManager.getBookIDbyName(bookName);
					
					ToggleGroup students = new ToggleGroup();
					
					int noOfRows = 0;
					
					while(result.next())
					{
						int r = result.getRow() - 1;
						noOfRows++;
						
						RadioButton rad = new RadioButton(result.getString(2));
						rad.setToggleGroup(students);
						
						pane2.add(rad,0,r );
					}
					
					Button issue = new Button("Issue");
					Button cancel = new Button("Cancel");
					
					pane2.add(issue, 2, noOfRows);
					pane2.add(cancel, 1, noOfRows);
					
					EventHandler<MouseEvent> issueEvent = getIssueEventHandler(bookNumberToBeIssued, students, 
							                                         LabelAuth, selectedButton);
					
					EventHandler<MouseEvent> cancelEvent = getCancelEventHandler(studentsWindow);
					
					issue.addEventHandler(MouseEvent.MOUSE_CLICKED, issueEvent);
					cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, cancelEvent);
					
					Scene scene2 = new Scene(pane2);
					
					
					studentsWindow.initModality(Modality.APPLICATION_MODAL);
					studentsWindow.setScene(scene2);
					studentsWindow.showAndWait();
					
					
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
			}
		};
		
		return nextButtonEventHandler;
	}
	
	// Not an Event Handler
	private Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
	
	public EventHandler<MouseEvent> getCancelEventHandler(Stage stage) 
	{
		EventHandler<MouseEvent> cancelEventHandler = new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				stage.close();
				
			}
		};
		
		return cancelEventHandler;
	}
	
	public EventHandler<MouseEvent> getReturnBookEventHandler(GridPane gridPane,ToggleGroup group)
	{
		EventHandler<MouseEvent> returnBook = new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				RadioButton selectedButton =(RadioButton) group.getSelectedToggle();
				
				Label LabelAuth = (Label) getNodeByRowColumnIndex(group.getToggles().lastIndexOf(selectedButton), 1, gridPane);
				
				if(LabelAuth.getTextFill() == Color.BLACK  && selectedButton.getTextFill() == Color.BLACK)
				{
					return;
				}
				
				try 
				{
					
					databaseManager.returnBook(group);	
					
					LabelAuth.setTextFill(Color.BLACK);
					selectedButton.setTextFill(Color.BLACK);
					
					
				} catch(Exception e) {e.printStackTrace();}
				
				
			}
		};
		
		return returnBook;
	}
}
