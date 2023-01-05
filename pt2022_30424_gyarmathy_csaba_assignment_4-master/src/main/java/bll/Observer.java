package bll;

import dao.Order;

public interface Observer {
    void notifyEvent(Order order);
}
