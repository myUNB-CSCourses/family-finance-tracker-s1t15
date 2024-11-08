package team15.fft;

import javafx.scene.layout.Region;
import javafx.util.Builder;

public class ViewController {
	
	ViewModel viewModel;
	Builder<Region> builder;
	
	public ViewController(ViewModel viewModel) {
		this.viewModel = viewModel;
		this.builder = new ViewBuilder(this.viewModel.getMessage(), this::noButtonAction);
	}
	
	public Region getView() {
		return builder.build();
	}

	private void noButtonAction() {
		viewModel.setMessage("I knew you would do that!");
	}
	
}
