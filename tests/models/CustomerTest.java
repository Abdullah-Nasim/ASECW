package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void getId() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        assertEquals("C001", obj1.getId());
    }

    @Test
    void getName() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        assertEquals("MOHD", obj1.getName());
    }

    @Test
    void getEmail() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        assertEquals("mohd@gmail.com", obj1.getEmail());
    }

    @Test
    void getPhone() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        assertEquals("0509898987", obj1.getPhone());
    }

    @Test
    void setId() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        obj1.setId("C002");
        assertEquals("C002", obj1.getId());
    }


    @Test
    void setName() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        obj1.setName("MOHD2");
        assertEquals("MOHD2", obj1.getName());
    }


    @Test
    void setEmail() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        obj1.setEmail("mohd2@gmail.com");
        assertEquals("mohd2@gmail.com", obj1.getEmail());
    }


    @Test
    void setPhone() {
        Customer obj1 = new Customer("C001", "MOHD", "mohd@gmail.com", "0509898987");
        obj1.setPhone("0509898954");
        assertEquals("0509898954", obj1.getPhone());
    }
}