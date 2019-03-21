package screens.customer_orders.lists;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import models.Order;

import java.io.IOException;

public class OrdersListViewCell extends ListCell<Order.OrderItem> {

    @FXML
    private Label order_id;

    @FXML
    private Label customer_id;

    @FXML
    private Label time_stamp;

    @FXML
    private Label total_cost;

    @FXML
    private Label current_status;

    @FXML
    private AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Order.OrderItem order, boolean empty) {
        super.updateItem(order, empty);

        if(empty || order == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/orders_list_cell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            order_id.setText(order.getId());
            customer_id.setText(order.getCustomerId());
            time_stamp.setText(order.getTimeStamp());
            total_cost.setText(order.getTotal() + " AED");
            current_status.setText(order.getProcessingStatus());

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
