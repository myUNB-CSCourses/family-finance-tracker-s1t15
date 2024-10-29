package com.lde;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FFTMain extends Application {
	
	String welcomeMessage = "Family Financial Tracker";
	String buttonMessage1 = "Use Case 1";
	String buttonMessage2 = "Use Case 2";
	String buttonMessage3 = "Use Case 3";
	String radialStyle = "custom-pane";
	EventHandler<ActionEvent> event1 = evt -> System.out.println("Hey, stop pressing me!");
	EventHandler<ActionEvent> event2 = evt -> System.out.println("That was rude!");
	EventHandler<ActionEvent> event3 = evt -> System.out.println("That was sweet!");

    public void start(Stage rootWindow) {
    
    	TBPane myPane = new TBPane(welcomeMessage, radialStyle, buttonMessage1, event1,
    								buttonMessage2, event2,
    								buttonMessage3, event3);
    	
    	Scene scene = new Scene(myPane, 500, 500);
    	scene.getStylesheets().add(getClass().getResource("/com/lde/styles.css").toExternalForm());
    	rootWindow.setScene(scene);
    	rootWindow.setTitle("This has been a test of our emergency broadcast service");
    	rootWindow.show();
    
    } // end start();
    
    public static void main(String[] args) {
        launch(); 
    }

}
