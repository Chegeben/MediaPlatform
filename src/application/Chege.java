package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Chege extends Application{

	
	public void start(Stage chegeStage) throws Exception {
		
		try {
		
		Parent root = FXMLLoader.load(getClass().getResource("Media.fxml"));
		Scene scene = new Scene(root);
		chegeStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		chegeStage.setTitle("CHEGE PLAY");
		chegeStage.getIcons().add(new Image(getClass().getResourceAsStream("icons8-internet-24.png")));
		chegeStage.show();
		
		chegeStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			public void handle(WindowEvent arg0) {
			Platform.exit();
			System.exit(0);
			}

			
			
		});
		
		
	} catch(Exception e) {
		e.printStackTrace();
		}
	}

	public static void main (String args) {
		launch(args);
	}
	
}
