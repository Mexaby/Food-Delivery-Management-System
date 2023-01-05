import bll.DeliveryService;
import bll.OrderService;
import dao.Employee;
import presentation.LoginWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DeliveryService deliveryService = new DeliveryService();
        OrderService orderService = new OrderService();

        LoginWindow loginWindow = new LoginWindow(deliveryService, orderService);
    }


}
