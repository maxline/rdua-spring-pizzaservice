package ua.rd.pizzaservice04.repository;

import ua.rd.pizzaservice04.domain.Order;

public interface OrderRepository {

    Order save(Order newOrder);
}
