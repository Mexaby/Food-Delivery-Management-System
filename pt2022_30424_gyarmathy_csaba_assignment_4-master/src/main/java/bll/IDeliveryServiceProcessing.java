package bll;

import dao.BaseProduct;
import dao.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface with administrator operations
 */
public interface IDeliveryServiceProcessing {
    /**
     * Method to import the initial set of products from products.csv
     */
    public void importProductList();

    /**
     * Method to create a new product
     * @param product
     */
    public void createMenuItem(BaseProduct product);

    /**
     * Methos do create a new product composed of more products
     * @param compositeList
     * @param name
     * @param rating
     */
    public void createCompositeMenuItem(List<MenuItem> compositeList, String name, float rating);

    /**
     * Returns the list of products based on selected indices
     * @param indexes
     * @return
     */
    public List<MenuItem> getProductsBasedOnIndices(int[] indexes);

    /**
     * Method to delete item based on index from item list
     * @param index
     */
    public void deleteMenuItem(int index);

    /**
     * Method to edit menu item
     * @param menuItem
     * @param modifiedMenuItem
     * @param row
     */
    public void editMenuItem(MenuItem menuItem, MenuItem modifiedMenuItem, int row);

}
