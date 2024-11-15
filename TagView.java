package tagView.uju;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public class TagView {

    public Pane buildTagView() {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(15));

        // Navigation Buttons
        HBox navigation = new HBox(10);
        navigation.setAlignment(Pos.CENTER);
        Button prevTransaction = new Button("Previous Transaction");
        Button nextTransaction = new Button("Next Transaction");
        navigation.getChildren().addAll(prevTransaction, nextTransaction);

        // Transaction Controls
        HBox transactionControls = new HBox(10);
        transactionControls.setAlignment(Pos.CENTER);
        Button saveTransaction = new Button("Save Transaction");
        Button writeLedger = new Button("Write Ledger");
        Button reviewLedger = new Button("Review Ledger");
        transactionControls.getChildren().addAll(saveTransaction, writeLedger, reviewLedger);

        // Transaction Details
        DatePicker datePicker = new DatePicker();
        TextField merchantField = new TextField();
        merchantField.setPromptText("Merchant");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        TextField buyerField = new TextField();
        buyerField.setPromptText("Buyer");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Groceries", "Rent", "Entertainment", "Utilities");

        TextArea notesArea = new TextArea();
        notesArea.setPromptText("Additional notes...");

        // Adding elements to the main layout
        mainLayout.getChildren().addAll(
                navigation,
                new Label("Transaction Date:"), datePicker,
                new Label("Merchant:"), merchantField,
                new Label("Amount:"), amountField,
                new Label("Buyer:"), buyerField,
                new Label("Category:"), categoryBox,
                new Label("Notes:"), notesArea,
                transactionControls
        );

        return mainLayout;
    }
}
