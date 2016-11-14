package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.order.Order;

public interface OrderRepository {

    Order findById(Long id);

    Order save(Order order);

}
