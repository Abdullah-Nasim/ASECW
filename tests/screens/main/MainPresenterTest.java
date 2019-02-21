package screens.main;

import exceptions.CostLessThanOneException;
import exceptions.InvalidCSVFormatException;
import javafx.fxml.Initializable;
import models.Product;
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
    void loadData() {
    }

    // dummy overloading function
    public void onMenuLoaded(TreeMap<String, ArrayList<Product>> menu) {
    }

}