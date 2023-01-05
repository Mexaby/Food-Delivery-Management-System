package dao;

import bll.Observer;

public class Employee implements Observer {
    private String username;
    private String password;

    public Employee() {
        this.username = "empl";
        this.password = "123";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public void notifyEvent(Order order) {
            System.out.println("Order " + order.getOrderID() + " has been placed");
    }
}
