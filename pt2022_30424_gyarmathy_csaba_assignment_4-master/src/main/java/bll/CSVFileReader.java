package bll;

import dao.BaseProduct;
import dao.MenuItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
    public static List<MenuItem> readFromCSV(String fileName) throws FileNotFoundException {
        List<MenuItem> products = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        FileReader file = new FileReader(fileName);
        try (BufferedReader br = new BufferedReader(file)) {
            String line = br.readLine();
            line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");

                BaseProduct product = createProduct(attributes);
                if(isUnique(product, products)){
                    products.add(product);
                }

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return products;
    }

    private static BaseProduct createProduct(String[] metadata) {
        String title = metadata[0];
        float rating = Float.parseFloat(metadata[1]);
        int calories = Integer.parseInt(metadata[2]);
        int proteins = Integer.parseInt(metadata[3]);
        int fats = Integer.parseInt(metadata[4]);
        int sodium = Integer.parseInt(metadata[5]);
        int price = Integer.parseInt(metadata[6]);

        return new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
    }

    public static boolean isUnique(BaseProduct baseProduct, List<MenuItem> menuItems){
        for(MenuItem item : menuItems){
            if(item.getTitle().equals(baseProduct.getTitle())){
                return false;
            }
        }
        return true;
    }
}
