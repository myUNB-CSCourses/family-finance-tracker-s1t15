package com.lde;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/*This could be a generic way to create a view that will have a label and some buttons, 
 * you can also pass event handlers and titles for the buttons (and a title for the label too). 
 * This could possibly be extended to create a basic generic view creator object class to avoid 
 * having to code many views as separate classes (just create instance of this type and pass 
 * the parameters to the constructor to create the view you want. This is based on the idea that
 * all views will likely be somewhat similar*/
public class TBPane extends VBox {
	
	private Label welcomeMessage;
	private VBox welcomeButtonBar;
	private Button button1, button2, button3;

	public TBPane ( String backGroundMessage, String style,
					String button1Message, EventHandler<ActionEvent> action1,
					String button2Message, EventHandler<ActionEvent> action2,
					String button3Message, EventHandler<ActionEvent> action3) {
		
		getStyleClass().add(style);
		
		welcomeMessage = new Label(backGroundMessage);
		welcomeMessage.setAlignment(Pos.TOP_CENTER);
		welcomeMessage.getStyleClass().add("custom-label");
		
		button1 = new Button(button1Message);
		button2 = new Button(button2Message);
		button3 = new Button(button3Message);
		
		button1.getStyleClass().add("custom-button");
		button2.getStyleClass().add("custom-button");
		button3.getStyleClass().add("custom-button");
		
		button1.setOnAction(action1);
		button2.setOnAction(action2);
		button3.setOnAction(action3);
		
		welcomeButtonBar = new VBox();
		welcomeButtonBar.getStyleClass().add("custom-button-bar");
		welcomeButtonBar.getChildren().addAll(button1, button2, button3);
		welcomeButtonBar.setAlignment(Pos.BOTTOM_CENTER);
		
		this.getChildren().addAll(welcomeMessage, welcomeButtonBar);
		
	}

}
