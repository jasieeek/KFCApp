package pl.jasiek.project.controller;

import pl.jasiek.project.repository.csv.OrderCsvRepo;
import pl.jasiek.project.view.View;

public class RemoveOrderCommand implements Command {
    private final View view;
    private final OrderCsvRepo orderCsvRepo;

    public RemoveOrderCommand(View view, OrderCsvRepo orderCsvRepo) {
        this.view = view;
        this.orderCsvRepo = orderCsvRepo;
    }

    @Override
    public void execute() {
        System.out.println("Rolling back transaction");

        orderCsvRepo.findAll().forEach(System.out::println);

        int chosenId = view.readInt("Choose order's id");

        orderCsvRepo.removeById(chosenId);

        System.out.println("You have deleted order with ID = " + chosenId + "!");
    }

    @Override
    public String getLabel() {
        return "Roll back transaction";
    }
}
