package screens.main;

import javafx.fxml.Initializable;
import models.Customer;
import models.Product;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable, MainView {

    private MainPresenter mainPresenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPresenter = new MainPresenter(this);

        try {
            mainPresenter.loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMenuLoaded(ArrayList<Product> products, ArrayList<Customer> customers) {

    }
}
