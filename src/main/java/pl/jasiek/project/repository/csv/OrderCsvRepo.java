package pl.jasiek.project.repository.csv;

import pl.jasiek.project.model.Order.Order;
import pl.jasiek.project.model.Products.Product;
import pl.jasiek.project.repository.OrderRepository;
import pl.jasiek.project.repository.csv.loadData.Loader;
import pl.jasiek.project.repository.csv.loadData.Recorder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OrderCsvRepo implements OrderRepository {
    private static final String PATH_TO_ORDERS = "C:\\Users\\user\\Desktop\\kfc_repo\\ordersRepo.csv";
//    private static final String PATH_TO_ORDERS = "../../../../resources/productsRepo.csv";
    private static final String SPLIT_BY = ";";
    private List<Order> orders;
    private final ProductCsvRepo productCsvRepo;
    private long id;

    public OrderCsvRepo(ProductCsvRepo productCsvRepo) {
        this.productCsvRepo = productCsvRepo;
        this.orders = new LinkedList<>();
        this.id = 0;
    }

    @Override
    public void create(Order order) {
        order.setId(id);
        orders.add(order);
        id++;
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public void removeById(long id) {
        int index = -1;
        for (Order o : orders) {
            if (o.getId() == id) {
                index = orders.indexOf(o);
            }
        }
        orders.remove(orders.get(index));
    }

    public void importOrders() {
        Loader loader = new Loader(productCsvRepo);
        try {
            System.out.println("Orders initialization starts...");
            BufferedReader csvReader = new BufferedReader(new FileReader(PATH_TO_ORDERS));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] line = row.split(SPLIT_BY);
                create(loader.loadOrder(line));
            }
            csvReader.close();
            System.out.println("Orders initialization has been ended successfully!");
        } catch (IOException e) {
            System.out.println("Order Repository error!");
        }
    }

    public void saveOrders() {
        Recorder.saveOrders(orders);
    }
}
