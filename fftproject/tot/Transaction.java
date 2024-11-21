package fftproject.tot;

import java.util.Date;
public class Transaction {
	private Date date;
    private String description; 
    private double amount;
    private String buyerId; 
    private String category;

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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    // Method to display transaction details in a readable format
    public void displayTransactionDetails() {
        System.out.println("Transaction Details:");
        System.out.println("Date: " + date);
        System.out.println("Description: " + description); 
        System.out.println("Amount: $" + amount);
        System.out.println("BuyerId: " + buyerId); 
        System.out.println("Category: " + category);
    }

    public boolean isTaggedCorrectly() {
        return (this.buyerId != null && !this.buyerId.isEmpty()) && (this.category != null && !this.category.isEmpty());
    }

    @Override
    public String toString() {
        return "Transaction [Date=" + date + ", Description=" + description + ", Amount=" + amount + 
               ", BuyerId=" + buyerId + ", Category=" + category + "]";
    }

    public static void main(String[] args) {
        Transaction transaction = new Transaction(new Date(), "Amazon Purchase", 123.45, "buyer123", "Electronics");
        
        transaction.displayTransactionDetails();
        
        System.out.println("Is Transaction Tagged Correctly? " + transaction.isTaggedCorrectly());
    }
}
