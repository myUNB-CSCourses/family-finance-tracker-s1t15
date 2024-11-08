package team15.fft;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class ViewBuilder implements Builder<Region> {
	
	Runnable runnable;
	StringProperty message;
	
	public ViewBuilder(StringProperty message, Runnable runnable) {
		this.message = message;
		this.runnable = runnable;
	}

	@Override
	public Region build() {
		
		GridPane view = new GridPane();
		
		view.add(helloLabel(), 5, 5);
		view.add(noButton(), 6, 6);
		
		return view;
	}
	
	private Node helloLabel() {
		Label helloWorld = new Label();
		helloWorld.textProperty().bind(message);
		return helloWorld;
	}
	
	private Node noButton() {
		Button button = new Button("Launch");
		button.setOnAction(e -> runnable.run());
		return button;
	}

}
