package group15.tot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddCategoryTest {
    private AddCategory addCategory;

    @BeforeEach
    public void setUp() {
        addCategory = new AddCategory();
    }

    @Test
    public void testAddCategory() {
        addCategory.addCategory("Books");
        ObservableList<String> categories = FXCollections.observableArrayList(addCategory.getCategories());
        assertTrue(categories.contains("Books"), "Category should be added to the list");
    }

    @Test
    public void testDeleteCategory() {
        addCategory.addCategory("Movies");
        ObservableList<String> categories = FXCollections.observableArrayList(addCategory.getCategories());
        categories.remove("Movies");
        assertFalse(categories.contains("Movies"), "Category should be removed from the list");
    }

    @Test
    public void testGetCategories() {
        addCategory.addCategory("Music");
        ObservableList<String> categories = FXCollections.observableArrayList(addCategory.getCategories());
        assertEquals(1, categories.size(), "Category list should have 1 item");
        assertEquals("Music", categories.get(0), "The category should be 'Music'");
    }
}

