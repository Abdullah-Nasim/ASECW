package screens.main;

import models.Customer;
import models.Product;

import java.util.ArrayList;

public interface MainView {
    void onMenuLoaded(ArrayList<Product> products, ArrayList<Customer> customers);
}
