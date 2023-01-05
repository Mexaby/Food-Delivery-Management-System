package dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private int orderID;
    private Client client;
    private LocalDateTime dateTime;
    private List<MenuItem> items;

    public Order(int orderID, Client client, List<MenuItem> items) {
        this.orderID = orderID;
        this.client = client;
        this.dateTime = LocalDateTime.now();
        this.items = items;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public int getTotalPrice(){
        int price = 0;
        for (MenuItem item : items) {
            price += item.getPrice();
        }
        return price;
    }
}
