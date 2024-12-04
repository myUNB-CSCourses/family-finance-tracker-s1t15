package fftproject;

import java.util.Date;

public class Transaction {
    private final Date date;
    private final String description;
    private final double amount;
    private String buyerId; // Buyer ID for tagging
    private String category; // Category for tagging

    public Transaction(Date date, String description, double amount, String buyerId, String category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.buyerId = buyerId;
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Transaction[Date=%s, Description='%s', Amount=%.2f, BuyerId='%s', Category='%s']",
                date, description, amount, buyerId, category);
    }

    public boolean isTagged() {
        return buyerId != null && !buyerId.isEmpty() && category != null && !category.isEmpty();
    }
}
