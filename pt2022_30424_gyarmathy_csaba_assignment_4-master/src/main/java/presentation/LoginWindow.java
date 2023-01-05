package presentation;

import bll.DeliveryService;
import bll.OrderService;
import dao.Client;
import dao.Employee;
import dao.Order;
import dao.Serializator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginWindow {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginButton;
    private static JButton registerButton;

    private static List<Client> clientList;

    public LoginWindow(DeliveryService deliveryService, OrderService orderService) throws IOException, ClassNotFoundException {
        Employee employee = new Employee();
        orderService.addObserver(employee);

        clientList = new ArrayList<>();
        clientList = Serializator.deserializeClient();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(310, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);

        userLabel = new JLabel("Username");
        userLabel.setBounds(20, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(90, 90, 120, 40);
        panel.add(loginButton);

        userLabel = new JLabel("New client?");
        userLabel.setBounds(115, 160, 150, 25);
        panel.add(userLabel);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 190, 100, 30);
        panel.add(registerButton);

        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = passwordText.getText();

                System.out.println(username + ", " + password);

                if (username.equals("admin") && password.equals("admin")) {
                    try {
                        new AdminWindow(deliveryService, orderService);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if(username.equals(employee.getUsername()) && password.equals(employee.getPassword())){
                    try {
                        new EmployeeWindow();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    Client client = null;

                    for (Client cl : clientList) {
                        if (username.equals(cl.getUsername()) && password.equals(cl.getPassword())) {
                            client = cl;
                            break;
                        }
                    }
                    if(client == null){
                        JOptionPane.showMessageDialog(null, "Invalid username or password");

                    } else {
                        try {
                            new ClientWindow(deliveryService, client, orderService);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }


            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterWindow(clientList);
            }
        });
    }
}
