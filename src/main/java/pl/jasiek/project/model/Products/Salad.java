package pl.jasiek.project.model.Products;

import pl.jasiek.project.model.SemifinishedProducts.Chicken;
import pl.jasiek.project.model.SemifinishedProducts.Sauces;
import pl.jasiek.project.model.SemifinishedProducts.Vegetable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Salad extends Product {
    private Chicken chicken;
    private List<Vegetable> vegetables;
    private Sauces sauce;

    public Chicken getChicken() {
        return chicken;
    }

    public void setChicken(Chicken chicken) {
        this.chicken = chicken;
    }

    public List<Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public Sauces getSauce() {
        return sauce;
    }

    public void setSauce(Sauces sauce) {
        this.sauce = sauce;
    }

    @Override
    public String toString() {
        return "Salad{" +
                "id=" + id +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", chicken=" + chicken +
                ", vegetables=" + vegetables +
                ", sauce=" + sauce +
                '}';
    }

    public String toStringCsv() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(id).append(";")
                .append(group).append(";")
                .append(name).append(";")
                .append(price).append(";")
                .append(chicken).append(";");


        stringBuilder.append("VEGETABLES[");

        for (int i = 0; i < vegetables.size(); i++) {
            if(vegetables.get(i) == vegetables.get(vegetables.size()-1)) {
                stringBuilder.append(vegetables.get(i))
                            .append("];");
            } else
                stringBuilder.append(vegetables.get(i))
                        .append(":");
        }

        stringBuilder.append(sauce);

        return stringBuilder.toString();
    }
}
