package tagView.uju;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private String description;
    private double amount;
    private String buyerId;
    private String category;
    private String merchant; // Add merchant field

    // Constructor
    public Transaction(String date, String description, double amount, String buyerId, String category, String merchant) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.description = description;
        this.amount = amount;
        this.buyerId = buyerId != null ? buyerId : "Unassigned"; // Default value
        this.category = category != null ? category : "Uncategorized"; // Default value
        this.merchant = merchant != null ? merchant : "Unknown Merchant"; // Default value
    }


    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    public void setBuyer(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMerchant() { // Add getter for merchant
        return merchant;
    }

    public void setMerchant(String merchant) { // Add setter for merchant
        this.merchant = merchant;
    }

    // Helper Methods
    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFormattedAmount() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "$" + df.format(amount);
    }

    public boolean isTaggedCorrectly() {
        return buyerId != null && !buyerId.isEmpty() && category != null && !category.isEmpty();
    }

    // Debugging Method
    @Override
    public String toString() {
        return "Transaction [Date=" + getFormattedDate() + ", Description=" + description +
               ", Amount=" + getFormattedAmount() + ", BuyerId=" + buyerId + 
               ", Category=" + category + ", Merchant=" + merchant + "]";
    }
}
