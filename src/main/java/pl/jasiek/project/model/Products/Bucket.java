package pl.jasiek.project.model.Products;

import pl.jasiek.project.model.SemifinishedProducts.Chicken;
import pl.jasiek.project.model.SemifinishedProducts.Extension;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bucket extends Product {
    public Map<Chicken, Integer> contains;
    public List<Extension> additions;


    public Map<Chicken, Integer> getContains() {
        return contains;
    }

    public void setContains(Map<Chicken, Integer> contains) {
        this.contains = contains;
    }

    public List<Extension> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Extension> additions) {
        this.additions = additions;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();

        tmp.append("Bucket{")
                .append("id=")
                .append(id)
                .append(", group=")
                .append(group)
                .append(", name='")
                .append(name)
                .append('\'')
                .append(", price=")
                .append(price)
                .append(", contains=")
                .append(contains);
        tmp.append(", extension=")
                .append(additions);
        tmp.append("}");

        return tmp.toString();
    }


    public String toStringCsv() {
        StringBuilder tmpStringBuilder = new StringBuilder();

        tmpStringBuilder.append(id).append(";")
                .append(group).append(";")
                .append(name).append(";")
                .append(price).append(";")
                .append("CHICKENS[");
        Set<Map.Entry<Chicken, Integer>> elementsContain = contains.entrySet();
        for (Map.Entry<Chicken, Integer> element : elementsContain) {
                tmpStringBuilder.append(element.getKey())
                        .append("-")
                        .append(element.getValue())
                        .append(":");
        }

        String tmpString = tmpStringBuilder.toString();
        String substring = tmpString.substring(0, (tmpString.length()-1));
        StringBuilder stringBuilder = new StringBuilder()
                .append(substring)
                .append("];ADDITIONS[");

        for (int i = 0; i < additions.size(); i++) {
            if (additions.get(i) == additions.get(additions.size() - 1)) {
                stringBuilder.append(additions.get(i))
                        .append("]");
            } else
                stringBuilder.append(additions.get(i))
                        .append(":");
        }

        return stringBuilder.toString();
    }
}
