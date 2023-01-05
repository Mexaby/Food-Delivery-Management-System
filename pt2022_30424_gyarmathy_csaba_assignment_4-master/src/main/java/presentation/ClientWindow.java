package presentation;

import bll.DeliveryService;
import bll.OrderService;
import bll.ProductFilterService;
import bll.util.IDGenerator;
import dao.Client;
import dao.MenuItem;
import dao.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ClientWindow {
    private JTable productsTable;
    private JLabel titleLabel;
    private JButton createOrderButton;
    private JButton searchButton;

    String options[] = {"Name", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price"};

    private JComboBox<String> menuBox;
    private JTextField nameText;
    private JTextField minText;
    private JTextField maxText;
    private JButton resetTableButton;


    public ClientWindow(DeliveryService deliveryService, Client client, OrderService orderService) throws IOException, ClassNotFoundException {
        DefaultTableModel myModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        deliveryService.setMyModel(myModel);

        ProductFilterService productFilterService = new ProductFilterService();
        deliveryService.readProducts();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(850, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);
        JScrollPane myScrollPane = new JScrollPane();

        if (!deliveryService.getProductsList().isEmpty()) {
            productsTable = deliveryService.createProductsTable();

            myScrollPane.setBounds(200, 50, 600, 400);
            myScrollPane.setViewportView(productsTable);
            panel.add(myScrollPane);
        }

        titleLabel = new JLabel("Logged in as client");
        titleLabel.setBounds(330, 5, 450, 50);
        panel.add(titleLabel);

        createOrderButton = new JButton("Create order");
        createOrderButton.setBounds(50, 50, 125, 50);
        panel.add(createOrderButton);
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndexes = productsTable.getSelectedRows();

                if (selectedIndexes.length > 0) {
                    List<MenuItem> list = deliveryService.getProductsBasedOnIndices(selectedIndexes);
                    list.forEach(MenuItem::incrementNrOfTimesOrdered);
                    try {
                        orderService.createOrder(client, list);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    orderService.writeOrders();
                    JOptionPane.showMessageDialog(null, "Successfully created");
                } else {
                    JOptionPane.showMessageDialog(null, "No items selected");
                }
            }
        });

        resetTableButton = new JButton("Reset table");
        resetTableButton.setBounds(50, 120, 125, 50);
        panel.add(resetTableButton);
        resetTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productsTable = deliveryService.createProductsTable();
                myScrollPane.setViewportView(productsTable);
            }
        });

        searchButton = new JButton("Search items");
        searchButton.setBounds(50, 200, 125, 50);
        panel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = menuBox.getSelectedIndex();
                List<MenuItem> filteredList = productFilterService.search(choice, nameText.getText(), minText.getText(), maxText.getText());
                productsTable = deliveryService.createProductsTable(filteredList);
                myScrollPane.setViewportView(productsTable);
                deliveryService.setProductsList(filteredList);
            }
        });

        menuBox = new JComboBox<>(options);
        menuBox.setBounds(50, 270, 125, 25);
        panel.add(menuBox);

        nameText = new JTextField();
        nameText.setBounds(50, 320, 125, 25);
        nameText.setText("name filter");
        panel.add(nameText);

        minText = new JTextField();
        minText.setBounds(50, 370, 50, 25);
        minText.setText("min");
        panel.add(minText);

        maxText = new JTextField();
        maxText.setBounds(125, 370, 50, 25);
        maxText.setText("max");
        panel.add(maxText);

        frame.setVisible(true);
    }
}
