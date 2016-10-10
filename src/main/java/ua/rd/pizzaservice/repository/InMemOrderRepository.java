package ua.rd.pizzaservice.repository;

import java.util.ArrayList;
import java.util.List;
import ua.rd.pizzaservice.domain.Order;

/**
 *
 * @author andrii
 */
public class InMemOrderRepository implements OrderRepository  {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

}
