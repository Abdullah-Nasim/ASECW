package models;

import java.util.ArrayList;
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

    public String id;
    public String customerId;
    public String timeStamp;
    public Double total;
    public LinkedList<Product> products = new LinkedList<>();

}
