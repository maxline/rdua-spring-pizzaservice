package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ua.rd.pizzaservice.domain.Order.Status.*;

public class OrderTest {

    private static Customer DEFAULT_CUSTOMER;
    private static final Order DEFAULT_ORDER;

    static {
        DEFAULT_CUSTOMER = new Customer("Adam", "Earth");
        List<Pizza> pizzas = new ArrayList<>(1);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        DEFAULT_ORDER = new Order(DEFAULT_CUSTOMER, pizzas);
    }

    @Before
    public void setup() {
    }

    @Test
    public void getPriceWithoutDiscount() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal(200.00)));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal(100.00)));

        Order order = new Order(DEFAULT_CUSTOMER, pizzas);
        assertEquals(new BigDecimal("400.0"), order.getPrice());
    }

    @Test
    public void getPriceWithDiscount() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal(200.00)));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal(100.00)));

        Order order = new Order(DEFAULT_CUSTOMER, pizzas);

        //assertEquals(new BigDecimalCloseTo(new BigDecimal("540.0"), new BigDecimal("0.00001")), order.getPrice());
        assertEquals(new BigDecimal("540.0"), order.getPrice());
    }

    @Test
    public void createOrderWithStatus() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(1);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        Order order = new Order(DEFAULT_CUSTOMER, pizzas);
        assertEquals(NEW, order.getStatus());

        order = new Order(DEFAULT_CUSTOMER, pizzas, IN_PROGRESS);
        assertEquals(IN_PROGRESS, order.getStatus());

        order = new Order(DEFAULT_CUSTOMER, pizzas, DONE);
        assertEquals(DONE, order.getStatus());
    }

    @Test
    public void changeStatus() throws Exception {
        Order order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), NEW);
        order.changeStatus(CANCELED);
        assertEquals(Order.Status.CANCELED, order.getStatus());

        order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), NEW);
        order.changeStatus(IN_PROGRESS);
        assertEquals(Order.Status.IN_PROGRESS, order.getStatus());

        order.changeStatus(DONE);
        assertEquals(Order.Status.DONE, order.getStatus());

        order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), IN_PROGRESS);
        order.changeStatus(CANCELED);
        assertEquals(Order.Status.CANCELED, order.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeWrongStatusFromNew() throws Exception {
        Order order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), NEW);
        order.changeStatus(DONE);
    }


    @Test(expected = NullPointerException.class)
    public void setNullCustomer() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        new Order(null, pizzas);
    }

    @Test(expected = NullPointerException.class)
    public void setEmptyCustomerAddress() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        Customer customerEmptyAddress = new Customer("Adam", "");
        new Order(customerEmptyAddress, pizzas);
    }

}