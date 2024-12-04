package fftproject;


import java.util.ArrayList;
import java.util.List;

public class Ledger {
    private final List<Transaction> transactions;

    public Ledger() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> findByCategory(String category) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (category.equalsIgnoreCase(transaction.getCategory())) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }

    public List<Transaction> findByBuyer(String buyerId) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (buyerId.equalsIgnoreCase(transaction.getBuyerId())) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }

    public double calculateTotalByCategory(String category) {
        double total = 0;
        for (Transaction transaction : findByCategory(category)) {
            total += transaction.getAmount();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ledger Transactions:\n");
        for (Transaction transaction : transactions) {
            sb.append(transaction).append("\n");
        }
        return sb.toString();
    }
}

