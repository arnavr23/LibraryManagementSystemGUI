package windows;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogWindow {
	
	private Stage window;
	private VBox vbox;
	private BorderPane pane;
	private Label label;
	private Button okButton;
	
	public DialogWindow(String text, String title)
	{
		label = new Label(text);
		okButton = new Button("Ok");
		pane = new BorderPane();
		vbox = new VBox();
		vbox.setPadding(new Insets(5));
		vbox.setSpacing(2);
		window = new Stage();
		window.setTitle(title);
		vbox.getChildren().add(label);
		vbox.getChildren().add(okButton);
		
		pane.setPadding(new Insets(8,8,8,8));
		

		pane.setCenter(vbox);
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() 
		{

			@Override
			public void handle(MouseEvent event) {
				
				window.close();
			}
			
		};
		
		okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		
		
		Scene scene = new Scene(pane,200,50);
		
		window.setScene(scene);
		window.show();
		
	}
	
	public DialogWindow(String text)
	{
		this(text, "Dialog Box");
	}

}
