package bll.util;

import dao.BaseProduct;
import dao.MenuItem;
import dao.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Util {
    public static JTable createMenuTable(DefaultTableModel myModel, String[] columnNames, List<MenuItem> myList) {
        myModel.setRowCount(0);
        myModel.setColumnIdentifiers(columnNames);
        for (MenuItem menuItem : myList) {
            createMenuRow(myModel, menuItem);
        }
        return new JTable(myModel);
    }

    public static JTable createOrderTable(DefaultTableModel myModel, String[] columnNames, List<Order> myList) {
        myModel.setRowCount(0);
        myModel.setColumnIdentifiers(columnNames);
        for (Order order : myList) {
            createOrderRow(myModel, order);
        }
        return new JTable(myModel);
    }

    public static void createMenuRow(DefaultTableModel myModel, MenuItem menuItem) {
        String[] row = {menuItem.getTitle(),
                String.valueOf(menuItem.getRating()),
                String.valueOf(menuItem.getCalories()),
                String.valueOf(menuItem.getProteins()),
                String.valueOf(menuItem.getFats()),
                String.valueOf(menuItem.getSodium()),
                String.valueOf(menuItem.getPrice())};
        myModel.addRow(row);
    }

    public static void createOrderRow(DefaultTableModel myModel, Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        String formatDateTime = order.getDateTime().format(formatter);

        String[] row = {String.valueOf(order.getOrderID()),
                String.valueOf(order.getClient().getClientID()),
                formatDateTime,
                String.valueOf(order.getTotalPrice())};
        myModel.addRow(row);
    }


    public static void reverse(int[] array) {

        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

    }



}

