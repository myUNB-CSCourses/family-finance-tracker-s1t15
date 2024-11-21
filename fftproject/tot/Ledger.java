package fftproject.tot;

import java.util.ArrayList;
import java.util.List;

public class Ledger {

    private List<Transaction> transactions; 
    private LedgerReader ledgerReader; 
    private LedgerWriter ledgerWriter; 

    public Ledger(LedgerReader ledgerReader, LedgerWriter ledgerWriter) {
        this.transactions = new ArrayList<>();
        this.ledgerReader = ledgerReader;
        this.ledgerWriter = ledgerWriter;
    }


    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void loadTransactionsFromFile(String filePath) {
        List<Transaction> loadedTransactions = ledgerReader.readFromExcel(filePath);
        transactions.addAll(loadedTransactions);
    }

    public void saveTransactionsToFile(String filePath) {
        ledgerWriter.writeToExcel(filePath, transactions);
    }

    public void displayAllTransactions() {
        System.out.println("Displaying all transactions:");
        for (Transaction transaction : transactions) {
            transaction.displayTransactionDetails();
        }
    }

    public List<Transaction> findTransactionsByDescription(String description) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getDescription().equalsIgnoreCase(description)) {
                result.add(transaction);
            }
        }
        return result;
    }

    public List<Transaction> findTransactionsByCategory(String category) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equalsIgnoreCase(category)) {
                result.add(transaction);
            }
        }
        return result;
    }

    public boolean validateLedger() {
        boolean isValid = true;
        for (Transaction transaction : transactions) {
            if (!transaction.isTaggedCorrectly()) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    // Method to apply rule-based tagging to all untagged transactions (optional future functionality)
    public void applyRulesForTagging() {
        for (Transaction transaction : transactions) {
            if (transaction.getBuyerId() == null || transaction.getCategory() == null) {
                // Example rule: Assign a default category if the transaction doesn't have a category
                if (transaction.getCategory() == null) {
                    transaction.setCategory("Uncategorized");
                }
                // Add more rules based on description, amount, or other attributes if needed
            }
        }
    }

    public double getTotalAmount() {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }
        return total;
    }

    public static void main(String[] args) {
        LedgerReader reader = new LedgerReader();
        LedgerWriter writer = new LedgerWriter();

        Ledger ledger = new Ledger(reader, writer);

        ledger.loadTransactionsFromFile("DummyLedger.xlsx");

        ledger.displayAllTransactions();

        System.out.println("Total Amount: $" + ledger.getTotalAmount());

        ledger.saveTransactionsToFile("DummyLedger.xlsx");

        System.out.println("Is Ledger Valid? " + ledger.validateLedger());

        ledger.applyRulesForTagging();
    }
}

