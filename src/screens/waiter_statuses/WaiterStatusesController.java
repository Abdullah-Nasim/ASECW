package screens.waiter_statuses;

import interfaces.OrderStatusChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class WaiterStatusesController implements Initializable, WaiterThread.OrderProcessedListener
{
    public TableView<WaiterThread> waiterStatusesTableView;

    public TableColumn<WaiterThread, String> waiter_id_col;
    public TableColumn<WaiterThread, String> order_id_col;
    public TableColumn<WaiterThread, String> customer_id_col;
    public TableColumn<WaiterThread, String> order_status_col;

    private OrderStatusChangeListener orderStatusChangeListener;

    public void setOrderStatusChangeListener(OrderStatusChangeListener orderStatusChangeListener) {
        this.orderStatusChangeListener = orderStatusChangeListener;
    }

    private ObservableList<WaiterThread> waiters = FXCollections.observableArrayList();

    public void addOrderItem(Order.OrderItem orderItem) {
        WaiterThread waiter = findFreeWaiter();
        if(waiter != null){
            orderItem.setProcessingStatus("Is processing");
            waiter.setOrderId(orderItem.getId());
            waiter.setCustomerId(orderItem.getCustomerId());
            waiter.setOrderProcessedListener(this);
            waiter.run();
        }
        waiterStatusesTableView.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i=0; i<=3; i++){
            int waiterId = 100 + i;
            waiters.add(new WaiterThread("Waiter" + waiterId, "", "", "", this));
        }

        waiter_id_col.setCellValueFactory(
                new PropertyValueFactory<>("waiterId")
        );

        order_id_col.setCellValueFactory(
                new PropertyValueFactory<>("orderId")
        );

        customer_id_col.setCellValueFactory(
                new PropertyValueFactory<>("customerId")
        );

        order_status_col.setCellValueFactory(
                new PropertyValueFactory<>("isProcessing")
        );

        waiterStatusesTableView.setItems(waiters);
    }

    private WaiterThread findFreeWaiter(){
        for (WaiterThread waiter : waiters) {
            if (!waiter.getIsProcessing().equals("Processing")){
                waiter.setIsProcessing("Processing");
                waiterStatusesTableView.refresh();
                return waiter;
            }
        }
        return null;
    }

    @Override
    public void onOrderProcessed(String orderId) {

        for(int i = 0; i < Order.getInstance().orders.size(); i++ ){
            if(Order.getInstance().orders.get(i).getId().equals(orderId)){
                Order.getInstance().orders.get(i).setProcessingStatus("Processed");
                break;
            }
        }

        for(int i = 0; i < Order.getInstance().orders.size(); i++ ){
            if(Order.getInstance().orders.get(i).getProcessingStatus().equals("Waiting")){
                addOrderItem(Order.getInstance().orders.get(i));
                break;
            }
        }
        orderStatusChangeListener.onOrderStatusChangedListener();
        waiterStatusesTableView.refresh();
    }
}
