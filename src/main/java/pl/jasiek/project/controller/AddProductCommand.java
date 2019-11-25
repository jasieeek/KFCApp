package pl.jasiek.project.controller;

import pl.jasiek.project.model.Products.*;
import pl.jasiek.project.model.SemifinishedProducts.Extension;
import pl.jasiek.project.repository.csv.ProductCsvRepo;
import pl.jasiek.project.view.View;

import java.util.*;

public class AddProductCommand implements Command {
    private View view;
    private View viewHelper;
    private ProductCsvRepo productCsvRepo;

    public AddProductCommand(View view, ProductCsvRepo productCsvRepo) {
        this.view = view;
        this.productCsvRepo = productCsvRepo;
    }

    @Override
    public void execute() {
        System.out.println("Add new product");
        Groups chosenGroup = view.readGroups("Choose group of new product");

        switch (chosenGroup) {
            case SIMPLE_PRODUCT:
                SimpleProduct simpleProduct = new SimpleProduct();
                simpleProduct.setGroup(Groups.SIMPLE_PRODUCT);
                simpleProduct.setName(view.readString("Insert name of new simpleProduct"));
                do {
                    simpleProduct.setPrice(view.readDouble("Insert price of new simpleProduct"));
                } while (simpleProduct.getPrice() == -1);
                productCsvRepo.create(simpleProduct);
                System.out.println("Additions has been added!");
                break;

            case BOX:
                Box box = new Box();
                box.setGroup(Groups.BOX);
                box.setName(view.readString("Insert name of new box"));
                box.setPrice(view.readDouble("Insert price of new box"));
                box.setSandwich(view.readSandwich("Choose sandwich to new box"));
                productCsvRepo.create(box);
                System.out.println("New box has been added!");
                break;

            case BUCKET:
                Bucket bucket = new Bucket();
                bucket.setGroup(Groups.BUCKET);
                bucket.setName(view.readString("Insert name of new bucket"));
                bucket.setPrice(view.readDouble("Insert price of new bucket"));
                bucket.setContains(view.readContains("Set contains in your bucket", view.readInt("How many ingredients are you adding?") - 1));
                int count = view.readInt("How many additions do you want add?");
                List<Extension> tmpAdditions = new LinkedList<>();
                while (count > 0) {
                    tmpAdditions.add(view.readAdditions("Choose simpleProduct"));
                    count--;
                }
                bucket.setAdditions(tmpAdditions);

                productCsvRepo.create(bucket);
                System.out.println("Bucket has been added!");
                break;
            case SALAD:
                Salad salad = new Salad();
                salad.setGroup(Groups.SALAD);
                salad.setName(view.readString("Insert name of new salad"));
                salad.setPrice(view.readDouble("Insert price of new salad"));
                salad.setChicken(view.readChicken("Set chicken in new salad"));
                salad.setVegetables(view.readVegetable("Set vegetables in new salad"));
                salad.setSauce(view.readSauce("Set sauce in new salad"));
                productCsvRepo.create(salad);
                System.out.println("Salad has been added!");
                break;

            case SHAKE:
                Shake shake = new Shake();
                shake.setGroup(Groups.SHAKE);
                shake.setName(view.readString("Insert name of new shake"));
                shake.setPrice(view.readDouble("Insert price of new shake"));
                shake.setCapacity(view.readInt("Insert capacity of new shake"));
                shake.setTaste(view.readTaste("Set taste of new shake"));
                productCsvRepo.create(shake);
                System.out.println("Shake has been added!");
                break;

            default: {
                System.out.println("Choose number from 0 to 4!");
            }
        }
    }

    @Override
    public String getLabel() {
        return "Add product";
    }
}
