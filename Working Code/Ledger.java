package tagView.uju;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ledger {
    private List<Transaction> transactions;

    public Ledger() {
        this.transactions = new ArrayList<>();
    }

    // Add a transaction to the ledger
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        } else {
            System.err.println("Cannot add null transaction.");
        }
    }

    // Get all transactions in the ledger
    public List<Transaction> getLedger() {
        return transactions;
    }

    // Get a transaction by index
    public Transaction getTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            return transactions.get(index);
        } else {
            System.err.println("Index out of bounds: " + index);
            return null;
        }
    }

    // Remove a transaction from the ledger
    public void removeTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) {
            transactions.remove(transaction);
        } else {
            System.err.println("Transaction not found in ledger.");
        }
    }

    // Remove a transaction by index
    public void removeTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
        } else {
            System.err.println("Index out of bounds: " + index);
        }
    }
    
    public void setLedger(ArrayList<Transaction> transactions) {
        if (transactions == null) {
            System.err.println("Transactions list is null.");
            return;
        }
        this.transactions = transactions;
    }

    // Load dummy transactions for testing purposes
    public void loadDummyTransactions() {
        transactions.add(new Transaction("2024-11-20", "Amazon Purchase", 123.45, null, null, "Amazon"));
        transactions.add(new Transaction("2024-11-21", "Grocery Shopping", 75.50, null, null, "Walmart"));
        transactions.add(new Transaction("2024-11-22", "Gas Station", 40.00, null, null, "Shell"));
    }

    // Debug method to print all transactions
    public void printAllTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Method to write transactions to a CSV file
    public void write() {
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions to write.");
            return;
        }

        try (FileWriter writer = new FileWriter("processed_transactions.csv")) {
            // Write the CSV header
            writer.write("Date,Amount,Merchant,Buyer,Category\n");

            // Write each transaction as a CSV row
            for (Transaction transaction : transactions) {
                writer.write(transaction.getDate() + "," +
                             transaction.getAmount() + "," +
                             (transaction.getMerchant() != null ? transaction.getMerchant() : "Unknown") + "," +
                             (transaction.getBuyerId() != null ? transaction.getBuyerId() : "Unassigned") + "," +
                             (transaction.getCategory() != null ? transaction.getCategory() : "Uncategorized") + "\n");
            }

            System.out.println("Transactions successfully written to 'processed_transactions.csv'.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
