package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaController implements Initializable {
	
	private Media media;
	private MediaPlayer mediaPlayer;
	
	@FXML
	private Button playButton, pauseButton, resetButton, nextButton, previousButton;
	@FXML
	private ProgressBar progress;
	@FXML
	private Slider volume;
	@FXML
	private Label songTitle, time;
	@FXML
	private ComboBox<String> speedBox;
	
	
	
	double end ;
	double end2 ;
	private File directory;
	private File [] files;
	
	private ArrayList<File> songs;
	
	private int songNumber;
	
	private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250};
	
	
	
	private Timer timer;
	
	private TimerTask task;
	
	
	
	private boolean running;
	

	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		songs = new ArrayList<File>();
		directory = new File("music");
		files = directory.listFiles();
		
		
		if(files !=null) {
			for(File file: files ) {
				songs.add(file);
			}
		}
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer= new MediaPlayer(media);
		songTitle.setText(songs.get(songNumber).getName() );
		
		
		
		for(int i =0; i< speeds.length; i++) {
			speedBox.getItems().addAll(Integer.toString(speeds[i])+"%");
		}
		speedBox.setOnAction(this::changespeed);
		
		volume.valueProperty().addListener(new ChangeListener<Number>() {

			
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				mediaPlayer.setVolume(volume.getValue()* 0.01);
				
			}
			
		});
		progress.setStyle("-fx-accent:red");
		
	}
	public void play() {
		beginTimer();
		changespeed(null);
		mediaPlayer.setVolume(volume.getValue()* 0.01);
		mediaPlayer.play();
	}
    public void pause() {
    	cancelTimer();
    	mediaPlayer.pause();
	}
    
    
    public void reset() {
		
    	progress.setProgress(0);
    	mediaPlayer.seek(Duration.seconds(0));
	}
    public void next() {
		
    	if(songNumber < songs.size()-1) {
    		songNumber ++;
    		mediaPlayer.stop();
    		if(running) {
    			cancelTimer();
    		}
    		media = new Media(songs.get(songNumber).toURI().toString());
    		mediaPlayer= new MediaPlayer(media);
    		songTitle.setText(songs.get(songNumber).getName() );
    		
    		play();
    		
    	}
    	else {
    		
    		songNumber = 0;
    		mediaPlayer.stop();
    		if(running) {
    			cancelTimer();
    		}
    		media = new Media(songs.get(songNumber).toURI().toString());
    		mediaPlayer= new MediaPlayer(media);
    		songTitle.setText(songs.get(songNumber).getName() );
    		
    		play();
    	}
	}
    public void previous() {
		
    	if(songNumber > 0) {
    		songNumber ++;
    		mediaPlayer.stop();
    		if(running) {
    			cancelTimer();
    		}
    		media = new Media(songs.get(songNumber).toURI().toString());
    		mediaPlayer= new MediaPlayer(media);
    		songTitle.setText(songs.get(songNumber).getName() );
    		
    		play();
    		
    	}
    	else {
    		
    		songNumber = songs.size()-1;
    		mediaPlayer.stop();
    		if(running) {
    			cancelTimer();
    		}
    		media = new Media(songs.get(songNumber).toURI().toString());
    		mediaPlayer= new MediaPlayer(media);
    		songTitle.setText(songs.get(songNumber).getName() +  end);
    	
    		play();
    	}
   	}
    public void changespeed(ActionEvent event) {
		
    	if(speedBox.getValue()== null) {
    		mediaPlayer.setRate(1);
    	}
    	else {
    		mediaPlayer.setRate(Integer.parseInt(speedBox.getValue().substring(0,speedBox.getValue().length()-1))* 0.01);
    	}
    	
   	} 
    public void beginTimer() {
    	timer = new Timer();
    	task = new TimerTask() {

		
			public void run() {
				
				running = true;
				double current = mediaPlayer.getCurrentTime().toSeconds();
				 end = media.getDuration().toSeconds();
				 end2 = media.getDuration().toSeconds()-current;
				progress.setProgress(current/ end2);
				

				
				
				if(current/ end2 == 1) {
					cancelTimer();
				}
			}
    		
    	};
    	
    	timer.scheduleAtFixedRate(task, 0, 1000);
    }
   public void cancelTimer() {
    	running = false;
    	timer.cancel();
    }
	
    
    
	
	
}
