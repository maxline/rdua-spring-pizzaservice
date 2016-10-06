package ua.rd.pizzaservice04.services;

import ua.rd.pizzaservice04.domain.Customer;
import ua.rd.pizzaservice04.domain.Order;

public interface OrderService {

    Order placeNewOrder(Customer customer, Integer... ids);

}
