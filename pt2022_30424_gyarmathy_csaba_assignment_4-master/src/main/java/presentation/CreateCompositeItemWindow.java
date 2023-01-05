package presentation;

import bll.DeliveryService;
import bll.util.Util;
import dao.CompositeItem;
import dao.MenuItem;
import dao.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateCompositeItemWindow extends JFrame {

    private final JLabel nameLabel;
    private final JTextField nameText;
    private final JLabel ratingLabel;
    private final JTextField ratingText;
    private final JButton finishButton;


    public CreateCompositeItemWindow(List<MenuItem> compositeList, DeliveryService deliveryService) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(310, 200);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);

        nameLabel = new JLabel("Title:");;
        nameLabel.setBounds(20, 20, 80, 25);
        panel.add(nameLabel);

        nameText = new JTextField(20);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);

        ratingLabel = new JLabel("Rating:");;
        ratingLabel.setBounds(20, 50, 80, 25);
        panel.add(ratingLabel);

        ratingText = new JTextField(20);
        ratingText.setBounds(100, 50, 165, 25);
        panel.add(ratingText);

        finishButton = new JButton("Save");
        finishButton.setBounds(70, 100, 150, 25);
        panel.add(finishButton);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deliveryService.createCompositeMenuItem(compositeList, nameText.getText(), Float.parseFloat(ratingText.getText()));

                    JOptionPane.showMessageDialog(null,"Successfully added");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Invalid input");

                }

                deliveryService.writeProducts();
            }
        });

        frame.setVisible(true);

    }
}
