package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class BenChe extends Application{
	
	
		public void start(Stage Benche) throws Exception {
			
			try {
				
				Parent root = FXMLLoader.load(getClass().getResource("Media.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Benche.setScene(scene);
				Benche.getIcons().add(new Image(getClass().getResourceAsStream("icons8-internet-24.png")));
				Benche.setTitle("BenChe on the deck");
				Benche.setResizable(false);
				Benche.show();
				Benche.setOnCloseRequest(new EventHandler<WindowEvent>() {
					
					public void handle(WindowEvent arg0) {
						Platform.exit();
						System.exit(0);
					}
				});
				
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}

		public static void main(String[] runOut) {
			
			launch(runOut);
		}
}


