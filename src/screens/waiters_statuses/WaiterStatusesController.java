package screens.waiters_statuses;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import models.Order;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WaiterStatusesController implements Initializable, WaiterThread.OrderProcessedListener
{
    public TableView waiterStatusesTableView;

    private List<WaiterThread> waiters;

    public void addOrderItem(Order.OrderItem orderItem) {

        if(findFreeWaiter() != null){
            findFreeWaiter().setOrder(orderItem);
            findFreeWaiter().setOrderProcessedListener(this);
            findFreeWaiter().run();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        waiters = new ArrayList<>();

        for(int i=0; i<=4; i++){
            waiters.add(new WaiterThread());
        }

        waiters.get(0).run();
    }

    private WaiterThread findFreeWaiter(){
        for (WaiterThread waiter : waiters) {
            if (!waiter.isAlive())
                return waiter;
        }
        return null;
    }

    @Override
    public void onOrderProcessed(Order order) {

    }
}
