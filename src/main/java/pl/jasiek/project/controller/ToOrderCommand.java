package pl.jasiek.project.controller;

import pl.jasiek.project.config.MenuToBuy;
import pl.jasiek.project.config.MenuWithDetails;
import pl.jasiek.project.model.Order.Order;
import pl.jasiek.project.model.Products.Product;
import pl.jasiek.project.repository.csv.OrderCsvRepo;
import pl.jasiek.project.repository.csv.ProductCsvRepo;
import pl.jasiek.project.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ToOrderCommand implements Command {
    private final ProductCsvRepo productCsvRepo;
    private final OrderCsvRepo orderCsvRepo;
    private MenuToBuy kfcMenuBuy = new MenuToBuy();
    private MenuWithDetails kfcMenuDetails = new MenuWithDetails();
    private View view;
    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public ToOrderCommand(View view, ProductCsvRepo productCsvRepo, OrderCsvRepo orderCsvRepo) {
        this.view = view;
        this.productCsvRepo = productCsvRepo;
        this.orderCsvRepo = orderCsvRepo;
    }

    @Override
    public void execute() {
        System.out.println("Order");
        Order order = new Order();
        LocalDateTime timestampCreation = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String timeStampCreationString = timestampCreation.format(formatter);
        order.setDateTimeCreate(LocalDateTime.parse(timeStampCreationString, formatter));
        List<Product> productsToOrder = new LinkedList<>();
        boolean status = true;
        while (status) {
            kfcMenuBuy.displayMenuKfc(productCsvRepo);
            System.out.println("[0] Add product to order" +
                    "\n[1] Show menu with details" +
                    "\n[2] Go to summary");
            int choice = view.readInt("Your choice");
            switch (choice) {
                case 0:
                    Product tmpProduct = productCsvRepo.findById(view.readInt("Choose number of product which do you want buy"));
                    if (tmpProduct != null) {
                        productsToOrder.add(tmpProduct);
                        System.out.println("You have added a product to the bag!");
                    } else {
                        System.out.println("Wrong product's id! Try again with correct number :)");
                    }
                    System.out.println(productsToOrder.toString());
                    break;
                case 1:
                    kfcMenuDetails.displayMenuKfc(productCsvRepo);
                    break;
                case 2:
                    System.out.println("Summary:");
                    status = false;
                    break;
            }
        }

        productsToOrder.stream()
                .map(v -> v.getGroup() + " " + v.getName() + " " + v.getPrice())
                .forEach(System.out::println);
        order.setProducts(productsToOrder);
        double finalPrice = Product.roundPrice(productsToOrder.stream()
                .mapToDouble(Product::getPrice)
                .sum());
        System.out.println("Final price: " + finalPrice);
        order.setFinalPrice(finalPrice);
        order.setName(view.readString("Give your name"));
        int condition = 0;
        do {
            String confirm = view.readString("Do you want confirm the order? (yes/no)");
            if (confirm.equals("yes")) {
                condition = 1;
            } else if (confirm.equals("no")) {
                condition = 2;
            }
        }
        while (condition == 0);

        if (condition == 1) {
            orderCsvRepo.create(order);
            System.out.println("You make an order!\n" + order.toStringCsv());
        } else
            System.out.println("Please come again! See you :)");

    }

    @Override
    public String getLabel() {
        return "Order";
    }
}
