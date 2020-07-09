package windows;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DialogWindow {
	
	private Stage window;
	private HBox hbox;
	private BorderPane pane;
	private Label label;
	private Button okButton;
	
	public DialogWindow(String text, String title)
	{
		label = new Label(text);
		okButton = new Button("Ok");
		pane = new BorderPane();
		hbox = new HBox();
		window = new Stage();
		hbox.getChildren().add(okButton);
		
		pane.setPadding(new Insets(8,8,8,8));
		
		pane.setBottom(okButton);
		pane.setTop(label);
		
		hbox.getChildren().add(label);
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() 
		{

			@Override
			public void handle(MouseEvent event) {
				
				window.close();
			}
			
		};
		
		okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		
		
		Scene scene = new Scene(pane,100,50);
		
		window.setScene(scene);
		window.show();
		window.setTitle(title);
		
	}
	
	public DialogWindow(String text)
	{
		this(text, "Dialog Box");
	}

}
