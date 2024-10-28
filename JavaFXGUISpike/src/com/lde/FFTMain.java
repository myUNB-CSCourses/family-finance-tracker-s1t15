package com.lde;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FFTMain extends Application {

    public void start(Stage rootWindow) {
        
        //create four panes, on the first pane (initially shown, will contain three buttons that will 
    	//take you to another pane. Each of these panes has a "complete" button that takes you back to 
    	//main pane.
    	
    	StackPane screen = new StackPane();
    	HBox buttonBar = new HBox();
    	Button processButton = new Button("Process something"); //once completed, will display message to indicate success or failure
    	Button backButton = new Button("Cancel"); //takes you back to main pane
    	processButton.setOnAction(evt -> System.out.println("Stop pressing that!"));
    	backButton.setOnAction(evt -> System.out.println("It's not doing anything"));
    	buttonBar.getChildren().addAll(processButton, backButton);
    	screen.getChildren().add(buttonBar);
    	Scene userStory = new Scene(screen, 400, 400);
    	
    	rootWindow.setScene(userStory);
    	rootWindow.setTitle("This is a start!");
    	rootWindow.show();
        
    } // end start();
    
    public static void main(String[] args) {
        launch();  // Run this Application.
    }

} // end class HelloWorldFX
