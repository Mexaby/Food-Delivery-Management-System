package presentation;

import bll.DeliveryService;
import dao.BaseProduct;
import dao.MenuItem;
import dao.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class EditWindow {
    private JLabel nameLabel;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinsLabel;
    private JLabel fatsLabel;
    private JLabel sodiumLabel;
    private JLabel priceLabel;

    private JTextField nameText;
    private JTextField ratingText;
    private JTextField caloriesText;
    private JTextField proteinsText;
    private JTextField fatsText;
    private JTextField sodiumText;
    private JTextField priceText;

    private JButton saveButton;

    public EditWindow(DeliveryService deliveryService, int row) {

        MenuItem menuItem = deliveryService.getProductBasedOnIndex(row);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(310, 330);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);

        nameLabel = new JLabel("Title:");
        nameLabel.setBounds(20, 20, 80, 25);
        panel.add(nameLabel);

        nameText = new JTextField(20);
        nameText.setBounds(100, 20, 165, 25);
        nameText.setText(menuItem.getTitle());
        panel.add(nameText);

        ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(20, 50, 80, 25);
        panel.add(ratingLabel);

        ratingText = new JTextField(20);
        ratingText.setBounds(100, 50, 165, 25);
        ratingText.setText(String.valueOf(menuItem.getRating()));
        panel.add(ratingText);

        caloriesLabel = new JLabel("Calories: ");
        caloriesLabel.setBounds(20, 80, 80, 25);
        panel.add(caloriesLabel);

        caloriesText = new JTextField(20);
        caloriesText.setBounds(100, 80, 165, 25);
        caloriesText.setText(String.valueOf(menuItem.getCalories()));
        panel.add(caloriesText);

        proteinsLabel = new JLabel("Proteins:");
        proteinsLabel.setBounds(20, 110, 80, 25);
        panel.add(proteinsLabel);

        proteinsText = new JTextField(20);
        proteinsText.setBounds(100, 110, 165, 25);
        proteinsText.setText(String.valueOf(menuItem.getProteins()));
        panel.add(proteinsText);

        fatsLabel = new JLabel("Fats:");
        fatsLabel.setBounds(20, 140, 80, 25);
        panel.add(fatsLabel);

        fatsText = new JTextField(20);
        fatsText.setBounds(100, 140, 165, 25);
        fatsText.setText(String.valueOf(menuItem.getFats()));
        panel.add(fatsText);

        sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setBounds(20, 170, 80, 25);
        panel.add(sodiumLabel);

        sodiumText = new JTextField(20);
        sodiumText.setBounds(100, 170, 165, 25);
        sodiumText.setText(String.valueOf(menuItem.getSodium()));
        panel.add(sodiumText);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 200, 80, 25);
        panel.add(priceLabel);

        priceText = new JTextField(20);
        priceText.setBounds(100, 200, 165, 25);
        priceText.setText(String.valueOf(menuItem.getPrice()));
        panel.add(priceText);

        saveButton = new JButton("Save");
        saveButton.setBounds(70, 240, 150, 25);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MenuItem modifiedMenuItem = new BaseProduct(nameText.getText(),
                            Float.parseFloat(ratingText.getText()),
                            Integer.parseInt(caloriesText.getText()),
                            Integer.parseInt(proteinsText.getText()),
                            Integer.parseInt(fatsText.getText()),
                            Integer.parseInt(sodiumText.getText()),
                            Integer.parseInt(priceText.getText()));

                    deliveryService.editMenuItem(menuItem, modifiedMenuItem, row);

                    JOptionPane.showMessageDialog(null, "Successfully edited");


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
                deliveryService.writeProducts();
            }
        });

        frame.setVisible(true);
    }
}
