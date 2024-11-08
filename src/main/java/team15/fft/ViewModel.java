package team15.fft;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {
	
	private StringProperty message = new SimpleStringProperty("");
	
	public ViewModel() {
		this.message.set("Don't press the button");
	}
	
	public void setMessage(String str) {
		this.message.set(str);
	}
	
	public StringProperty getMessage() {
		return message;
	}

}
