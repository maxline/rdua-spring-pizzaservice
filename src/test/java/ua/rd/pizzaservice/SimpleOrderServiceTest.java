package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.repository.InMemOrderRepository;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.SimpleOrderService;
import ua.rd.pizzaservice.services.SimplePizzaService;

public class SimpleOrderServiceTest {

    private Customer customer;
    private SimpleOrderService simpleOrderService;

    @Before
    public void setup() {
        customer = new Customer("Adam", "Earth");

    }


    @Test(expected = IllegalArgumentException.class)
    public void placeOrderMoreThenTen() {
        OrderService orderService = new SimpleOrderService(
                new InMemOrderRepository(),
                new SimplePizzaService(new InMemPizzaRepository()));

        orderService.placeNewOrder(null, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderLessThenOne() {
        OrderService orderService = new SimpleOrderService(
                new InMemOrderRepository(),
                new SimplePizzaService(new InMemPizzaRepository()));

        orderService.placeNewOrder(null);
    }

}