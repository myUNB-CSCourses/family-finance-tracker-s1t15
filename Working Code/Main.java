package tagView.uju;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize TagViewModel and get the UI view
            TagViewModel tagViewModel = new TagViewModel();
            Scene scene = new Scene(tagViewModel.getView(), 650, 620);

            // Load CSS
            String stylesheet = getClass().getResource("TagView.css").toExternalForm();
            if (stylesheet != null) {
                scene.getStylesheets().add(stylesheet);
            } 
            
            else {
                System.err.println("CSS file not found. Skipping styles.");
            }

            // Configure stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Family Financial Tracker");
            primaryStage.setResizable(false);
            primaryStage.show();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initializing the application: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
