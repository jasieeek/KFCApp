package pl.jasiek.project.view;

import pl.jasiek.project.model.Products.Groups;
import pl.jasiek.project.model.Products.SimpleProduct;
import pl.jasiek.project.model.SemifinishedProducts.*;

import java.util.List;
import java.util.Map;

public interface View {
    String readString(String label);
    int readInt(String label);
    double readDouble(String label);
    Map<Chicken, Integer> readContains(String label, int maxAmountIngredients);
    boolean readBoolean(String label);
    Groups readGroups(String label);
    Sandwiches readSandwich(String label);
    Chicken readChicken(String label);
    List<Vegetable> readVegetable(String label);
    Sauces readSauce(String label);
    TasteOfShake readTaste(String label);
    Extension readAdditions(String label);
    void info(String message);
}
