package main;

import java.sql.ResultSet;
import database.DatabaseManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{


	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setGridLinesVisible(false);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		DatabaseManager databaseManager = new DatabaseManager();
		EventHandlers eventHandlers = new EventHandlers(databaseManager);
		ResultSet resultSet = databaseManager.getLibraryBooksData();
		
		ToggleGroup group = new ToggleGroup();
		
		int noOfRows = 0;
		
		while(resultSet.next())
		{
			int rowNo = resultSet.getRow() - 1;
			
			RadioButton radio = new RadioButton();
			radio.setText(resultSet.getString(2));
			
			Label authorName = new Label(resultSet.getString(3));
			
			if(resultSet.getInt(4) != 0)
			{
				radio.setTextFill(Color.GRAY);
				authorName.setTextFill(Color.GREY);
			}
				
			gridPane.add(radio, 0, rowNo);
			gridPane.add(authorName, 1, rowNo);
			
			radio.setToggleGroup(group);
			
			noOfRows++;
		}
		
		EventHandler<MouseEvent> nextButtonEventHandler = eventHandlers.
												getNextButtonEventHandler(gridPane, group);
		
		EventHandler<MouseEvent> returnEventHandler = eventHandlers.
				                                getReturnBookEventHandler(gridPane, group);
		
		
		Button next = new Button("Next");
		gridPane.add(next, 2, noOfRows);
		next.addEventHandler(MouseEvent.MOUSE_CLICKED, nextButtonEventHandler);
		
		Button returnButton = new Button("Return");
		gridPane.add(returnButton, 1, noOfRows);
		returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, returnEventHandler);
		
		
		Scene scene = new Scene(gridPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	

	
		
}
