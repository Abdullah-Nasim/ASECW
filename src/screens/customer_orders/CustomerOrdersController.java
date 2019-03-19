package screens.customer_orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Order;
import screens.customer_orders.lists.OrdersListViewCell;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class CustomerOrdersController implements Initializable {

    public ListView<Order.OrderItem> ordersListView;
    private ObservableList<Order.OrderItem> ordersObservableList = FXCollections.observableArrayList();

    public void setData(LinkedList<Order.OrderItem> orderItems){
        ordersObservableList.addAll(orderItems);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ordersListView.setItems(ordersObservableList);
        ordersListView.setCellFactory(ordersListView -> new OrdersListViewCell());

    }
}
