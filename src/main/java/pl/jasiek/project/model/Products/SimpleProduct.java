package pl.jasiek.project.model.Products;


public class SimpleProduct extends Product {

    public SimpleProduct(long id, Groups group, String name, double price) {
        super(id, group, name, price);
    }

    public SimpleProduct() {
    }

    @Override
    public String toString() {
        return "Simple products{" +
                "id=" + id +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String toStringCsv() {
        return id + ";"
                + group + ";"
                + name + ";"
                + price;
    }
}
