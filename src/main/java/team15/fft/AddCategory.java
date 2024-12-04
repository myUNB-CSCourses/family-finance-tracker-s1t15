//package group15.tot;
package team15.fft;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddCategory extends Application {
    private ObservableList<String> categories;
    private ListView<String> categoryListView;

    public AddCategory() {
        // Initialize the categories list so it can be used in tests
        categories = FXCollections.observableArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        categoryListView = new ListView<>(categories);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Top Panel with TextField and Add Button
        HBox inputPanel = new HBox(10);
        inputPanel.setPadding(new Insets(10));
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Enter category");
        Button addButton = new Button("Add Category");
        inputPanel.getChildren().addAll(categoryTextField, addButton);
        root.setTop(inputPanel);

        // Center Panel with ListView (Category List)
        root.setCenter(categoryListView);

        // Bottom Panel with Delete Button
        HBox buttonPanel = new HBox(10);
        buttonPanel.setPadding(new Insets(10));
        Button deleteButton = new Button("Delete Category");
        buttonPanel.getChildren().add(deleteButton);
        root.setBottom(buttonPanel);

        // Add Category Button Action
        addButton.setOnAction(e -> {
            String category = categoryTextField.getText().trim();
            addCategory(category);
            categoryTextField.clear();
        });

        // Delete Category Button Action
        deleteButton.setOnAction(e -> {
            String selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                categories.remove(selectedCategory);
            }
        });

        // Set up the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Category Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addCategory(String category) {
        if (category != null && !category.isEmpty() && !categories.contains(category)) {
            categories.add(category);
        }
    }

    public ArrayList<String> getCategories() {
        return new ArrayList<>(categories);
    }
}
