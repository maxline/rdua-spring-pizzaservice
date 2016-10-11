package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrii
 */
@Repository
public class InMemOrderRepository implements OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

}
