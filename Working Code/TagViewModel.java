package tagView.uju;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class TagViewModel {
    private final Builder<Region> builder;
    private final Ledger ledger;
    private final Ledger processedTransactions;
    private final ObservableList<Transaction> viewLedger;
    private final ObjectProperty<Transaction> currentTransaction;
    private final StringProperty buyerChoice = new SimpleStringProperty("");
    private final StringProperty categoryChoice = new SimpleStringProperty("");
    private final Rules ruleList = new Rules();

    public TagViewModel() {
        this.ledger = new Ledger(); // Initialize ledger with transactions
        this.processedTransactions = new Ledger(); // For processed transactions
        this.viewLedger = FXCollections.observableArrayList();

        // Add dummy transactions for testing
        ledger.addTransaction(new Transaction("2024-11-20", "Amazon Purchase", 123.45, null, null, "Amazon"));
        ledger.addTransaction(new Transaction("2024-11-21", "Groceries", 75.50, null, null, "Walmart"));

        // Initialize the currentTransaction with the first transaction in the ledger
        if (!ledger.getLedger().isEmpty()) {
            this.currentTransaction = new SimpleObjectProperty<>(ledger.getLedger().get(0));
        } else {
            this.currentTransaction = new SimpleObjectProperty<>(null);
        }

        this.builder = new TagView(viewLedger, this::addButtonAction, this::writeButtonAction,
                                   buyerList(), categoryList(), currentTransaction, buyerChoice, categoryChoice);
    }


    private ArrayList<String> categoryList() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Dining Out");
        categories.add("Recreation");
        categories.add("Groceries");
        categories.add("Fuel");
        categories.add("Takeout Coffee");
		categories.add("Car Repairs");
		
        return categories;
    }

    private ArrayList<String> buyerList() {
        ArrayList<String> buyers = new ArrayList<>();
        buyers.add("Bob");
        buyers.add("Cathy");
        return buyers;
    }

    public Region getView() {
        return builder.build();
    }

    private void addButtonAction() {
        if (currentTransaction.get() == null) {
            System.out.println("No transaction available for tagging.");
            return;
        }

        // Retrieve the current transaction
        Transaction transaction = currentTransaction.get();
        transaction.setBuyer(buyerChoice.get()); // Set the buyer from the dropdown
        transaction.setCategory(categoryChoice.get()); // Set the category from the dropdown

        // Add the transaction to the viewLedger
        viewLedger.add(transaction);

        // Remove the transaction from the ledger
        ledger.getLedger().remove(transaction);

        // Update currentTransaction to the next available transaction
        if (!ledger.getLedger().isEmpty()) {
            currentTransaction.set(ledger.getLedger().get(0));
        } 
        
        else {
            currentTransaction.set(null); // No more transactions left
        }

        System.out.println("Transaction added to viewLedger: " + transaction);
    }



    private void writeButtonAction() {
        if (viewLedger.isEmpty()) {
            System.out.println("No transactions to write.");
            return;
        }

        try {
            // Update the processed transactions ledger
            processedTransactions.setLedger(new ArrayList<>(viewLedger));
            
            // Write to file
            processedTransactions.write();
            
            // Clear the view ledger after writing
            viewLedger.clear();
            System.out.println("Transactions successfully processed and saved.");
        } 
        
        catch (Exception e) {
            System.err.println("Error processing transactions: " + e.getMessage());
        }
    }


    private void checkForRules() {
        ArrayList<Transaction> taggedTransactions = new ArrayList<>();
        for (Transaction transaction : ledger.getLedger()) {
            String buyer = ruleList.getBuyer(transaction.getMerchant()); // Ensure getMerchant() is called correctly
            if (buyer != null) {
                transaction.setBuyer(buyer);
                taggedTransactions.add(transaction);
            }
        }
        viewLedger.addAll(taggedTransactions);
    }

  
}
