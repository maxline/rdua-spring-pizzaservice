package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.StatusManager;

/**
 * @author andrii
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, int... pizzaID);

    void changeOrderStatus(Order order, StatusManager.Status status);
}
