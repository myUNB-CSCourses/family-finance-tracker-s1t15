package com.lde;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*Creates a window with a border pane. In the center is the first view with some
 * functionality (minimal). You can switch the content in the middle using control
 * buttons found at the bottom. This is simply to exhibit one way to manage views 
 * in this application. It is certainly not the only way.*/
public class FFTMain extends Application {
	
	/*Set up member variables to be used in this demo. Not necessarily set up like this if we choose to manage views this way*/
	private StackPane rootContentPane;

	private String welcomeMessage = "Family Financial Tracker";
	private String buttonMessage1 = "Use Case 1";
	private String buttonMessage2 = "Use Case 2";
	private String buttonMessage3 = "Use Case 3";
	private String radialStyle = "custom-pane";
	private EventHandler<ActionEvent> event1 = evt -> System.out.println("Hey, stop pressing me!");
	private EventHandler<ActionEvent> event2 = evt -> System.out.println("That was rude!");
	private EventHandler<ActionEvent> event3 = evt -> System.out.println("That was sweet!");
	
	private String welcomeMessage2 = "Another view, also ugly, but you could style it to look pretty";
	private String buttonMessage12 = "Use Case 4";
	private String buttonMessage22 = "Use Case 5";
	private String buttonMessage32 = "Use Case 6";
	private String radialStyle2 = "custom-pane";
	private EventHandler<ActionEvent> event12 = evt -> System.out.println("Hey, stop picking on me!");
	private EventHandler<ActionEvent> event22 = evt -> System.out.println("That wasn't very nice!");
	private EventHandler<ActionEvent> event32 = evt -> System.out.println("That's better!");

    public void start(Stage rootWindow) {
    	
    	/*Set up the two views for this demo*/
    	TBPane tbPane1 = new TBPane(welcomeMessage, radialStyle, buttonMessage1, event1,
				buttonMessage2, event2,
				buttonMessage3, event3);
    	
    	TBPane tbPane2 = new TBPane(welcomeMessage2, radialStyle2, buttonMessage12, event12,
    			buttonMessage22, event22,
    			buttonMessage32, event32);
    	
    	/*Create some components to be added to the root content pane that will surround the main content (which will be the two panes mentioned above)*/
    	Menu file = new Menu("File");
    	MenuItem newFile = new MenuItem("New");
    	MenuItem openFile = new MenuItem("Open");
    	MenuItem saveFile = new MenuItem("Save");
    	file.getItems().addAll(newFile, openFile, saveFile);
    	
    	Menu help = new Menu("Help");
    	MenuItem helpIndex = new MenuItem("Index");
    	help.getItems().addAll(helpIndex);
    	
    	MenuBar menuBar = new MenuBar();
    	menuBar.setVisible(true);
    	menuBar.getMenus().addAll(file, help);
    	
    	Button leftButton1 = new Button("This");
    	Button leftButton2 = new Button("does");
    	Button leftButton3 = new Button("something");
    	
    	Button rightButton1 = new Button("It's");
    	Button rightButton2 = new Button("a");
    	Button rightButton3 = new Button("button");
    	
    	VBox leftBox = new VBox();
    	leftBox.getChildren().addAll(leftButton1, leftButton2, leftButton3);
    	leftBox.getStyleClass().add("vbox-style");
    	
    	VBox rightBox = new VBox();
    	rightBox.getChildren().addAll(rightButton1, rightButton2, rightButton3);
    	rightBox.getStyleClass().add("vbox-style");
    	
    	Button bottomButton1 = new Button("Go to view 1");
    	Button bottomButton2 = new Button("Go to view 2");
    	bottomButton1.setOnAction(e -> switchToPane(tbPane1)); 					//created switching logic to take care of switching between content panes 
    	bottomButton2.setOnAction(e -> switchToPane(tbPane2));
    	applyStyleToButtons(leftButton1, leftButton2, leftButton3, rightButton1, 
				rightButton2, rightButton3, bottomButton1, bottomButton2); 		//Using CSS to style the buttons, but I created a method to take care of all buttons at once
    
    	
    	HBox bottomBox = new HBox();
    	bottomBox.getChildren().addAll(bottomButton1, bottomButton2);
    	bottomBox.getStyleClass().add("hbox-style");							//Using CSS to style
    	
    	rootContentPane = new StackPane();										//A parent for all views
    	rootContentPane.getChildren().addAll(tbPane1, tbPane2);
    	tbPane2.setVisible(false);												//ensure only one pane is visible at first
    	
    	BorderPane mainScreen = new BorderPane();								//content is centered while control buttons surround the main content
    	mainScreen.setTop(menuBar);
    	mainScreen.setLeft(leftBox);
    	mainScreen.setRight(rightBox);
    	mainScreen.setBottom(bottomBox);
    	mainScreen.setCenter(rootContentPane);
    	
    	/*Set up the scene and add it to the stage, ensuring that CSS is loaded, and put a stock icon in the root window menu bar*/
    	Scene scene = new Scene(mainScreen, 500, 500);							
    	scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    	Image icon = new Image(getClass().getResourceAsStream("financeIcon.png"));
    	rootWindow.getIcons().add(icon);
    	rootWindow.setScene(scene);
    	rootWindow.setTitle("This is ugly (aesthetically, I am not an artist) but this is a possible way to handle views");
    	rootWindow.show();
    	
    }
    
    private void switchToPane(TBPane tbPane) {
		for (Node pane : rootContentPane.getChildren()) {						//important that only views are loaded into root content pane for this to work correctly 
			pane.setVisible(false);												//or unexpected bugs like disappearing components will occur
		}
		tbPane.setVisible(true);
	}
    
    private void applyStyleToButtons(Button...buttons ) {						//helpful to iteratively add styles to many buttons
    	for (Button button : buttons) {
    		button.getStyleClass().add("custom-button");
    	}
    }
    
    public static void main(String[] args) {
        launch(); 
    }

}
