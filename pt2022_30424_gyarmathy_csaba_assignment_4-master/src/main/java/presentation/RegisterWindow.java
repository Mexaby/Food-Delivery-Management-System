package presentation;
import bll.util.IDGenerator;
import dao.Client;
import dao.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

class RegisterWindow extends JFrame implements ActionListener {

    private Container panel;
    private JLabel title;
    private JLabel nameLabel;
    private JTextField nameText;
    private JLabel passLabel;
    private JTextField passText;
    private JLabel genderLabel;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dateOfBirthLabel;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JLabel addressLabel;
    private JTextArea addressText;
    private JCheckBox termsAndConditions;
    private JButton submitButton;
    private JButton resetButton;
    private JLabel res;

    public List<Client> clientList;

    private String dates[]
            = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String months[]
            = {"Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec"};
    private String years[]
            = {"1987", "1988", "1989", "1990",
            "1991", "1992", "1993", "1994",
            "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004"};

    public RegisterWindow(List<Client> clientList) {
        setTitle("Registration Form");
        setBounds(300, 90, 410, 600);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);

        panel = getContentPane();
        panel.setLayout(null);

        this.clientList = clientList;

        title = new JLabel("Registration");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(100, 30, 300, 35);;
        panel.add(title);

        nameLabel = new JLabel("Username");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setBounds(50, 100, 100, 20);
        panel.add(nameLabel);

        nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 15));
        nameText.setBounds(150, 100, 190, 20);
        panel.add(nameText);

        passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passLabel.setBounds(50, 150, 100, 20);
        panel.add(passLabel);

        passText = new JPasswordField();
        passText.setBounds(150, 150, 190, 20);
        panel.add(passText);

        genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        genderLabel.setBounds(50, 200, 100, 20);
        panel.add(genderLabel);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setBounds(150, 200, 75, 20);
        panel.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setBounds(225, 200, 80, 20);
        panel.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dateOfBirthLabel = new JLabel("Birthday");
        dateOfBirthLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dateOfBirthLabel.setBounds(50, 250, 120, 20);
        panel.add(dateOfBirthLabel);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(150, 250);
        panel.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(210, 250);
        panel.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(280, 250);
        panel.add(year);

        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        addressLabel.setBounds(50, 300, 100, 20);
        panel.add(addressLabel);

        addressText = new JTextArea();
        addressText.setFont(new Font("Arial", Font.PLAIN, 15));
        addressText.setBounds(150, 300, 190, 75);
        addressText.setLineWrap(true);
        panel.add(addressText);

        termsAndConditions = new JCheckBox("I have read the Terms And Conditions.");
        termsAndConditions.setFont(new Font("Arial", Font.PLAIN, 15));
        termsAndConditions.setBounds(50, 400, 400, 20);
        panel.add(termsAndConditions);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setBounds(50, 450, 100, 20);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setBounds(130, 450, 100, 20);
        resetButton.addActionListener(this);
        panel.add(resetButton);


        res = new JLabel("");
        res.setSize(500, 25);
        res.setLocation(70, 500);
        panel.add(res);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            if (termsAndConditions.isSelected()) {

                Client client = new Client(IDGenerator.getNextID(), nameText.getText(), passText.getText());
                clientList.add(client);
                System.out.println(client);

                try {
                    Serializator.serializeClient(clientList);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                res.setText("Registration successful");

            } else {
                res.setText("Please accept the"
                        + " Terms & Conditions.");
            }
        } else if (e.getSource() == resetButton) {
            String def = "";
            nameText.setText(def);
            addressText.setText(def);
            passText.setText(def);
            res.setText(def);
            termsAndConditions.setSelected(false);
            date.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
        }
    }
}
