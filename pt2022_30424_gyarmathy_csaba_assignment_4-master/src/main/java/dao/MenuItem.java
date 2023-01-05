package dao;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    protected String title;
    protected float rating;
    protected int calories;
    protected int proteins;
    protected int fats;
    protected int sodium;
    protected int price;
    protected int nrOfTimesOrdered = 0;

    public abstract int calculateCalories();

    public abstract int calculateProteins();

    public abstract int calculateFats();

    public abstract int calculateSodium();

    public abstract int calculatePrice();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNrOfTimesOrdered() {
        return nrOfTimesOrdered;
    }

    public void incrementNrOfTimesOrdered(){
        this.nrOfTimesOrdered++;
    }

    @Override
    public String toString() {
        return "name: " + title + ", rating: " + rating + ", calories: "
                + calories + ", proteins: " + proteins + ", fats: " + fats + ", sodium: " + sodium + ", price: " + price;
    }
}
