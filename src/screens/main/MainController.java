package screens.main;

import interfaces.CartInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import models.Order;
import models.Product;
import screens.main.lists.ProductsListViewCell;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class MainController implements Initializable, MainView, CartInterface {

    public ListView<Product> foodItemsLV;
    public ListView<Product> beveragesLV;
    public ListView<Product> otherItemsLV;
    public Button placeOrderBtn;
    public TableView<Product> cartTableView;
    public TableColumn<Product, String> productIdCol;
    public TableColumn<Product, String> productNameCol;
    public TableColumn<Product, String> categoryCol;
    public TableColumn<Product, Double> costCol;
    public TableColumn<Product, String> quantityCol;

    private ObservableList<Product> foodItemsObservableList;
    private ObservableList<Product> beveragesObservableList;
    private ObservableList<Product> otherItemsObservableList;

    private ObservableList<Product> cartObservableList;

    private MainPresenter mainPresenter;

    public MainController(){
        foodItemsObservableList = FXCollections.observableArrayList();
        beveragesObservableList = FXCollections.observableArrayList();
        otherItemsObservableList = FXCollections.observableArrayList();
        cartObservableList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPresenter = new MainPresenter(this);

        cartTableView.setEditable(true);

        try {
            mainPresenter.loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        placeOrderBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Order.getInstance();
            }
        });

        // Set up the table data
        productIdCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        productNameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        categoryCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));

        costCol.setCellValueFactory(
                new PropertyValueFactory<>("cost")
        );

        quantityCol.setCellValueFactory(
                new PropertyValueFactory<>("orderedQuantity")
        );

        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn());

        quantityCol.setMinWidth(50);

        quantityCol.setOnEditCommit((TableColumn.CellEditEvent<Product, String> event) -> {
            TablePosition<Product, String> pos = event.getTablePosition();

            String newQuantity = event.getNewValue();

            int row = pos.getRow();
            Product product = event.getTableView().getItems().get(row);

            product.setOrderedQuantity(newQuantity);
        });

        cartTableView.setItems(cartObservableList);
    }

    @Override
    public void onMenuLoaded(TreeMap<String, ArrayList<Product>> menu) {

        //Setting up the food items list view
        foodItemsObservableList.addAll(menu.get("CAT102"));
        foodItemsLV.setItems(foodItemsObservableList);
        foodItemsLV.setCellFactory(foodItemsLV -> new ProductsListViewCell(this));

        //Setting up the beverages list view
        beveragesObservableList.addAll(menu.get("CAT101"));
        beveragesLV.setItems(beveragesObservableList);
        beveragesLV.setCellFactory(beveragesLV -> new ProductsListViewCell(this));

        //Setting up the other items list view
        otherItemsObservableList.addAll(menu.get("CAT103"));
        otherItemsLV.setItems(otherItemsObservableList);
        otherItemsLV.setCellFactory(otherItemsLV -> new ProductsListViewCell(this));

    }

    @Override
    public void productAddedToCart(Product product) {
        cartObservableList.add(product);
    }

    @Override
    public void productRemovedFromCart(Product product) {
        cartObservableList.remove(product);
    }
}
