package pl.jasiek.project.repository.csv.loadData;

import pl.jasiek.project.model.Order.Order;
import pl.jasiek.project.model.Products.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Recorder {
    private static final String PATH_TO_PRODUCTS = "C:\\Users\\user\\Desktop\\kfc_repo\\productsRepo.csv";
    private static final String PATH_TO_ORDERS = "C:\\Users\\user\\Desktop\\kfc_repo\\ordersRepo.csv";

    public static void saveProducts(List<Product> products) {
        try {
            Path path = Paths.get(PATH_TO_PRODUCTS);
            StringBuilder productsBuilder = new StringBuilder();
            for (Product p : products) {
                switch (p.getGroup()) {
                    case SIMPLE_PRODUCT:
                        SimpleProduct simpleProduct = (SimpleProduct) p;
                        productsBuilder.append((simpleProduct.toStringCsv())).append("\n");
                        break;
                    case BOX:
                        Box box = (Box) p;
                        productsBuilder.append((box.toStringCsv())).append("\n");
                        break;
                    case BUCKET:
                        Bucket bucket = (Bucket) p;
                        productsBuilder.append((bucket.toStringCsv())).append("\n");
                        break;
                    case SALAD:
                        Salad salad = (Salad) p;
                        productsBuilder.append((salad.toStringCsv())).append("\n");
                        break;
                    case SHAKE:
                        Shake shake = (Shake) p;
                        productsBuilder.append((shake.toStringCsv())).append("\n");
                        break;
                }
                Files.write(path, productsBuilder.toString().lines().collect(Collectors.toList()));
            }
        } catch (IOException e) {
            System.out.println("SAVE PRODUCTS: Error with repo!");
        }
    }

    public static void saveOrders(List<Order> orders) {
        try {
            Path path = Paths.get(PATH_TO_ORDERS);
            StringBuilder stringBuilder = new StringBuilder();
            for (Order o : orders) {
                stringBuilder.append(o.toStringCsv()).append("\n");
            }
            Files.write(path, stringBuilder.toString()
                    .lines()
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println("SAVE ORDERS: Error with repo!");
        } catch (NullPointerException e) {
            System.out.println("SAVE ORDERS: Error with data in repo!");
        }
    }
}
