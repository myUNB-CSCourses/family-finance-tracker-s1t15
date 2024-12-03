package addBuyer.uju;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBuyerWindow {
    private ListClass buyerList;
    private Stage stage;

    public AddBuyerWindow(ListClass buyerList, Stage stage) {
        this.buyerList = buyerList;
        this.stage = stage;
    }

    public void show() {
        VBox layout = new VBox(10);

        Label nameLabel = new Label("Enter Buyer Name:");
        TextField nameField = new TextField();
        Label errorLabel = new Label(); // Label for error messages
        errorLabel.setStyle("-fx-text-fill: red;");

        Button saveButton = new Button("Save Buyer");

        saveButton.setOnAction(e -> {
            String buyerName = nameField.getText().trim();
            if (buyerName.isEmpty()) {
                errorLabel.setText("Name cannot be empty!");
            } else if (buyerList.containsItem(buyerName)) {
                errorLabel.setText("Error: Buyer already exists!");
            } else {
                buyerList.addItem(buyerName);
                stage.close();
            }
        });

        layout.getChildren().addAll(nameLabel, nameField, errorLabel, saveButton);
        Scene scene = new Scene(layout, 300, 200);

        stage.setTitle("Add Buyer");
        stage.setScene(scene);
        stage.show();
    }
}