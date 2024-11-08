package team15.fft;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage rootWindow) throws Exception {
		
		ViewModel viewModel = new ViewModel();
		
		GridPane myPane = new GridPane();
		myPane.add(new ViewController(viewModel).getView(), 0, 0);
		
		rootWindow.setScene(new Scene(myPane, 400, 400));
		rootWindow.setTitle("This has been a test of the emergency broadcast system");
		rootWindow.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
