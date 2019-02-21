package models;

import java.util.LinkedList;

public class Order {

    private static Order order;

    public static Order getInstance(){
        if(order==null) {
            order = new Order();
            return order;
        }
        else
            return order;
    }

    public LinkedList<OrderItem> orders = new LinkedList<>();

    public static class OrderItem{
        private String id;
        private String customerId;
        private String timeStamp;
        private Double total;

        public OrderItem(String id, String customerId, String timeStamp, Double total) {
            this.id = id;
            this.customerId = customerId;
            this.timeStamp = timeStamp;
            this.total = total;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }
    }

}
