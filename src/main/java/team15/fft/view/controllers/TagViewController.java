//Contributing Author: Lloyd Edison (Lionel053)
package team15.fft.view.controllers;

import java.util.ArrayDeque;

import javafx.scene.layout.Region;
import javafx.util.Builder;
import team15.fft.model.Ledger;
import team15.fft.model.TagViewModel;
import team15.fft.model.Transaction;
import team15.fft.view.builders.TagView;

public class TagViewController {
	
	private Builder<Region> builder;
	private TagViewModel tagViewModel;
	private Ledger ledger;
	
	public TagViewController(Ledger ledger) {
		
		this.ledger = ledger;
		
		tagViewModel = new TagViewModel(this.ledger);
		
		builder = new TagView(tagViewModel, this::writeButtonAction);
		
	}
	
	public Region getView() {
		
		return builder.build();
		
	}
	
	private boolean writeButtonAction() {
		
		if (!tagViewModel.getUnprocessedTransactions().isEmpty()) {
			
			tagViewModel.setCompletionMessage(false);
			
			return false;
			
		} else {
			
			ArrayDeque<Transaction> d = new ArrayDeque<>();
			d.addAll(tagViewModel.getProcessedTransactions());
			
			Ledger completeLedger = new Ledger(d);
			completeLedger.writeToFile();
			
			tagViewModel.setCompletionMessage(true);
			
			return true;
			
		}
		
	}
	
}
