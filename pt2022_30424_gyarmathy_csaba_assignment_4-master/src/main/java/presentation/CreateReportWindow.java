package presentation;

import bll.OrderService;
import dao.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class CreateReportWindow {
    String options[] = {"Time interval", "Number of ordered products", "Clients and value"};

    private JTextField minText;
    private JTextField maxText;
    private JButton createReportButton;

    private JComboBox<String> menuBox;
    public CreateReportWindow(OrderService orderService) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(310, 330);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);

        menuBox = new JComboBox<>(options);
        menuBox.setBounds(30, 20, 235, 25);
        panel.add(menuBox);

        minText = new JTextField();
        minText.setBounds(80, 70, 50, 25);
        panel.add(minText);

        maxText = new JTextField();
        maxText.setBounds(155, 70, 50, 25);
        panel.add(maxText);

        createReportButton = new JButton("Generate report");
        createReportButton.setBounds(70, 120, 155, 25);
        panel.add(createReportButton);
        createReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = menuBox.getSelectedIndex();
                try {
                    orderService.generateReport(choice, minText.getText(), maxText.getText());
                    JOptionPane.showMessageDialog(null,"Successfully generated");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.setVisible(true);
    }
}
