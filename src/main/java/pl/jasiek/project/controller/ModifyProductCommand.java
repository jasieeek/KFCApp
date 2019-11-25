package pl.jasiek.project.controller;

import pl.jasiek.project.model.Products.Product;
import pl.jasiek.project.model.Products.SimpleProduct;
import pl.jasiek.project.repository.csv.ProductCsvRepo;
import pl.jasiek.project.view.View;

public class ModifyProductCommand implements Command {
    private final View view;
    private final ProductCsvRepo productCsvRepo;

    public ModifyProductCommand(View view, ProductCsvRepo productCsvRepo) {
        this.view = view;
        this.productCsvRepo = productCsvRepo;
    }

    @Override
    public void execute() {
        System.out.println("Modifying product");
        String oldProductName;
        Product tmpProduct;
        do {
            oldProductName = view.readString("Insert product's name to modify");
            if (oldProductName.equals("0")) {
                System.out.println("Return to menu!");
                break;
            }
            tmpProduct = productCsvRepo.findByName(oldProductName);
        } while (tmpProduct == null);



        if (!oldProductName.equals("0")) {

        }
    }

    @Override
    public String getLabel() {
        return "Modify product";
    }
}
