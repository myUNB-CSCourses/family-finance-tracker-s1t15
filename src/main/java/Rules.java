package main.java;

import java.util.HashMap;

public class Rules {
	
	HashMap<String, String> rules;
	
	public Rules() {
		
		rules = new HashMap<>();
		setTestRules();												//for testing only, remove before merge with main
		
	}
	
	public boolean addRule(String merchant, String buyer) {
		
		try {
			
			rules.put(merchant, buyer);
			return true;
			
		} catch(Throwable t) {
			
			return false;
			
		}
		
	}
	
	public String getBuyer(String merchant) {
		
		String buyer = rules.get(merchant);
		
		return buyer;
		
	}
	
	/*remove before merge with main*/
	private void setTestRules() {
		rules.put("McDonalds", "Bob");
		rules.put("LiquorNB", "Cathy");
		rules.put("Local Garage", "Cathy");
	}

}
