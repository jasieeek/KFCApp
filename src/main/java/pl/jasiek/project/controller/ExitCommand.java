package pl.jasiek.project.controller;

import pl.jasiek.project.repository.csv.OrderCsvRepo;
import pl.jasiek.project.repository.csv.ProductCsvRepo;

public class ExitCommand implements Command {
    private final ProductCsvRepo productCsvRepo;
    private final OrderCsvRepo orderCsvRepo;

    public ExitCommand(ProductCsvRepo productCsvRepo, OrderCsvRepo orderCsvRepo) {
        this.productCsvRepo = productCsvRepo;
        this.orderCsvRepo = orderCsvRepo;
    }

    @Override
    public void execute() {
        System.out.println("Saving...");
        productCsvRepo.saveProducts();
        orderCsvRepo.saveOrders();
        System.out.println("Saving ended!");
        System.exit(0);
    }

    @Override
    public String getLabel() {
        return "Exit";
    }
}
