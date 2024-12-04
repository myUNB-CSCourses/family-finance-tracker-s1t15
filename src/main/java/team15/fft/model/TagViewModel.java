//Contributing Author: Lloyd Edison (Lionel053)
package team15.fft.model;

import javafx.beans.property.StringProperty;

import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TagViewModel {

    private ObservableList<Transaction> unprocessedTransactions;
    private ObservableList<Transaction> processedTransactions;
    private StringProperty currentTransaction;
    private StringProperty completionMessage;

    public TagViewModel(Ledger ledger) {
       
        this.unprocessedTransactions = FXCollections.observableArrayList(ledger.getTransactions());
        
        this.processedTransactions = FXCollections.observableArrayList();
        
        if (!unprocessedTransactions.isEmpty()) {
        	
            this.currentTransaction = new SimpleStringProperty(format(unprocessedTransactions.get(0)));
            
        } else {
        	
            this.currentTransaction = new SimpleStringProperty("There are no transactions to process");
            
        }
        
        completionMessage = new SimpleStringProperty("");
        
    }

    public void nextTransaction() {
    	
        if (!unprocessedTransactions.isEmpty()) {
           
            Transaction transaction = unprocessedTransactions.remove(0);
            
            processedTransactions.add(transaction);
            
            if (!unprocessedTransactions.isEmpty()) {
            	
                currentTransaction.set(format(unprocessedTransactions.get(0)));
                
            } else {
            	
                currentTransaction.set("No more transaction to process"); 
                
            }
            
        }
        
    }

    private String format(Transaction t) {
    	
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d");
		return String.format(t.getDate().format(dateFormatter) + " | $" + t.getAmount() + 
								" | " + t.getMerchant() + " | " + t.getBuyer() + " | " + t.getCategory());
		
	}

	public void removeTransaction(Transaction transaction) {
    	
        if (processedTransactions.remove(transaction)) {
           
            unprocessedTransactions.add(0, transaction);
            
            if (!unprocessedTransactions.isEmpty()) {
            	
            	currentTransaction.set(format(unprocessedTransactions.get(0)));
                
            } else {
            	
                currentTransaction.set(""); 
                
            }
        }
        
    }

    public ObservableList<Transaction> getUnprocessedTransactions() {
    	
        return unprocessedTransactions;
        
    }

    public ObservableList<Transaction> getProcessedTransactions() {
    	
        return processedTransactions;
        
    }

    public StringProperty currentTransactionProperty() {
    	
        return currentTransaction;
        
    }
    
    public StringProperty getCompletionMessage() {
    	
    	return completionMessage;
    	
    }
    
    public void setCompletionMessage(boolean b) {
    	
    	if (b) {
    		
    		completionMessage.set("Ledger successfully written to Excel, you can close this window");
    		
    	} else {
    		
    		completionMessage.set("Unprocessed transactions remain, please complete before hitting write");
    		
    	}
    }
/*
    //May not be necessary, review later
    public String getCurrentTransaction() {
    	
        return currentTransaction.get();
        
    }

    //May not be necessary, review later
    public void setCurrentTransaction(String nextTransaction) {
    	
        currentTransaction.set(nextTransaction);
        
    }
*/ 
}
