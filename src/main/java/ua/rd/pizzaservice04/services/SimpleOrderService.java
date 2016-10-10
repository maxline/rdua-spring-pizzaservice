package ua.rd.pizzaservice04.services;

import ua.rd.pizzaservice04.domain.Order;
import ua.rd.pizzaservice04.domain.Pizza;
import ua.rd.pizzaservice04.domain.Customer;
import java.util.ArrayList;
import java.util.List;

import ua.rd.pizzaservice04.repository.OrderRepository;

/**
 *
 * @author andrii
 */
public class SimpleOrderService implements OrderService  {

    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }    

    @Override
    public Order placeNewOrder(Customer customer, int... pizzaID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzaID) {
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
