package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.domain.order.StatusManager;

public interface OrderService {

    Order placeNewOrder(Customer customer, int... pizzaID);

    Order doPayment(Order order);

    void changeOrderStatus(Order order, StatusManager.Status status);
}
