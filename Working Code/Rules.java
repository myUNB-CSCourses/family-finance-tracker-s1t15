package tagView.uju;

import java.util.HashMap;

public class Rules {
    private HashMap<String, String> rules;

    public Rules() {
        rules = new HashMap<>();
        setTestRules(); // For testing only
    }

    public boolean addRule(String merchant, String buyer) {
        if (merchant == null || buyer == null) {
            return false; // Ensure valid inputs
        }
        try {
            rules.put(merchant, buyer);
            return true;
        } 
        
        catch (Exception e) {
            System.err.println("Failed to add rule: " + e.getMessage());
            return false;
        }
    }

    public String getBuyer(String merchant) {
        return rules.get(merchant);
    }

    // Remove this method before merging into the main branch
    private void setTestRules() {
        rules.put("McDonalds", "Bob");
        rules.put("LiquorNB", "Cathy");
        rules.put("Local Garage", "Cathy");
    }
}
