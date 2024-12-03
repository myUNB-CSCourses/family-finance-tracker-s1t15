package addBuyer.uju;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private ListClass buyerList;

    public void start(Stage primaryStage) {
        buyerList = new ListClass(); // Initialize the list for buyers

        Button addBuyerButton = new Button("Add Buyer");
        addBuyerButton.setOnAction(e -> openAddBuyerWindow());

        VBox layout = new VBox(10, addBuyerButton);
        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setTitle("Family Financial Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openAddBuyerWindow() {
        Stage addBuyerStage = new Stage();
        AddBuyerWindow addBuyerWindow = new AddBuyerWindow(buyerList, addBuyerStage);
        addBuyerWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

