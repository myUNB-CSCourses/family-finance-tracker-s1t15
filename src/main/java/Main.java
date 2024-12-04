//Contributing Author: Lloyd Edison (Lionel053), James Ukandu (only lines as annotated), 
package test;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primaryStage;
	private Scene rootScene;
	private Scene tagViewScene;
	private BorderPane rootPane;
	private StackPane tagViewPane;
	private Ledger ledger;
	private String filePath;

    public static void main(String[] args) {
       
        launch(args);
        
    }

    @Override
    public void start(Stage args) {
    	
    	primaryStage = args;
    	
    	makeRootPane();
    	rootScene = new Scene(rootPane, 400, 400);
        rootScene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        
        primaryStage.setScene(rootScene);
        primaryStage.setTitle("Version 2.0");
        primaryStage.show();
        
    }
    
    private void makeRootPane() {
    	
    	rootPane = new BorderPane();
    	MenuBar menuBar = new MenuBar();
    	Menu menu = new Menu("File");
    	MenuItem browseButton = new MenuItem("Browse");
    	browseButton.setOnAction(evt -> openTagView());
    	
    	menu.getItems().addAll(browseButton);
    	menuBar.getMenus().add(menu);
    	rootPane.setTop(menuBar);
    	
    }

	private void openTagView() {

		filePath = openDialogBox();
		
		Stage tagViewStage = new Stage();
		tagViewStage.initModality(Modality.APPLICATION_MODAL);
		tagViewStage.initOwner(primaryStage);
		
		makeTagViewPane(filePath);
		tagViewScene = new Scene(tagViewPane, 700, 700);
		tagViewScene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
		
		tagViewStage.setScene(tagViewScene);
		tagViewStage.setTitle("Tag Transactions");
		tagViewStage.show();
		
	}

	private void makeTagViewPane(String filePath) {
    	
    	tagViewPane = new StackPane();
        ledger = new Ledger(filePath);
        tagViewPane.getChildren().add(new TagViewController(ledger).getView());
    	
    }
    
    private String openDialogBox() {
		
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		
		if (selectedFile != null) {
			
			String filePath = selectedFile.getAbsolutePath();
			return filePath;
			
		} else { 
			
			return null;
			
		}
		
	}
    
}