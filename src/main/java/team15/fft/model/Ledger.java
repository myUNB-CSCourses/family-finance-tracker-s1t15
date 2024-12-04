//Contributing Author: Lloyd Edison (Lionel053)
package team15.fft.model;

import java.util.ArrayDeque;
import java.util.Deque;

import team15.fft.datastore.LedgerReader;
import team15.fft.datastore.LedgerWriter;

public class Ledger {
   
    private Deque<Transaction> transactions;
   
    public Ledger() {
    	
        this.transactions = new ArrayDeque<>();
        
    }

    public Ledger(String filePath) {
      
        this.transactions = LedgerReader.readTransactions(filePath);
        
    }
    
    public Ledger(ArrayDeque<Transaction> t) {
    	
    	this.transactions = t;
    	
    }
  
    public void addTransaction(Transaction transaction) {
    	
        transactions.push(transaction);
        
    }

   
    public Transaction removeTransaction() {
    	
        return transactions.poll();  
        
    }

    @Override
    public String toString() {
    	
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions) {
            sb.append(transaction.toString()).append("\n");
        }
        return sb.toString();
        
    }
    
    public void writeToFile() {
    	
    	LedgerWriter.writeTransactions(this.transactions);
    	
    }

    public Deque<Transaction> getTransactions() {
    	
        return transactions;
        
    }
}

