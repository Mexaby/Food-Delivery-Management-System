package dao;

import java.util.ArrayList;
import java.util.List;

public class CompositeItem extends MenuItem {
    private List<MenuItem> productList;

    public CompositeItem(List<MenuItem> productList, String name, float rating) {
        this.productList = productList;
        this.title = name;
        this.rating = rating;
        this.calories = calculateCalories();
        this.fats = calculateFats();
        this.sodium = calculateSodium();
        this.proteins = calculateProteins();
        this.price = calculatePrice();
    }

    public int calculateCalories() {
        int cal = 0;
        for (MenuItem currentItem : productList) {
            cal += currentItem.getCalories();
        }
        return cal;
    }

    public int calculateProteins() {
        int prot = 0;
        for (MenuItem currentItem : productList) {
            prot += currentItem.getProteins();
        }
        return prot;
    }

    public int calculateFats() {
        int fat = 0;
        for (MenuItem currentItem : productList) {
            fat += currentItem.getFats();
        }
        return fat;
    }

    public int calculateSodium() {
        int sod = 0;
        for (MenuItem currentItem : productList) {
            sod += currentItem.getSodium();
        }
        return sod;
    }

    public int calculatePrice() {
        int price = 0;
        for (MenuItem currentItem : productList) {
            price += currentItem.getPrice();
        }
        return price;
    }
}
