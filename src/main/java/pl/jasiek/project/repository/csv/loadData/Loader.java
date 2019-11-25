package pl.jasiek.project.repository.csv.loadData;

import pl.jasiek.project.model.Order.Order;
import pl.jasiek.project.model.Products.*;
import pl.jasiek.project.model.SemifinishedProducts.*;
import pl.jasiek.project.repository.csv.ProductCsvRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Loader {
    private final ProductCsvRepo productCsvRepo;

    public Loader(ProductCsvRepo productCsvRepo) {
        this.productCsvRepo = productCsvRepo;
    }

    public Order loadOrder(String[] line) {
        Order order = new Order();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        order.setDateTimeCreate(LocalDateTime.parse(line[1], timeFormatter));
        List<Product> tmpProducts = new LinkedList<>();
        for (String s : getProductsNamesFromLine(line[2])) {
            tmpProducts.add(this.productCsvRepo.findByName(s));
        }
        order.setProducts(tmpProducts);
        order.setFinalPrice(Double.parseDouble(line[3]));
        order.setName(line[4]);

        return order;
    }

    public static SimpleProduct loadSimpleProduct(String[] line) {
        SimpleProduct simpleProduct = new SimpleProduct();
//        simpleProduct.setId(Long.parseLong(line[0]));
        simpleProduct.setGroup(Groups.SIMPLE_PRODUCT);
        simpleProduct.setName(line[2]);
        simpleProduct.setPrice(Double.parseDouble(line[3]));
        return simpleProduct;
    }

    public static Box loadBox(String[] line) {
        Box box = new Box();
//        box.setId(Long.parseLong(line[0]));
        box.setGroup(Groups.BOX);
        box.setName(line[2]);
        box.setPrice(Double.parseDouble(line[3]));
        box.setSandwich(Sandwiches.valueOf(line[4]));
        box.setContains(getChickens(line[5]));
        box.setAdditions(getExtension(line[6]));
        return box;
    }

    public static Bucket loadBucket(String[] line) {
        Bucket bucket = new Bucket();
        bucket.setGroup(Groups.BUCKET);
        bucket.setName(line[2]);
        bucket.setPrice(Double.parseDouble(line[3]));
        bucket.setContains(getChickens(line[4]));
        bucket.setAdditions(getExtension(line[5]));
        return bucket;
    }

    public static Salad loadSalad(String[] line) {
        Salad salad = new Salad();
        salad.setGroup(Groups.SALAD);
        salad.setName(line[2]);
        salad.setPrice(Double.parseDouble(line[3]));
        salad.setChicken(Chicken.valueOf(line[4]));
        salad.setVegetables(getVegetables(line[5]));
        salad.setSauce(Sauces.valueOf(line[6]));
        return salad;
    }

    public static Shake loadShake(String[] line) {
        Shake shake = new Shake();
        shake.setGroup(Groups.SHAKE);
        shake.setName(line[2]);
        shake.setPrice(Double.parseDouble(line[3]));
        shake.setCapacity(Integer.parseInt(line[4]));
        shake.setTaste(TasteOfShake.valueOf(line[5]));
        return shake;
    }

    private static Map<Chicken, Integer> getChickens(String partOfLine) {
        String tmpChickens = partOfLine.substring(9, partOfLine.length()-1);
        String[] chickensArray = tmpChickens.split(":");
        Map<Chicken, Integer> chickens = new HashMap<>();
        for (String s : chickensArray) {
            String[] chickenAndAmount = s.split("-");
            chickens.put(Chicken.valueOf(chickenAndAmount[0]), Integer.parseInt(chickenAndAmount[1]));
        }
        return chickens;
    }

    private static List<Extension> getExtension(String partOfLine) {
        String tmpAdditions = partOfLine.substring(10, partOfLine.length()-1);
        String[] additionsArray = tmpAdditions.split(":");
        List<Extension> additions = new LinkedList<>();
        for (String s : additionsArray) {
            additions.add(Extension.valueOf(s));
        }
        return additions;
    }

    private static List<Vegetable> getVegetables(String partOfLine) {
        String tmpVegetables = partOfLine.substring(11, partOfLine.length()-1);
        String[] vegetablesArray = tmpVegetables.split(":");
        List<Vegetable> vegetables = new LinkedList<>();
        for (String s : vegetablesArray) {
            vegetables.add(Vegetable.valueOf(s));
        }
        return vegetables;
    }

    private static List<String> getProductsNamesFromLine(String partOfLine) {
        String tmpProducts = partOfLine.substring(9, partOfLine.length()-1);
        String[] productsArray = tmpProducts.split(":");

        return Arrays.asList(productsArray);
    }
}
