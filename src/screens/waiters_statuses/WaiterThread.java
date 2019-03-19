package screens.waiters_statuses;

import models.Order;

public class WaiterThread extends Thread {

    private Order order;
    private OrderProcessedListener orderProcessedListener;

    public void setOrder(Order.OrderItem order) {
        this.order = order;
    }

    public void setOrderProcessedListener(OrderProcessedListener orderProcessedListener) {
        this.orderProcessedListener = orderProcessedListener;
    }

    @Override
    public void run() {
        super.run();

        try {
            wait(3000);
            orderProcessedListener.onOrderProcessed(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    interface OrderProcessedListener{
        void onOrderProcessed(Order order);
    }
}
