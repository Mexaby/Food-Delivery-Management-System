package presentation;

import bll.CSVFileReader;
import bll.DeliveryService;
import bll.OrderService;
import bll.util.Util;
import dao.MenuItem;
import dao.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class AdminWindow extends JFrame {

    private JLabel titleLabel;
    private JTable productsTable;
    private JButton createNewItemButton;
    private JButton createCompositeItemButton;
    private JButton deleteItemButton;
    private JButton editItemButton;
    private JButton importButton;
    private JButton createReportButton;

    public AdminWindow(DeliveryService deliveryService, OrderService orderService) throws IOException, ClassNotFoundException {

        DefaultTableModel myModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        deliveryService.setMyModel(myModel);

        deliveryService.readProducts();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(850, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);

        if (!deliveryService.getProductsList().isEmpty()) {
            productsTable = deliveryService.createProductsTable();
            JScrollPane myScrollPane = new JScrollPane();
            myScrollPane.setBounds(200, 50, 600, 400);
            myScrollPane.setViewportView(productsTable);
            panel.add(myScrollPane);
        }

        titleLabel = new JLabel("Logged in as administrator");
        titleLabel.setBounds(330, 5, 450, 50);
        panel.add(titleLabel);

        createNewItemButton = new JButton("New item");
        createNewItemButton.setBounds(50, 50, 125, 50);
        panel.add(createNewItemButton);
        createNewItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNewItemWindow(deliveryService);
            }
        });

        createCompositeItemButton = new JButton("Compose item");
        createCompositeItemButton.setBounds(50, 120, 125, 50);
        panel.add(createCompositeItemButton);
        createCompositeItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int[] selectedIndexes = productsTable.getSelectedRows();

                if (selectedIndexes.length > 0) {
                    List<MenuItem> compositeList = deliveryService.getProductsBasedOnIndices(selectedIndexes);
                    new CreateCompositeItemWindow(compositeList, deliveryService);

                } else {
                    JOptionPane.showMessageDialog(null, "No items selected");
                }
            }
        });

        deleteItemButton = new JButton("Delete item");
        deleteItemButton.setBounds(50, 190, 125, 50);
        panel.add(deleteItemButton);
        deleteItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedItemNr = 0;
                int[] selectedIndices = productsTable.getSelectedRows();
                if (selectedIndices.length > 0) {

                    Arrays.sort(selectedIndices);
                    Util.reverse(selectedIndices);
                    for (int i : selectedIndices) {
                        selectedItemNr++;
                        deliveryService.deleteMenuItem(i);
                    }

                    if (selectedItemNr == 1) {
                        JOptionPane.showMessageDialog(null, "Successfully deleted one item");
                    } else if (selectedItemNr > 1) {
                        JOptionPane.showMessageDialog(null, "Successfully deleted " + selectedItemNr + " items");
                    }

                    deliveryService.writeProducts();

                } else {
                    JOptionPane.showMessageDialog(null, "No items selected");
                }
            }
        });


        editItemButton = new JButton("Edit item");
        editItemButton.setBounds(50, 260, 125, 50);
        panel.add(editItemButton);
        editItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new EditWindow(deliveryService, productsTable.getSelectedRow());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No item selected");
                }
            }
        });

        importButton = new JButton("Import items");
        importButton.setBounds(50, 330, 125, 50);
        panel.add(importButton);
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "This will overwrite all new products. Do you wish to proceed?");
                if (confirm == 0) {
                    deliveryService.importProductList();
                    productsTable = deliveryService.createProductsTable();
                }
            }
        });

        createReportButton = new JButton("Create report");
        createReportButton.setBounds(50, 400, 125, 50);
        panel.add(createReportButton);
        createReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateReportWindow(orderService);
            }
        });

        frame.setVisible(true);

    }


}
