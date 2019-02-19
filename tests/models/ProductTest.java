package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Category cat = new Category("CAT1", "Beverages");
    Product obj1 = new Product("P1", "Water", "Bottled Water", cat, 3.00);

    @Test
    void getId() {
        assertEquals("P1", obj1.getId());
    }

    @Test
    void getName() {
        assertEquals("Water", obj1.getName());
    }

    @Test
    void getDescription() {
        assertEquals("Bottled Water", obj1.getDescription());
    }

    @Test
    void getCategory() {
        assertEquals("CAT1", obj1.getCategory().getId());
    }

    @Test
    void getCost() {
        assertEquals(3.0, obj1.getCost());
    }

    @Test
    void setId() {
        obj1.setId("P2");
        assertEquals("P2", obj1.getId());
    }

    @Test
    void setName() {
        obj1.setName("Milk");
        assertEquals("Milk", obj1.getName());
    }


    @Test
    void setDescription() {
        obj1.setDescription("Fresh Milk");
        assertEquals("Fresh Milk", obj1.getDescription());
    }

    @Test
    void setCategory() {
        cat.setName("Food");
        obj1.setCategory(cat);
        assertEquals("Food", obj1.getCategory().getName());
    }

    @Test
    void setCost() {
        obj1.setCost(4.00);
        assertEquals(4.0, obj1.getCost());
    }
}