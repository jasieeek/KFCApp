package pl.jasiek.project.repository;

import pl.jasiek.project.model.Order.Order;

import java.util.List;

public interface OrderRepository {
    void create(Order order);
    List<Order> findAll();
    void removeById(long id);
}
