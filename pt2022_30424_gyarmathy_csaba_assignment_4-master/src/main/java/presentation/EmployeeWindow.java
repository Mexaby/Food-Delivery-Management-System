package presentation;

import bll.OrderService;
import bll.util.Util;
import dao.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;
import java.util.Observer;

public class EmployeeWindow {
    private static String[] COLUMNS = {"OrderID", "ClientID", "Date", "Value"};
    OrderService orderService;

    private JTable ordersTable;

    public EmployeeWindow() throws IOException, ClassNotFoundException {
        orderService = new OrderService();
        DefaultTableModel myModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        orderService.readOrders();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(750, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);

        List<Order> orderList = orderService.getOrderList();

        if(!orderList.isEmpty()) {
            ordersTable = Util.createOrderTable(myModel, COLUMNS, orderList);
            JScrollPane myScrollPane = new JScrollPane();
            myScrollPane.setBounds(50, 50, 600, 400);
            myScrollPane.setViewportView(ordersTable);
            panel.add(myScrollPane);
        }

        frame.setVisible(true);

    }
}
