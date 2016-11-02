package ua.rd.pizzaservice.repository.memory;

import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrii
 */
//@Repository("orderRepository")
public class InMemOrderRepository implements OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order find(Long id) {
        return null;
    }

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

}
