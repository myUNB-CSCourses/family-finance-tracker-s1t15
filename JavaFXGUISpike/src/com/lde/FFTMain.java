package com.lde;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FFTMain extends Application {

    public void start(Stage stage) {
        
        Label message = new Label("First FX Application!");
        message.setFont( new Font(40) );
        
        Label myMessage = new Label("");
        myMessage.setFont( new Font(8));
        myMessage.setAlignment(Pos.TOP_LEFT);
        
        Button helloButton = new Button("Say Hello");
        helloButton.setOnAction( evt -> message.setText("Hello World!") );
        Button goodbyeButton = new Button("Say Goodbye");
        goodbyeButton.setOnAction( evt -> message.setText("Goodbye!!") );
        Button quitButton = new Button("Quit");
        quitButton.setOnAction( evt -> Platform.exit() );
        
        Button myButton = new Button("Look! It is a button");
        myButton.setAlignment(Pos.TOP_RIGHT);
        myButton.setOnAction( evt -> myMessage.setText("This is what it does!"));

        HBox buttonBar = new HBox( 20, helloButton, goodbyeButton, quitButton );
        buttonBar.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setCenter(message);
        root.setRight(myMessage);
        root.setTop(myButton);
        root.setBottom(buttonBar);
        
        Scene scene = new Scene(root, 450, 200);
        stage.setScene(scene);
        stage.setTitle("JavaFX Test");
        stage.show();
        
    } // end start();
    
    public static void main(String[] args) {
        launch();  // Run this Application.
    }

} // end class HelloWorldFX
