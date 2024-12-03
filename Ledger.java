package test;

import java.util.ArrayDeque;
import java.util.Deque;

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

