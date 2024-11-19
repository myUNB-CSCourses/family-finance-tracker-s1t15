//Contributing Authors: Lloyd Edison (Lionel 053)
package main.java;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class TagViewModel {

	Builder<Region> builder;
	Ledger ledger;																//Model from the Ledger class
	Ledger processedTransactions;												//A collection of processed transactions removed from the view (to be used to write to excel)
	ObservableList<Transaction> viewLedger;										//For the table view object to let buyer know what transactions have been tagged
	ObjectProperty<Transaction> currentTransaction;
	
	StringProperty buyerChoice = new SimpleStringProperty("");
	StringProperty categoryChoice = new SimpleStringProperty("");
	
	Rules ruleList = new Rules();
	
	public TagViewModel() {
		
		this.ledger = new Ledger(true);											//Calling test method that generates DummyLedger, replace with file path
		this.processedTransactions = new Ledger(this.ledger.getMonthYear());	//Uses the unprocessed ledger's date to start a processed ledger
		
		
		this.viewLedger = FXCollections.observableArrayList();
		checkForRules();														//automatically tag any transactions add it to the table view
		this.currentTransaction = new SimpleObjectProperty<>(ledger.getTransaction(0));
		
		builder = new TagView(viewLedger, this::addButtonAction, this::writeButtonAction, 
								buyerList(), categoryList(),currentTransaction, this.buyerChoice, 
								this.categoryChoice);
		
	}

	/*For testing purposes, will need to be replaced by a feature that will allow buyer to modify a category list*/
	private ArrayList<String> categoryList() {
		
		ArrayList<String> categoryList = new ArrayList<>();
		categoryList.add("Dining Out");
		categoryList.add("Recreation");
		categoryList.add("Shopping/Grocery");
		categoryList.add("Shopping");
		categoryList.add("Automotive Fuel");
		categoryList.add("Takeout Coffee");
		categoryList.add("Car Repairs");
		
		return categoryList;
	}

	/*For testing purposes, will need to be replaced by a feature that will allow buyer to modify a buyer list*/
	private ArrayList<String> buyerList() {
		
		ArrayList<String> buyerList = new ArrayList<>();
		buyerList.add("Bob");
		buyerList.add("Cathy");
		
		return buyerList;
	}

	public Region getView() {
		
		return this.builder.build();
		
	}
	
	/*This button will allow the buyer to add the current transaction to the table view*/
	private void addButtonAction() {
		if (this.ledger.getLedger().size() != 0) {
			currentTransaction.get().setBuyer(buyerChoice.get());
			currentTransaction.get().setCategory(categoryChoice.get());
			viewLedger.add(currentTransaction.get());
			ledger.removeTransaction();
			try {
				currentTransaction.set(ledger.getTransaction(0));
			} catch(Throwable t) {
				currentTransaction.set(new Transaction());
			}
		} else {
			//do nothing
		}
		
		
	}
	
	/*Used for writing to the Ledger class that will call an Excel reader to write data*/
	private void writeButtonAction() {
		
		Iterator<Transaction> i = viewLedger.iterator();
		ArrayList<Transaction> dummy = new ArrayList<>();
		
		while (i.hasNext()) {
			dummy.add(i.next());
			i.remove();
		}
		
		processedTransactions.setLedger(dummy);
		processedTransactions.write();
		
	}
	
	/*Remove all transactions that existing rules and add it to the table view*/
	private void checkForRules() {
		
		String check;
		Transaction t;
		int j = 0;
		
		for (int i = 0; i < ledger.getLedger().size(); i++) {
			
			t = this.ledger.getTransaction(j);
			check = ruleList.getBuyer(t.getMerchant());
			if (check == null) {
				j = j + 1;
			}
			else {
				t.setBuyer(check);
				this.viewLedger.add(t);
				this.ledger.removeTransaction(j);
			}
			
		}
		
	}
	
}
