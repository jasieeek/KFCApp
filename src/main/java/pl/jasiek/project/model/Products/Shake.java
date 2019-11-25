package pl.jasiek.project.model.Products;

import pl.jasiek.project.model.SemifinishedProducts.TasteOfShake;

public class Shake extends Product{
    private int capacity;
    private TasteOfShake taste;


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TasteOfShake getTaste() {
        return taste;
    }

    public void setTaste(TasteOfShake taste) {
        this.taste = taste;
    }

    @Override
    public String toString() {
        return "Shake{" +
                "id=" + id +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", taste=" + taste +
                '}';
    }

    public String toStringCsv() {
        return id + ";"
                + group + ";"
                + name + ";"
                + price + ";"
                + capacity + ";"
                + taste;
    }
}
