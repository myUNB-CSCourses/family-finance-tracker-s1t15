package com.lde;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FFTMain extends Application {
	
	Scene testCase, scene1, scene2, scene3;

    public void start(Stage rootWindow) {
        
        //create four panes, on the first pane (initially shown, will contain three buttons that will 
    	//take you to another pane. Each of these panes has a "complete" button that takes you back to 
    	//main pane.
    	
    	StackPane welcomeScreen = new StackPane();
    	Label welcomeMessage = new Label("Family Financial Tracker");
    	welcomeMessage.setAlignment(Pos.CENTER);
    	
    	HBox welcomeButtonBar = new HBox();
    	welcomeButtonBar.setAlignment(Pos.BOTTOM_CENTER);
    	
    	Button goToUseCase1 = new Button("Use Case 1");
    	Button goToUseCase2 = new Button("Use Case 2"); 
    	Button goToUseCase3 = new Button("Use Case 3");
    	
    	goToUseCase1.setOnAction(evt -> rootWindow.setScene(scene1));
    	goToUseCase2.setOnAction(evt -> rootWindow.setScene(scene2));
    	goToUseCase3.setOnAction(evt -> rootWindow.setScene(scene3));
    	
    	welcomeButtonBar.getChildren().addAll(goToUseCase1, goToUseCase2, goToUseCase3);
    	welcomeScreen.getChildren().addAll(welcomeButtonBar, welcomeMessage);
    	
    	Scene testCase = new Scene(welcomeScreen, 400, 400);
    	
    	///////////////////////////////////////////////////////////////////////////////
    	
    	StackPane useCase1 = new StackPane();
    	Label welcomeMessageUseCase1 = new Label("This is where you process use case 1");
    	welcomeMessageUseCase1.setAlignment(Pos.CENTER);
    	
    	HBox useCase1ButtonBar = new HBox();
    	useCase1ButtonBar.setAlignment(Pos.BOTTOM_CENTER);
    	
    	Button goToWelcomeScreen1 = new Button("Go back to Welcome Screen");
    	Button processUseCase1 = new Button("Click me to process use case 1");
    	
    	goToWelcomeScreen1.setOnAction(evt -> rootWindow.setScene(testCase));
    	processUseCase1.setOnAction(evt -> System.out.println("I have been processed, thanks"));
    	
    	useCase1ButtonBar.getChildren().addAll(goToWelcomeScreen1, processUseCase1);
    	
    	useCase1.getChildren().addAll(welcomeMessageUseCase1, useCase1ButtonBar);
    	
    	scene1 = new Scene(useCase1, 400, 400);
    	
    	//////////////////////////////////////////////////////////////////////////////
    	
    	StackPane useCase2 = new StackPane();
    	Label welcomeMessageUseCase2 = new Label("This is where you process use case 1");
    	welcomeMessageUseCase2.setAlignment(Pos.CENTER);
    	
    	HBox useCase2ButtonBar = new HBox();
    	useCase2ButtonBar.setAlignment(Pos.BOTTOM_CENTER);
    	
    	Button goToWelcomeScreen2 = new Button("Go back to Welcome Screen");
    	Button processUseCase2 = new Button("Click me to process use case 2");
    
    	goToWelcomeScreen2.setOnAction(evt -> rootWindow.setScene(testCase));
    	processUseCase2.setOnAction(evt -> System.out.println("I have been processed, thanks"));
    	
    	useCase2ButtonBar.getChildren().addAll(goToWelcomeScreen2, processUseCase2);
    	
    	useCase2.getChildren().addAll(welcomeMessageUseCase2, useCase2ButtonBar);
    	
    	scene2 = new Scene(useCase2, 400, 400);
    	
    	//////////////////////////////////////////////////////////////////////////////
    	
    	StackPane useCase3 = new StackPane();
    	Label welcomeMessageUseCase3 = new Label("This is where you process use case 3");
    	welcomeMessageUseCase3.setAlignment(Pos.CENTER);
    	
    	HBox useCase3ButtonBar = new HBox();
    	useCase3ButtonBar.setAlignment(Pos.BOTTOM_CENTER);
    	
    	Button goToWelcomeScreen3 = new Button("Go back to Welcome Screen");
    	Button processUseCase3 = new Button("Click me to process use case 3");
    	
    	goToWelcomeScreen3.setOnAction(evt -> rootWindow.setScene(testCase));
    	processUseCase3.setOnAction(evt -> System.out.println("I have been processed, thanks"));
    	
    	useCase3ButtonBar.getChildren().addAll(goToWelcomeScreen3, processUseCase3);
    	
    	useCase3.getChildren().addAll(welcomeMessageUseCase3, useCase3ButtonBar);
    	
    	scene3 = new Scene(useCase3, 400, 400);
    	
    	//////////////////////////////////////////////////////////////////////////////
    	
    	rootWindow.setScene(testCase);
    	rootWindow.setTitle("This is a start!");
    	rootWindow.show();
        
    } // end start();
    
    public static void main(String[] args) {
        launch();  // Run this Application.
    }

} // end class HelloWorldFX
