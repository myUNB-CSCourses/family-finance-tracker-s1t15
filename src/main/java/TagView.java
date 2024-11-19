//Contributing Authors: Lloyd Edison (Lionel 053), James Ukandu 
//Code snippets were used from James' previous commit (see f4d19f2 for their original code).
package main.java;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	
	ObservableList<Transaction> ledger;
	ObjectProperty<Transaction> currentTransaction;
	ObjectProperty<Transaction> removalChoice;
	
	ArrayList<String> buyerList;
	ArrayList<String> categoryList;
	
	StringProperty buyerChoice;
	StringProperty categoryChoice;
	
	Runnable addButtonAction;
	Runnable writeTransactionButton;
	
	public TagView(ObservableList<Transaction> ledger, Runnable add, Runnable write, 
					ArrayList<String> buyerList, ArrayList<String> categoryList,
					ObjectProperty<Transaction> currentTransaction, StringProperty buyerChoice, 
					StringProperty categoryChoice) {
		
		this.addButtonAction = add;
		this.writeTransactionButton = write;
		this.buyerList = buyerList;
		this.categoryList = categoryList;
		this.buyerChoice = buyerChoice;
		this.categoryChoice = categoryChoice;
		this.ledger = ledger;
		this.currentTransaction = currentTransaction;
		
	}

	@Override
	public Region build() {
		
		VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(15));
        
		mainLayout.getChildren().addAll(buttonBox(),inputBox(), setTableView(ledger));
		return mainLayout;
		
	}
	
	private Node buttonBox() {
		
		HBox transactionControls = new HBox();
		transactionControls.getChildren().addAll( getButton("Add", addButtonAction), 
									getButton("Write", writeTransactionButton));
		return transactionControls;
		
	}

	private Node getButton(String string, Runnable runnable) {
		
		Button button = new Button(string);
		button.setOnAction(evt -> runnable.run());
		return button;
		
	}

	private Node inputBox() {
		
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(getLabel("date"), getLabel("amount"), getLabel("merchant"), 
				getBuyerBox("Select Buyer", buyerList), getCategoryBox("Select Category", categoryList));
		
		return vBox;
		
	}
	
	private Node getCategoryBox(String string, ArrayList<String> list) {
		
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setPromptText(string);
		comboBox.getItems().addAll(list);
		comboBox.setOnAction(evt -> {
			categoryChoice.set(comboBox.getValue());
		});
		return comboBox;
		
	}

	private Node getBuyerBox(String string, ArrayList<String> list) {
		
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setPromptText(string);
		comboBox.getItems().addAll(list);
		comboBox.setOnAction(evt -> {
			buyerChoice.set(comboBox.getValue());
		});
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
				switch (str) {
				case "date":
					return dummy.getFormattedDate();
				case "amount":
					return dummy.getFormattedAmount();
				case "merchant":
					return dummy.getMerchant();
				default:
					return null;
				}
			}
		};
		label.textProperty().bind(binder);
		
		return label;
		
	}
	
	@SuppressWarnings({ "unchecked" })
	private TableView<Transaction> setTableView(ObservableList<Transaction> ledger) {
		
		TableColumn<Transaction, LocalDate> dateColumn = new TableColumn<>("Date");
		dateColumn.setMinWidth(75);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<Transaction, BigDecimal> amountColumn = new TableColumn<>("Amount");
		amountColumn.setMinWidth(75);
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("formattedAmount"));
		
		TableColumn<Transaction, String> merchantColumn = new TableColumn<>("Merchant");
		merchantColumn.setMinWidth(200);
		merchantColumn.setCellValueFactory(new PropertyValueFactory<>("merchant"));
		
		TableColumn<Transaction, String> buyerColumn = new TableColumn<>("Buyer");
		buyerColumn.setMinWidth(75);
		buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
		
		TableColumn<Transaction, String> categoryColumn = new TableColumn<>("Category");
		categoryColumn.setMinWidth(200);
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		TableView<Transaction> ledgerView = new TableView<>();
		ledgerView.setItems(ledger);
		ledgerView.getColumns().addAll(dateColumn, amountColumn, merchantColumn, buyerColumn, categoryColumn);
		ledgerView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			
			this.removalChoice = new SimpleObjectProperty<>(newValue);
			
		});
		
		return ledgerView;
		
	}

}
