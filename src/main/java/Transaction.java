//Contributing Author: Lloyd Edison (Lionel053)
package test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    
    private LocalDate date;
    private BigDecimal amount;
    private String merchant;
    private String buyer;
    private String category;

    public Transaction(LocalDate date, BigDecimal amount, String merchant) {
    	
        this.date = date;
        this.amount = amount;
        this.merchant = merchant;
        this.buyer = "";
        this.category = "";
        
    }

    public Transaction(LocalDate date, BigDecimal amount, String merchant, String buyer, String category) {
    	
        this.date = date;
        this.amount = amount;
        this.merchant = merchant;
        this.buyer = buyer;
        this.category = category;
        
    }

    public LocalDate getDate() {
    	
        return date;
        
    }

    public BigDecimal getAmount() {
    	
        return amount;
        
    }

    public String getMerchant() {
    	
        return merchant;
        
    }

    public String getBuyer() {
    	
        return buyer;
        
    }

    public String getCategory() {
    	
        return category;
        
    }

    public void setBuyer(String buyer) {
    	
        this.buyer = buyer;
        
    }

    public void setCategory(String category) {
    	
        this.category = category;
        
    }

    @Override
    public String toString() {
    	
        return date.toString() + " " + amount.toString() + " " + merchant + " " + buyer + " " + category;
        
    }
    
}

