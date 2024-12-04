//Contributing Author: Lloyd Edison (Lionel 053)
package team15.fft.view.builders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Builder;
import team15.fft.model.TagViewModel;
import team15.fft.model.Transaction;

public class TagView implements Builder<Region> {

    private TagViewModel tagViewModel;
    private TableView<Transaction> ledgerView;
    private Runnable writeButtonAction;
    private ComboBox<String> comboBoxBuyer;    //needed so to avoid the problem with not selecting a new buyer or category each new transaction presented
    private ComboBox<String> comboBoxCategory; //likewise, same as above
    
    public TagView(TagViewModel tagViewModel, Runnable writeButtonAction) {
    	
        this.tagViewModel = tagViewModel;
        this.writeButtonAction = writeButtonAction;
        
    }

    @Override
    public Region build() {
     
        VBox results = new VBox(10);

        results.getChildren().addAll(createCurrentTransactionLabel(), createCurrentTransactionText(), createSelectInstructionText(),
        								createComboBoxes(), createAddButton(), createTableView(), createRemoveButton(),
        								createWriteButton(), createCompletionMessageLabel());

        return results;
        
    }

    private Node createAddButton() {
    	
		Button button = new Button("Add");
		Tooltip addButtonToolTip = new Tooltip("Click to add transaction once you select a buyer and category");
		Tooltip.install(button, addButtonToolTip);
		button.setOnAction(evt -> {tagViewModel.getUnprocessedTransactions().get(0).setBuyer(comboBoxBuyer.getValue()); 
									tagViewModel.getUnprocessedTransactions().get(0).setCategory(comboBoxCategory.getValue()); tagViewModel.nextTransaction();});
		return button;
		
	}

	private Node createCurrentTransactionLabel() {
    	
		Label text = new Label("Current transaction to process");
		return text;
		
	}

	private Node createCurrentTransactionText() {
    	
        Label text = new Label();
        StringProperty currentTransaction = tagViewModel.currentTransactionProperty();
        text.textProperty().bind(currentTransaction);
        return text;
        
    }

    private Node createSelectInstructionText() {
    	
        return new Label("Please select a 'Buyer' and 'Category'.");
        
    }

    private Node createComboBoxes() {
    	
        HBox comboBoxContainer = new HBox(10);
        comboBoxContainer.setStyle("-fx-padding: 10;");
        
        comboBoxContainer.getChildren().addAll(new Label("Buyer: "), getBuyerCombo(), new Label("Category: "), getCategoryCombo());

        return comboBoxContainer;
        
    }
    
    private Node getBuyerCombo() {
		
		/*Test method for adding buyers temporarily, remove when feature is fully integrated*/
    	ArrayList<String> buyerList = createBuyerList();
    	
    	comboBoxBuyer = new ComboBox<>();
    	comboBoxBuyer.getItems().addAll(buyerList);
   
    	return comboBoxBuyer;
    	
    }
    
    private Node getCategoryCombo() {
    	
    	/*Test method for adding category temporarily, remove when feature is fully integrated*/
    	ArrayList<String> categoryList = createCategoryList();
    	
    	comboBoxCategory = new ComboBox<>();
    	comboBoxCategory.getItems().addAll(categoryList);
    	
    	return comboBoxCategory;
    	
	}
    
    private Node createRemoveButton() {
    	
        Button removeButton = new Button("Remove");
        Tooltip removeButtonToolTip = new Tooltip("Click to remove a selected transaction from table and it will show back up at the top");
		Tooltip.install(removeButton, removeButtonToolTip);
        removeButton.setOnAction(evt -> tagViewModel.removeTransaction(ledgerView.getSelectionModel().getSelectedItem()));
        return removeButton;
        
    }
    
    private Node createWriteButton() {
    	
    	Button writeButton = new Button("Write");
    	Tooltip writeButtonToolTip = new Tooltip("Click to write to Excel");
		Tooltip.install(writeButton, writeButtonToolTip);
    	writeButton.setOnAction(evt -> writeButtonAction.run());
    	
    	return writeButton;
    	
    }
    
    private Node createCompletionMessageLabel() {
    	
    	Label label = new Label();
    	label.textProperty().bind(tagViewModel.getCompletionMessage());
    	return label;
    	
    }

    @SuppressWarnings("unchecked")
	private TableView<Transaction> createTableView() {
    	
    	TableColumn<Transaction, LocalDate> dateColumn = new TableColumn<>("Date");
		dateColumn.setMinWidth(75);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<Transaction, BigDecimal> amountColumn = new TableColumn<>("Amount ($)");
		amountColumn.setMinWidth(75);
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		TableColumn<Transaction, String> merchantColumn = new TableColumn<>("Merchant");
		merchantColumn.setMinWidth(200);
		merchantColumn.setCellValueFactory(new PropertyValueFactory<>("merchant"));
		
		TableColumn<Transaction, String> buyerColumn = new TableColumn<>("Buyer");
		buyerColumn.setMinWidth(75);
		buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
		
		TableColumn<Transaction, String> categoryColumn = new TableColumn<>("Category");
		categoryColumn.setMinWidth(200);
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		ledgerView = new TableView<>();
		ledgerView.setItems(tagViewModel.getProcessedTransactions());
		ledgerView.getColumns().addAll(dateColumn, amountColumn, merchantColumn, buyerColumn, categoryColumn);

        return ledgerView;
        
    }
    
    /*Remove when buyer feature is fully implemented, this is for testing only*/
    private ArrayList<String> createBuyerList() {
    	
    	ArrayList<String> buyerList = new ArrayList<>();
    	buyerList.add("");
		buyerList.add("Bob");
		buyerList.add("Cathy");
		
		return buyerList;
		
	}

    /*Remove when category feature is fully implemented, this is for testing only*/
	private ArrayList<String> createCategoryList() {
		
		ArrayList<String> categoryList = new ArrayList<>();
		categoryList.add("");
		categoryList.add("Dining Out");
		categoryList.add("Recreation");
		categoryList.add("Shopping/Grocery");
		categoryList.add("Shopping");
		categoryList.add("Automotive Fuel");
		categoryList.add("Takeout Coffee");
		categoryList.add("Car Repairs");
		
		return categoryList;
		
	}
    
}

