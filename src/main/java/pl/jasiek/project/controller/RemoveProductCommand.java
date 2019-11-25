package pl.jasiek.project.controller;

import pl.jasiek.project.model.Products.Product;
import pl.jasiek.project.repository.csv.ProductCsvRepo;
import pl.jasiek.project.view.View;

public class RemoveProductCommand implements Command {
    private final View view;
    private final ProductCsvRepo productCsvRepo;

    public RemoveProductCommand(View view, ProductCsvRepo productCsvRepo) {
        this.view = view;
        this.productCsvRepo = productCsvRepo;
    }

    @Override
    public void execute() {
        System.out.println("Removing product");

        String productName;
        Product product;
        do {
            productName = view.readString("Insert product's name to remove");
            if (productName.equals("0")) {
                System.out.println("Return to menu!");
                break;
            }
            product = productCsvRepo.findByName(productName);
        } while (product == null);

        if (!productName.equals("0")) {
            productCsvRepo.removeByName(productName);
            System.out.println(productName + " has been removed!");
        }
    }

    @Override
    public String getLabel() {
        return "Remove product";
    }
}
