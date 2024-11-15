package tagView.uju;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TagView tagBuilder = new TagView();
        Scene scene = new Scene(tagBuilder.buildTagView(), 400, 620);
        
        scene.getStylesheets().add(getClass().getResource("tagView.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Family Financial Tracker");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
