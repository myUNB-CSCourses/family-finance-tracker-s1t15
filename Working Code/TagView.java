package tagView.uju;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class TagView implements Builder<Region> {
    private final ObservableList<Transaction> ledger;
    private final ObjectProperty<Transaction> currentTransaction;
    private final StringProperty buyerChoice;
    private final StringProperty categoryChoice;
    private final Runnable addButtonAction;
    private final Runnable writeTransactionButton;
    private final ArrayList<String> buyerList;
    private final ArrayList<String> categoryList;

    public TagView(ObservableList<Transaction> ledger, Runnable add, Runnable write,
                   ArrayList<String> buyerList, ArrayList<String> categoryList,
                   ObjectProperty<Transaction> currentTransaction, StringProperty buyerChoice,
                   StringProperty categoryChoice) {
        this.ledger = ledger;
        this.addButtonAction = add;
        this.writeTransactionButton = write;
        this.buyerList = buyerList;
        this.categoryList = categoryList;
        this.currentTransaction = currentTransaction;
        this.buyerChoice = buyerChoice;
        this.categoryChoice = categoryChoice;
    }

    @Override
    public Region build() {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(15));
        mainLayout.getChildren().addAll(buttonBox(), inputBox(), setTableView(ledger));
        return mainLayout;
    }

    private Node buttonBox() {
        HBox transactionControls = new HBox(10);
        transactionControls.getChildren().addAll(getButton("Add", addButtonAction),
                                                 getButton("Write", writeTransactionButton));
        return transactionControls;
    }

    private Node getButton(String label, Runnable action) {
        Button button = new Button(label);
        if (action != null) {
            button.setOnAction(evt -> action.run());
        } else {
            System.err.println("Action not set for button: " + label);
        }
        return button;
    }

    private Node inputBox() {
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(getLabel("date"), getLabel("amount"), getLabel("merchant"),
                                  getComboBox("Select Buyer", buyerList, buyerChoice),
                                  getComboBox("Select Category", categoryList, categoryChoice));
        return vBox;
    }

    private Node getComboBox(String promptText, ArrayList<String> list, StringProperty choice) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(promptText);
        comboBox.getItems().addAll(list);
        comboBox.setOnAction(evt -> choice.set(comboBox.getValue()));
        return comboBox;
    }

    private Node getLabel(String str) {
        Label label = new Label();

        StringBinding binder = new StringBinding() {
            {
                bind(currentTransaction);
            }

            @Override
            protected String computeValue() {
                Transaction dummy = currentTransaction.get();
                if (dummy == null) return "";

                switch (str) {
                    case "date":
                        return dummy.getFormattedDate();
                    case "amount":
                        return dummy.getFormattedAmount();
                    case "merchant":
                        return dummy.getMerchant();
                    default:
                        return "";
                }
            }
        };

        label.textProperty().bind(binder);
        return label;
    }


    private TableView<Transaction> setTableView(ObservableList<Transaction> ledger) {
        TableView<Transaction> tableView = new TableView<>();
        tableView.setItems(ledger); // Bind the ObservableList to the table view

        // Define columns and map them to Transaction properties
        TableColumn<Transaction, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Transaction, String> merchantColumn = new TableColumn<>("Merchant");
        merchantColumn.setCellValueFactory(new PropertyValueFactory<>("merchant"));

        TableColumn<Transaction, String> buyerColumn = new TableColumn<>("Buyer");
        buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyerId"));

        TableColumn<Transaction, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Add all columns to the table
        tableView.getColumns().addAll(dateColumn, amountColumn, merchantColumn, buyerColumn, categoryColumn);

        return tableView;
    }

}
