package pl.jasiek.project.repository.memory;

import pl.jasiek.project.model.Products.Bucket;
import pl.jasiek.project.model.Products.Groups;
import pl.jasiek.project.model.Products.Product;
import pl.jasiek.project.repository.ProductRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMemoryRepo implements ProductRepository {
    private List<Product> products;
    private long id;

    public ProductMemoryRepo() {
        this.products = new LinkedList<>();
        id = 0;
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
                .findFirst()
                .filter(v -> v.getId() == id)
                .get();
    }

    @Override
    public Collection<Product> findAll() {
        return this.products;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public void removeByName(String name) {
        int value = -1;
        for (Product product : products) {
            if(product.getName().equals(name))
                value = products.indexOf(product);
        }
        products.remove(products.get(value));
    }

    @Override
    public void modifyByName(Product product, String name) {

    }
}
