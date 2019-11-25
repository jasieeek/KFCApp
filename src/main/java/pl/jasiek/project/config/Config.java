package pl.jasiek.project.config;

import pl.jasiek.project.controller.*;
import pl.jasiek.project.repository.csv.OrderCsvRepo;
import pl.jasiek.project.repository.csv.ProductCsvRepo;
import pl.jasiek.project.repository.memory.ProductMemoryRepo;
import pl.jasiek.project.view.View;
import pl.jasiek.project.view.console.ConsoleView;
import pl.jasiek.project.view.console.Menu;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private View view = new ConsoleView();
    //    private ProductMemoryRepo productRepository = new ProductMemoryRepo();
    private ProductCsvRepo productCsvRepo = new ProductCsvRepo();
    private OrderCsvRepo orderCsvRepo;

    public List<Command> initializeCommands() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new ShowProductsCommand(productCsvRepo));
        commandList.add(new AddProductCommand(view, productCsvRepo));
        commandList.add(new RemoveProductCommand(view, productCsvRepo));
        commandList.add(new ShowOrdersCommand(orderCsvRepo));
        commandList.add(new ToOrderCommand(view, productCsvRepo, orderCsvRepo));
        commandList.add(new RemoveOrderCommand(view, orderCsvRepo));

        commandList.add(new ExitCommand(productCsvRepo, orderCsvRepo));

        return commandList;
    }

    public Menu initializeMenu() {
        return new Menu(view);
    }

    public void initializeProducts() {
        productCsvRepo.importProducts();
    }

    public void initializeOrdersCsv() {
        orderCsvRepo = new OrderCsvRepo(this.productCsvRepo);
        orderCsvRepo.importOrders();
    }
}
