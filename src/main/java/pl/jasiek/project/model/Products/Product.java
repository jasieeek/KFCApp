package pl.jasiek.project.model.Products;

public abstract class Product {
    public long id;
    public Groups group;
    public String name;
    public double price;

    public Product(long id, Groups group, String name, double price) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static double roundPrice(double price) {
        double roundPrice = price * 100;
        roundPrice = Math.round(roundPrice);
        return roundPrice / 100;
    }
}
