package pl.jasiek.project.repository;

import pl.jasiek.project.model.Products.Groups;
import pl.jasiek.project.model.Products.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface ProductRepository {
    void create(Product product);
    List<Product> findAllByGroup(Groups groups);
    Product findById(long id);
    Collection<Product> findAll();
    Product findByName(String name);
    void removeByName(String name);
    void modifyByName(Product product, String name);
}
