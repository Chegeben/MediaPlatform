package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


public class BencheTest extends Application {
	
	public void start(Stage Benche) {
		
		try {	
		Parent root = FXMLLoader.load(getClass().getResource("Media.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Benche.setScene(scene);
		Benche.getIcons().add( new Image(getClass().getResourceAsStream("icons8-instagram-24.png")));
		Benche.setTitle("KARATINA UNI");
		Benche.setResizable(false);
		Benche.setOnCloseRequest(new EventHandler<WindowEvent>(){
			
			public void handle(WindowEvent event){
				
				Platform.exit();
				System.exit(0);
			}
		});
		}catch(Exception args) {
			args.printStackTrace();
		}
	}
		
		}
	



