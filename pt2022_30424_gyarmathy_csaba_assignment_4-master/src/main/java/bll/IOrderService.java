package bll;

import dao.Client;
import dao.MenuItem;
import dao.Order;

import java.io.IOException;
import java.util.List;
/**
 * Interface that implements client operations
 */
public interface IOrderService {
    /**
     * method that creates report based on order
     * @param orders
     * @throws IOException
     */
    public void createOrderReport(List<Order> orders) throws IOException;

    /**
     * method that creates report based on items
     * @param menuItems
     * @throws IOException
     */
    public void createItemReport(List<MenuItem> menuItems) throws IOException;

    /**
     * method that creates an order
     * @param client
     * @param list
     * @throws IOException
     */
    public void createOrder(Client client, List<MenuItem> list) throws IOException;

    /**
     * method that creates bill
     * @param order
     * @throws IOException
     */
    public void createBill(Order order) throws IOException;
}
