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

public class OrderTest {
    Customer customer;

    @Before
    public void setup() {
        customer = new Customer("Adam", "Earth");
    }

    @Test
    public void getPriceWithoutDiscount() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal(200.00)));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal(100.00)));

        Order order = new Order(customer, pizzas);

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

        Order order = new Order(customer, pizzas);

        //assertEquals(new BigDecimalCloseTo(new BigDecimal("540.0"), new BigDecimal("0.00001")), order.getPrice());
        assertEquals(new BigDecimal("540.0"), order.getPrice());
    }

    @Test
    public void setStatusNew() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        Order order = new Order(customer, pizzas);

        assertEquals(Order.Status.NEW, order.getStatus());
        order.setStatus(Order.Status.IN_PROGRESS);

        assertEquals(Order.Status.IN_PROGRESS, order.getStatus());

        order.setStatus(Order.Status.DONE);
        assertEquals(Order.Status.DONE, order.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromNew() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        Order order = new Order(customer, pizzas);

        assertEquals(Order.Status.NEW, order.getStatus());
        order.setStatus(Order.Status.DONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromInProgress() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        Order order = new Order(customer, pizzas);

        assertEquals(Order.Status.NEW, order.getStatus());
        order.setStatus(Order.Status.IN_PROGRESS);
        assertEquals(Order.Status.IN_PROGRESS, order.getStatus());
        order.setStatus(Order.Status.NEW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromCanceled() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        Order order = new Order(customer, pizzas);

        assertEquals(Order.Status.NEW, order.getStatus());
        order.setStatus(Order.Status.CANCELED);
        assertEquals(Order.Status.CANCELED, order.getStatus());
        order.setStatus(Order.Status.NEW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromDone() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        Order order = new Order(customer, pizzas);

        assertEquals(Order.Status.NEW, order.getStatus());
        order.setStatus(Order.Status.IN_PROGRESS);
        assertEquals(Order.Status.IN_PROGRESS, order.getStatus());
        order.setStatus(Order.Status.DONE);
        assertEquals(Order.Status.DONE, order.getStatus());
        order.setStatus(Order.Status.NEW);
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