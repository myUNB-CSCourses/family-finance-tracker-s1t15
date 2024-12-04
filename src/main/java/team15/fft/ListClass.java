//Contributing Author: James Ukandu
package team15.fft;

import java.io.*;
import java.util.ArrayList;

public class ListClass {
    private ArrayList<String> items;
    private final String filePath = "buyers.txt";

    public ListClass() {
        this.items = new ArrayList<>();
        loadFromFile();
    }

    public void addItem(String item) {
        items.add(item);
        saveToFile();
        System.out.println("Added: " + item);
    }

    public boolean containsItem(String item) {
        return items.stream().anyMatch(existingItem -> existingItem.equalsIgnoreCase(item));
    }

    public ArrayList<String> getItems() {
        return items;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String item : items) {
                writer.write(item);
                writer.newLine();
            }
        } 
        
        catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
    }
}
