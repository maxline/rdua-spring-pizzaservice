package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.order.Order;

/**
 * @author andrii
 */
public interface OrderRepository {

    Order find(Long id);

    Order save(Order order);

}
