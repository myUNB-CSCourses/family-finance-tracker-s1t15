//Contributing Author: Lloyd (Lionel 053)
package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       
        Scene scene = new Scene(new TagViewModel().getView(), 650, 620);
        scene.getStylesheets().add(getClass().getResource("TagViewTheme.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Family Financial Tracker");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
