package ua.rd.pizzaservice04.services;

import ua.rd.pizzaservice04.domain.Customer;
import ua.rd.pizzaservice04.domain.Order;

/**
 *
 * @author andrii
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, int... pizzaID);
    
}
