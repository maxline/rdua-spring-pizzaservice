package ua.rd.pizzaservice04.repository;

import ua.rd.pizzaservice04.domain.Order;

/**
 *
 * @author andrii
 */
public interface OrderRepository {

    Order save(Order order);
    
}
