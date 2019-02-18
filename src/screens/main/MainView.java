package screens.main;

import models.Customer;
import models.Product;

import java.util.ArrayList;
import java.util.TreeMap;

public interface MainView {
    void onMenuLoaded(TreeMap<String, ArrayList<Product>> menu);
}
