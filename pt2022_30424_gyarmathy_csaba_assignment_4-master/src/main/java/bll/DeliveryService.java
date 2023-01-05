package bll;

import bll.util.Util;
import dao.BaseProduct;
import dao.CompositeItem;
import dao.MenuItem;
import dao.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliveryService implements IDeliveryServiceProcessing {
    private static String[] COLUMNS = {"Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price"};

    DefaultTableModel myModel;

    private List<MenuItem> productsList;
    private List<MenuItem> originalProductsList;

    public void setMyModel(DefaultTableModel myModel) {
        this.myModel = myModel;
    }

    public List<MenuItem> getProductsList() {
        return productsList;
    }


    public void setProductsList(List<MenuItem> productsList) {
        this.productsList = productsList;
    }

    public DeliveryService() {
        productsList = new ArrayList<>();
        readProducts();
    }


    @Override
    public void importProductList() {
        try {
            productsList = CSVFileReader.readFromCSV("products.csv");
            writeProducts();
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "products.csv not found");
        }
    }

    @Override
    public void createMenuItem(BaseProduct product) {
        productsList.add(product);
        Util.createMenuRow(myModel, product);
    }

    @Override
    public void createCompositeMenuItem(List<MenuItem> compositeList, String name, float rating) {
        CompositeItem compositeItem = new CompositeItem(compositeList, name, rating);
        productsList.add(compositeItem);

        Util.createMenuRow(myModel, compositeItem);
    }

    @Override
    public List<MenuItem> getProductsBasedOnIndices(int[] selectedIndexes) {
        List<MenuItem> compositeList = new ArrayList<>();

        Arrays.sort(selectedIndexes);
        Util.reverse(selectedIndexes);
        for (int i : selectedIndexes) {
            compositeList.add(getProductBasedOnIndex(i));
        }
        return compositeList;
    }

    public MenuItem getProductBasedOnIndex(int selectedIndex) {
        return productsList.get(selectedIndex);
    }

    @Override
    public void deleteMenuItem(int index) {
        productsList.remove(index);
        myModel.removeRow(index);
    }

    @Override
    public void editMenuItem(MenuItem menuItem, MenuItem modifiedMenuItem, int row) {
        menuItem.setTitle(modifiedMenuItem.getTitle());
        menuItem.setRating(modifiedMenuItem.getRating());
        menuItem.setCalories(modifiedMenuItem.getCalories());
        menuItem.setProteins(modifiedMenuItem.getProteins());
        menuItem.setFats(modifiedMenuItem.getSodium());
        menuItem.setSodium(modifiedMenuItem.getSodium());
        menuItem.setPrice(modifiedMenuItem.getPrice());

        myModel.setValueAt(modifiedMenuItem.getTitle(), row, 0);
        myModel.setValueAt(modifiedMenuItem.getRating(), row, 1);
        myModel.setValueAt(modifiedMenuItem.getCalories(), row, 2);
        myModel.setValueAt(modifiedMenuItem.getProteins(), row, 3);
        myModel.setValueAt(modifiedMenuItem.getFats(), row, 4);
        myModel.setValueAt(modifiedMenuItem.getSodium(), row, 5);
        myModel.setValueAt(modifiedMenuItem.getPrice(), row, 6);
    }

    public void writeProducts() {
        try {
            Serializator.serializeMenu(productsList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readProducts() {
        try {
            productsList = Serializator.deserializeMenu();
            originalProductsList = new ArrayList<>(productsList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please import from products.csv first");
        }
    }

    public JTable createProductsTable(){
        return Util.createMenuTable(myModel, COLUMNS, originalProductsList);
    }

    public JTable createProductsTable(List<MenuItem> list){
        return Util.createMenuTable(myModel, COLUMNS, list);
    }
}
