package pl.jasiek.project.repository.csv;

import pl.jasiek.project.model.Products.Groups;
import pl.jasiek.project.model.Products.Product;
import pl.jasiek.project.repository.ProductRepository;
import pl.jasiek.project.repository.csv.loadData.Loader;
import pl.jasiek.project.repository.csv.loadData.Recorder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCsvRepo implements ProductRepository {
    private static final String PATH_TO_PRODUCTS = "C:\\Users\\user\\Desktop\\kfc_repo\\productsRepo.csv";
//    private static final String PATH_TO_PRODUCTS = "productsRepo.csv";
    private static final String SPLIT_BY = ";";
    private List<Product> products;
    private long id;

    public ProductCsvRepo() {
        this.products = new LinkedList<>();
        this.id = 0;
    }

    @Override
    public void create(Product product) {
        product.setId(id);
        products.add(product);
        id++;
    }

    @Override
    public List<Product> findAllByGroup(Groups groups) {
        return this.products.stream()
                .filter(v -> v.getGroup() == groups)
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(long id) {
        return this.products.stream()
                .filter(v -> v.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Collection<Product> findAll() {
        return this.products;
    }

    @Override
    public Product findByName(String name) {
        return products.stream()
                .filter(v -> v.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public void removeByName(String name) {
        int index = -1;
        for (Product product : products) {
            if(product.getName().equals(name))
                index = products.indexOf(product);
        }
        products.remove(products.get(index));
    }

    @Override
    public void modifyByName(Product product, String name) {
        int value = -1;
        for (Product tmpProduct : products) {
            if(tmpProduct.getName().equals(name))
                value = products.indexOf(tmpProduct);
        }
        products.remove(products.get(value));
    }

    public void importProducts() {
        List<Product> tmpProducts = new LinkedList<>();
        try {
            System.out.println("Products initialization starts...");
            BufferedReader csvReader = new BufferedReader(new FileReader(PATH_TO_PRODUCTS));
            String row;

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(SPLIT_BY);
                switch (data[1]) {
                    case "SIMPLE_PRODUCT":
                        tmpProducts.add(Loader.loadSimpleProduct(data));
                        break;
                    case "BOX":
                        tmpProducts.add(Loader.loadBox(data));
                        break;
                    case "BUCKET":
                        tmpProducts.add(Loader.loadBucket(data));
                        break;
                    case "SALAD":
                        tmpProducts.add(Loader.loadSalad(data));
                        break;
                    case "SHAKE":
                        tmpProducts.add(Loader.loadShake(data));
                        break;
                }
            }
            csvReader.close();
            for (Product p : tmpProducts) {
                create(p);
            }
            System.out.println("Products initialization has been ended successfully!");
        } catch (IOException e) {
            System.out.println("Error with products repository!");
        }
    }

    public void saveProducts() {
        Recorder.saveProducts(products);
    }

}
