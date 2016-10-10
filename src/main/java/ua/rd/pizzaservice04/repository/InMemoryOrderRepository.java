package ua.rd.pizzaservice04.repository;

import java.util.ArrayList;
import java.util.List;
import ua.rd.pizzaservice04.domain.Order;

/**
 *
 * @author andrii
 */
public class InMemoryOrderRepository implements OrderRepository  {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

}
