package dao;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializator {
    public static void serializeMenu(List<MenuItem> itemsList) throws IOException {
        FileOutputStream file = new FileOutputStream("menu.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(itemsList);

        out.close();
        file.close();

        System.out.println("Serialization complete");
    }

    public static List<MenuItem> deserializeMenu() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("menu.ser");
        ObjectInputStream in = new ObjectInputStream(file);

        List<MenuItem> itemsList = (ArrayList<MenuItem>) in.readObject();

        in.close();
        file.close();

        System.out.println("Deserialization complete");

        return itemsList;
    }
    public static void serializeClient(List<Client> clientList) throws IOException {
        FileOutputStream file = new FileOutputStream("clients.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(clientList);

        out.close();
        file.close();

        System.out.println("Serialization complete");
    }

    public static List<Client> deserializeClient() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("clients.ser");
        ObjectInputStream in = new ObjectInputStream(file);

        List<Client> cleintList = (ArrayList<Client>) in.readObject();

        in.close();
        file.close();

        System.out.println("Deserialization complete");

        return cleintList;
    }

    public static void serializeOrders(List<Order> orders) throws IOException {
        FileOutputStream file = new FileOutputStream("orders.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(orders);

        out.close();
        file.close();

        System.out.println("Serialization complete");
    }

    public static List<Order> deserializeOrders() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("orders.ser");
        ObjectInputStream in = new ObjectInputStream(file);

        List<Order> orders = (ArrayList<Order>) in.readObject();

        in.close();
        file.close();

        System.out.println("Deserialization complete");

        return orders;
    }
}
