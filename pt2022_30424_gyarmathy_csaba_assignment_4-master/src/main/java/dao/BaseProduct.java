package dao;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable{

    public BaseProduct(String title, float rating, int calories, int proteins, int fats, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    public int calculateCalories() {
        return getCalories();
    }

    public int calculateProteins() {
        return getProteins();
    }

    public int calculateFats() {
        return getFats();
    }

    public int calculateSodium() {
        return getSodium();
    }

    public int calculatePrice() {
        return getPrice();
    }
}
