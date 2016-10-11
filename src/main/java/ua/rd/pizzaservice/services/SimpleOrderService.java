package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrii
 */
public class SimpleOrderService implements OrderService {

    private final OrderRepository orderRepository;  //тут ссылка на интерфейс, завязка не на конкретный экземпляр, а на абстакцию - на это направлен IoC
    private final PizzaService pizzaService;

    private int maxOrderCount;

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, int maxOrderCount) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.maxOrderCount = maxOrderCount;
    }

    @Override
    public Order placeNewOrder(Customer customer, int... pizzaID) {
        if (checkParameters(pizzaID)) {
            throw new IllegalArgumentException();
        }
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzaID) {
            pizzas.add(findPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private boolean checkParameters(int[] pizzaID) {
        return (pizzaID.length < 1) || (pizzaID.length > maxOrderCount);
    }

    private Pizza findPizzaByID(Integer id) {
        return pizzaService.find(id);
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }
}
