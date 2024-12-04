package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayDeque;

import org.junit.Test;

public class LedgerTest {
	
	@Test
    public void testLedgerConstructor() {
		
        Ledger ledger = new Ledger();
        assertNotNull(ledger.getTransactions()); 
        assertTrue(ledger.getTransactions().isEmpty());
        
    }
	
	@Test
	public void testLedgerConstructorWithDeque() {
		
	    ArrayDeque<Transaction> transactionDeque = new ArrayDeque<>();
	    transactionDeque.push(new Transaction(LocalDate.now(), BigDecimal.valueOf(100), "Some Merchant"));
	    Ledger ledger = new Ledger(transactionDeque);
	    
	    assertEquals(1, ledger.getTransactions().size());
	    
	}

	@Test
    public void testAddTransaction() {
		
        Ledger ledger = new Ledger();
        Transaction transaction = new Transaction(LocalDate.now(), BigDecimal.valueOf(100), "Some Merchant");
        ledger.addTransaction(transaction);

        assertEquals(1, ledger.getTransactions().size());
        assertEquals(transaction, ledger.getTransactions().peek());
        
    }

    @Test
    public void testRemoveTransaction() {
    	
        Ledger ledger = new Ledger();
        Transaction transaction1 = new Transaction(LocalDate.now(), BigDecimal.valueOf(100), "Merchant");
        ledger.addTransaction(transaction1);
        
        Transaction removedTransaction = ledger.removeTransaction();
        
        assertEquals(transaction1, removedTransaction);
        assertTrue(ledger.getTransactions().isEmpty());
        
    }

}
