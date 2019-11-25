package pl.jasiek.project.controller;

import pl.jasiek.project.repository.csv.OrderCsvRepo;

public class ShowOrdersCommand implements Command {
    private final OrderCsvRepo orderCsvRepo;

    public ShowOrdersCommand(OrderCsvRepo orderCsvRepo) {
        this.orderCsvRepo = orderCsvRepo;
    }

    @Override
    public void execute() {
        orderCsvRepo.findAll().forEach(System.out::println);
    }

    @Override
    public String getLabel() {
        return "Show orders";
    }
}
