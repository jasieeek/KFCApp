package pl.jasiek.project.controller;

import pl.jasiek.project.repository.csv.ProductCsvRepo;
import pl.jasiek.project.repository.memory.ProductMemoryRepo;

public class ShowProductsCommand implements Command {
    private final ProductCsvRepo productCsvRepo;

    public ShowProductsCommand(ProductCsvRepo productCsvRepo) {
        this.productCsvRepo = productCsvRepo;
    }

    @Override
    public void execute() {
        productCsvRepo.findAll().forEach(System.out::println);
    }

    @Override
    public String getLabel() {
        return "Show products";
    }
}
