package ua.rd.pizzaservice04.services;

import ua.rd.pizzaservice04.domain.Customer;
import ua.rd.pizzaservice04.domain.Order;
import ua.rd.pizzaservice04.domain.Pizza;
import ua.rd.pizzaservice04.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * пицца, кастомер, ордер
 */
public class SimpleOrderService implements OrderService {
    private final PizzaService pizzaService;
    private final OrderRepository orderRepository;

    public SimpleOrderService(PizzaService pizzaService, OrderRepository orderRepository) {
        this.pizzaService = pizzaService;
        this.orderRepository = orderRepository;
    }

    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(findPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private Pizza findPizzaByID(Integer id) {

        return pizzaService.find(id);
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }
}