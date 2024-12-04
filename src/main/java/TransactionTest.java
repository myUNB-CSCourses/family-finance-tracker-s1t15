package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Test;

public class TransactionTest {

	@Test
	public void testTransactionConstructorAndGetters() {
		
	    Transaction transaction = new Transaction(LocalDate.now(), BigDecimal.valueOf(100), "Some Merchant", "Some Buyer", "Some Category");
	    
	    assertNotNull(transaction.getDate());
	    assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
	    assertEquals("Some Merchant", transaction.getMerchant());
	    assertEquals("Some Buyer", transaction.getBuyer());
	    assertEquals("Some Category", transaction.getCategory());
	    
	}

	@Test
	public void testTransactionConstructorWithDefaultValues() {
		
	    Transaction transaction = new Transaction(LocalDate.now(), BigDecimal.valueOf(50), "Some Merchant");
	    
	    assertNotNull(transaction.getDate());
	    assertEquals(BigDecimal.valueOf(50), transaction.getAmount());
	    assertEquals("Some Merchant", transaction.getMerchant());
	    assertEquals("", transaction.getBuyer()); 
	    assertEquals("", transaction.getCategory()); 
	    
	}
	
}
