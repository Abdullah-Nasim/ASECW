package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getId() {
        Category obj1 = new Category("Cat1", "Food");
        assertEquals("Cat1", obj1.getId());
    }

    @Test
    void getName() {
        Category obj1 = new Category("Cat1", "Food");
        assertEquals("Food", obj1.getName());
    }

    @Test
    void setId() {
        Category obj1 = new Category("Cat1", "Food");
        obj1.setId("Cat2");
        assertEquals("Cat2", obj1.getId());
    }

    @Test
    void setName() {
        Category obj1 = new Category("Cat1", "Food");
        obj1.setName("Food2");
        assertEquals("Food2", obj1.getName());
    }
}