package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;

public class LedgerReader {

    public static Deque<Transaction> readTransactions(String filePath) {
    	
        Deque<Transaction> transactions = new ArrayDeque<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        	
            String line;
            
            while ((line = reader.readLine()) != null) {
                
                String[] parts = line.split(": ");
                
                if (parts.length == 3) {
                	
                    try {
                       
                        LocalDate date = LocalDate.parse(parts[0]);
                        BigDecimal amount = new BigDecimal(parts[1]);
                        String merchant = parts[2];
                        
                        Transaction transaction = new Transaction(date, amount, merchant);
                        transactions.push(transaction); 
                        
                    } catch (Exception e) {
                    	
                        System.out.println("Error parsing line: " + line + ". Skipping this line.");
                        
                    }
                    
                } else {
                	
                    System.out.println("Invalid format: " + line + ". Skipping this line.");
                    
                }
                
            }
            
        } catch (IOException e) {
        	
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
            
        }
        
        return transactions;
    }
    
}

