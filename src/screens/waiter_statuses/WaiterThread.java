package screens.waiter_statuses;

import javafx.concurrent.Task;
import models.Order;

public class WaiterThread extends Thread {

    private String waiterId;
    private String orderId;
    private String customerId;
    private String isProcessing;

    private OrderProcessedListener orderProcessedListener;

    WaiterThread(String waiterId, String orderId, String customerId, String isProcessing, OrderProcessedListener orderProcessedListener) {
        this.waiterId = waiterId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.isProcessing = isProcessing;
        this.orderProcessedListener = orderProcessedListener;
    }

    void setOrderProcessedListener(OrderProcessedListener orderProcessedListener) {
        this.orderProcessedListener = orderProcessedListener;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setIsProcessing(String isProcessing) {
        this.isProcessing = isProcessing;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getIsProcessing() {
        return isProcessing;
    }

    @Override
    public void run() {
        super.run();

            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };

        sleeper.setOnSucceeded(event -> {
            isProcessing = "Order Processed";
            orderProcessedListener.onOrderProcessed(orderId);
        });
        new Thread(sleeper).start();
    }

    interface OrderProcessedListener{
        void onOrderProcessed(String orderId);
    }
}
