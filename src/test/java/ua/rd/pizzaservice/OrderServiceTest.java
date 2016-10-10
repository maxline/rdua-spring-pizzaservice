package ua.rd.pizzaservice;

import org.junit.Test;
import ua.rd.pizzaservice.repository.InMemOrderRepository;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.SimpleOrderService;
import ua.rd.pizzaservice.services.SimplePizzaService;

public class OrderServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderMoreThenTen() {
        OrderService orderService = new SimpleOrderService(
                new InMemOrderRepository(),
                new SimplePizzaService(new InMemPizzaRepository()),
                10);

        orderService.placeNewOrder(null, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderLessThenOne() {
        OrderService orderService = new SimpleOrderService(
                new InMemOrderRepository(),
                new SimplePizzaService(new InMemPizzaRepository()),
                10);

        orderService.placeNewOrder(null);
    }

}