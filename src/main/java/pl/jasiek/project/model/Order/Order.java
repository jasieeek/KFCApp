package pl.jasiek.project.model.Order;

import pl.jasiek.project.model.Products.Product;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private long id;
    private LocalDateTime dateTimeCreate;
    private List<Product> products;
    private double finalPrice;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(LocalDateTime dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateTimeCreate=" + dateTimeCreate +
                ", products=" + products +
                ", finalPrice=" + finalPrice +
                ", name='" + name + '\'' +
                '}';
    }

    public String toStringCsv() {
        StringBuilder tmp = new StringBuilder();
        tmp.append(id).append(";")
                .append(dateTimeCreate).append(";PRODUCTS[");
        for (Product p: products) {
            String tmpName = p.getName();
            if (tmpName.equals(products.get(products.size()-1).getName())) {
                tmp.append(tmpName)
                        .append("]");
            } else {
                tmp.append(tmpName)
                        .append(":");
            }
        }
        tmp.append(";")
                .append(finalPrice).append(";")
                .append(name);

        return tmp.toString();
    }
}
