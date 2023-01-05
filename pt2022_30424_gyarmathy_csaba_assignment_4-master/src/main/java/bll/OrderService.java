package bll;

import bll.util.IDGenerator;
import dao.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService implements IOrderService{
    private List<Order> orderList;
    private List<Observer> observerList;
    private ProductFilterService productFilterService;

    public OrderService() throws IOException, ClassNotFoundException {
        productFilterService = new ProductFilterService();
        observerList = new ArrayList<>();
        orderList = new ArrayList<>();
        readOrders();

    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) throws IOException {
        orderList.add(order);
        createBill(order);
        observerList.forEach(observer -> observer.notifyEvent(order));
    }

    public List<Order> filterByTime(int startHour, int endHour) {
        return orderList.stream().filter(order -> order.getDateTime().getHour() >= startHour).filter(order -> order.getDateTime().getHour() <= endHour).collect(Collectors.toList());
    }

    public List<Order> filterByClientAndValue(int nrTimesOrdered, int orderValue) {
        return orderList.stream().filter(order -> order.getClient().getNrTimesOrdered() >= nrTimesOrdered).filter(order -> order.getTotalPrice() >= orderValue).collect(Collectors.toList());
    }

    public void writeOrders() {
        try {
            Serializator.serializeOrders(orderList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readOrders() {
        try {
            orderList = Serializator.deserializeOrders();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Please import from orders.csv first");
        }
    }

    @Override
    public void createOrderReport(List<Order> orderList) throws IOException {
        PrintWriter writer = new PrintWriter("Report.txt", StandardCharsets.UTF_8);
        for (Order order : orderList) {
            writer.println("Order ID: " + order.getOrderID());
            writer.println("Client ID: " + order.getClient().getClientID());
            writer.println("Client username: " + order.getClient().getUsername());
            writer.println("Order date: " + order.getDateTime());
            writer.println("Value: " + order.getTotalPrice());
            writer.println("");
        }
        writer.close();
    }

    public void createItemReport(List<MenuItem> products) throws IOException {
        PrintWriter writer = new PrintWriter("Report.txt", StandardCharsets.UTF_8);
        for (MenuItem product : products) {
            writer.println("Product name: " + product.getTitle());
            writer.println("Number of times ordered: " + product.getNrOfTimesOrdered());
            writer.println("");
        }
        writer.close();
    }

    @Override
    public void createOrder(Client client, List<MenuItem> list) throws IOException {
        Order order = new Order(IDGenerator.getNextID(), client, list);
        client.setNrTimesOrdered(client.getNrTimesOrdered() + 1);
        addOrder(order);
    }

    @Override
    public void createBill(Order order) throws IOException {
        PrintWriter writer = new PrintWriter("Order" + order.getOrderID()+".txt", StandardCharsets.UTF_8);
            writer.println("Order ID: " + order.getOrderID());
            writer.println("Client name: " + order.getClient().getUsername());
            writer.println("Items: ");
            for(MenuItem product : order.getItems()){
                writer.println(product.getTitle());
                writer.println("    Price: " + product.getPrice());
            }
            writer.println("Total value: " + order.getTotalPrice());
        writer.close();
    }

    public void generateReport(int choice, String firstValue, String secondValue) throws IOException {
        switch (choice) {
            case 0:
                createOrderReport(filterByTime(Integer.parseInt(firstValue), Integer.parseInt(secondValue)));
                break;
            case 1:
                createItemReport(productFilterService.filterByNrTimesOrdered(Integer.parseInt(firstValue)));
                break;
            case 2:
                createOrderReport(filterByClientAndValue(Integer.parseInt(firstValue), Integer.parseInt(secondValue)));
                break;
            default:
                break;
        }
    }

    public void addObserver(Employee employee){
        observerList.add(employee);
    }
}
