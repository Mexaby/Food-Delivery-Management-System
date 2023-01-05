package bll;

import dao.MenuItem;
import dao.Serializator;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFilterService {
    DefaultTableModel myModel;
    List<MenuItem> productsList;

    public ProductFilterService() throws IOException, ClassNotFoundException {
        productsList = Serializator.deserializeMenu();
    }

    public List<MenuItem> filterByTitle(String titleFilter) {
        return productsList.stream().filter(product -> product.getTitle().toLowerCase().contains(titleFilter.toLowerCase())).collect(Collectors.toList());
    }

    public List<MenuItem> filterByRating(Float ratingFilterMin, Float ratingFilterMax) {
        return productsList.stream().filter(product -> product.getRating() >= (ratingFilterMin)).filter(product -> product.getRating() <= (ratingFilterMax)).collect(Collectors.toList());
    }

    public List<MenuItem> filterByCalories(int caloriesFilterMin, int caloriesFilterMax) {
        return productsList.stream().filter(product -> product.getCalories() >= (caloriesFilterMin)).filter(product -> product.getCalories() <= (caloriesFilterMax)).collect(Collectors.toList());
    }

    public List<MenuItem> filterBySodium(int sodiumFilterMin, int sodiumFilterMax) {
        return productsList.stream().filter(product -> product.getSodium() >= (sodiumFilterMin)).filter(product -> product.getSodium() <= (sodiumFilterMax)).collect(Collectors.toList());
    }

    public List<MenuItem> filterByFats(int fatFilterMin, int fatFilterMax) {
        return productsList.stream().filter(product -> product.getFats() >= (fatFilterMin)).filter(product -> product.getFats() <= (fatFilterMax)).collect(Collectors.toList());
    }

    public List<MenuItem> filterByProtein(int proteinFilterMin, int proteinFilterMax) {
        return productsList.stream().filter(product -> product.getProteins() >= (proteinFilterMin)).filter(product -> product.getProteins() <= (proteinFilterMax)).collect(Collectors.toList());
    }

    public List<MenuItem> filterByPrice(int priceFilterMin, int priceFilterMax) {
        return productsList.stream().filter(product -> product.getPrice() >= (priceFilterMin)).filter(product -> product.getPrice() <= (priceFilterMax)).collect(Collectors.toList());
    }

    public List<MenuItem> filterByNrTimesOrdered(int nrTimesOrdered){
        return productsList.stream().filter(product -> product.getNrOfTimesOrdered() >= nrTimesOrdered).collect(Collectors.toList());
    }
    public List<MenuItem> search(int choice, String nameText, String minText, String maxText) {
        List<MenuItem> filteredList = new ArrayList<>();
        int min, max;
        float minf, maxf;
        switch (choice) {
            case 0: //name
                String title = nameText;
                filteredList = filterByTitle(title);
                break;
            case 1: //rating
                minf = Float.parseFloat(minText);
                maxf = Float.parseFloat(maxText);

                filteredList = filterByRating(minf, maxf);
                break;
            case 2: //calories
                min = Integer.parseInt(minText);
                max = Integer.parseInt(maxText);

                filteredList = filterByCalories(min, max);
                break;
            case 3: //proteins
                min = Integer.parseInt(minText);
                max = Integer.parseInt(maxText);

                filteredList = filterByProtein(min, max);
                break;
            case 4: //fats
                min = Integer.parseInt(minText);
                max = Integer.parseInt(maxText);

                filteredList = filterByFats(min, max);
                break;
            case 5: //sodium
                min = Integer.parseInt(minText);
                max = Integer.parseInt(maxText);

                filteredList = filterBySodium(min, max);
                break;
            case 6: //price
                min = Integer.parseInt(minText);
                max = Integer.parseInt(maxText);

                filteredList = filterByPrice(min, max);
                break;
            default:
                break;
        }
        return  filteredList;
    }
}
