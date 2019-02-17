package screens.main;

import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, MainView {

    private MainPresenter mainPresenter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPresenter = new MainPresenter(this);
        try {
            mainPresenter.initCategories();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMenuLoaded() {

    }
}
