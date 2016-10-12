package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.InMemOrderRepository;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.SimpleOrderService;
import ua.rd.pizzaservice.services.SimplePizzaService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ua.rd.pizzaservice.domain.Order.Status.NEW;

public class SimpleOrderServiceTest {

    private Customer customer;
    private SimpleOrderService simpleOrderService;

    @Before
    public void setup() {
        customer = new Customer("Adam", "Earth");

    }

//    @Test(expected = IllegalArgumentException.class)
//    public void changeWrongStatusFromNew() throws Exception {
//        List<Pizza> pizzas = new ArrayList<>(3);
//        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
//
//        Order order = new Order(customer, pizzas, NEW);
//
//
//        assertEquals(NEW, order.getStatus());
//        order.setStatus(Order.Status.DONE);
//    }


//    @Test(expected = IllegalArgumentException.class)
//    public void setWrongStatusFromNew() throws Exception {
//        List<Pizza> pizzas = new ArrayList<>(3);
//        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
//
//        Order order = new Order(customer, pizzas);
//
//        assertEquals(Order.Status.NEW, order.getStatus());
//        order.setStatus(Order.Status.DONE);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void setWrongStatusFromInProgress() throws Exception {
//        List<Pizza> pizzas = new ArrayList<>(3);
//        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
//
//        Order order = new Order(customer, pizzas);
//
//        assertEquals(Order.Status.NEW, order.getStatus());
//        order.setStatus(Order.Status.IN_PROGRESS);
//        assertEquals(Order.Status.IN_PROGRESS, order.getStatus());
//        order.setStatus(Order.Status.NEW);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void setWrongStatusFromCanceled() throws Exception {
//        List<Pizza> pizzas = new ArrayList<>(3);
//        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
//
//        Order order = new Order(customer, pizzas);
//
//        assertEquals(Order.Status.NEW, order.getStatus());
//        order.setStatus(Order.Status.CANCELED);
//        assertEquals(Order.Status.CANCELED, order.getStatus());
//        order.setStatus(Order.Status.NEW);
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void setWrongStatusFromDone() throws Exception {
//        List<Pizza> pizzas = new ArrayList<>(3);
//        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
//
//        Order order = new Order(customer, pizzas);
//
//        assertEquals(Order.Status.NEW, order.getStatus());
//        order.setStatus(Order.Status.IN_PROGRESS);
//        assertEquals(Order.Status.IN_PROGRESS, order.getStatus());
//        order.setStatus(Order.Status.DONE);
//        assertEquals(Order.Status.DONE, order.getStatus());
//        order.setStatus(Order.Status.NEW);
//    }







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