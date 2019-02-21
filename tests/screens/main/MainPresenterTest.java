package screens.main;

import exceptions.CostLessThanOneException;
import exceptions.InvalidCSVFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import models.Category;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MainPresenterTest implements MainView {
    @Test
    void getCategoryUsingId() {
        MainPresenter mainPresenter = new MainPresenter(this);
        try {
            mainPresenter.loadData();
        } catch (FileNotFoundException | InvalidCSVFormatException | CostLessThanOneException e) {
            e.printStackTrace();
        }
        assertEquals("Beverages", mainPresenter.getCategoryUsingId("CAT101").getName());
        assertEquals("Food Items", mainPresenter.getCategoryUsingId("CAT102").getName());
        assertEquals("Other Items", mainPresenter.getCategoryUsingId("CAT103").getName());
    }

    @Test
    void getBeverageItems() {
        MainPresenter mainPresenter = new MainPresenter(this);
        try {
            mainPresenter.loadData();
        } catch (FileNotFoundException | InvalidCSVFormatException | CostLessThanOneException e) {
            e.printStackTrace();
        }
        ArrayList<Product> beverageProduct = mainPresenter.getBeverageItems();
//        assertArrayEquals("expected products",beverageProduct);
    }


    @Test
    void checkForDiscountEligibilityTest() throws CostLessThanOneException {
        MainPresenter mainPresenter = new MainPresenter(this);
        try {
            mainPresenter.loadData();
        } catch (FileNotFoundException | InvalidCSVFormatException | CostLessThanOneException e) {
            e.printStackTrace();
        }
        ObservableList<Product> cartObservableList;
        cartObservableList = FXCollections.observableArrayList();
        Category category_bev = new Category("CAT101", "Beverages");
        Category category_food = new Category("CAT102", "Food Items");

        Product product = new Product("FOOD101", "Sandwiches", "", category_food, 10.00);
        cartObservableList.add(product);

        product = new Product("BRV101", "Coffee(Hot, Cold)", "", category_bev, 10.00);
        cartObservableList.add(product);

        assertFalse(mainPresenter.checkForDiscountEligibility(cartObservableList));

        product = new Product("FOOD102", "Croissant", "", category_food, 10.00);
        cartObservableList.add(product);

        assertTrue(mainPresenter.checkForDiscountEligibility(cartObservableList));

    }

    @Test
    void loadData() {
    }

    // dummy overloading function
    public void onMenuLoaded(TreeMap<String, ArrayList<Product>> menu) {
    }

}