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
import static ua.rd.pizzaservice.domain.StatusManager.Status.*;

public class OrderTest {

    private static Customer DEFAULT_CUSTOMER;
    private static final Order DEFAULT_ORDER;

    static {
        DEFAULT_CUSTOMER = new Customer("Adam", "Earth");
        List<Pizza> pizzas = new ArrayList<>(1);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));

        DEFAULT_ORDER = new Order(DEFAULT_CUSTOMER, pizzas);
    }

    //todo default_castomer
    @Before
    public void setup() {
    }

    @Test
    public void getPriceWithoutDiscount() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal("200.00")));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal("100.00")));

        Order order = new Order(DEFAULT_CUSTOMER, pizzas);
        assertEquals(new BigDecimal("400.00"), order.getPriceWithDiscount());
    }

    //todo добавить тест второй скидки
    @Test
    public void getPriceWithDiscountFourPizza() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal("200.00")));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal("100.00")));

        Order order = new Order(DEFAULT_CUSTOMER, pizzas);

        //assertEquals(new BigDecimalCloseTo(new BigDecimal("540.0"), new BigDecimal("0.00001")), order.getPriceWithDiscount());
        assertEquals(new BigDecimal("540.00"), order.getPriceWithDiscount());
    }

    @Test
    public void createOrderWithStatus() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(1);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));

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
        assertEquals(CANCELED, order.getStatus());

        order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), NEW);
        order.changeStatus(IN_PROGRESS);
        assertEquals(IN_PROGRESS, order.getStatus());

        order.changeStatus(DONE);
        assertEquals(DONE, order.getStatus());

        order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), IN_PROGRESS);
        order.changeStatus(CANCELED);
        assertEquals(CANCELED, order.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeWrongStatusFromNew() throws Exception {
        Order order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), NEW);
        order.changeStatus(DONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromInProgress() throws Exception {
        Order order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), IN_PROGRESS);
        order.changeStatus(NEW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromCanceled() throws Exception {
        Order order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), CANCELED);
        order.changeStatus(NEW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromDone() throws Exception {
        Order order = new Order(DEFAULT_CUSTOMER, DEFAULT_ORDER.getPizzas(), DONE);
        order.changeStatus(NEW);
    }

    @Test(expected = NullPointerException.class)
    public void setNullCustomer() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        new Order(null, pizzas);
    }

    @Test(expected = NullPointerException.class)
    public void setEmptyCustomerAddress() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));

        Customer customerEmptyAddress = new Customer("Adam", "");
        new Order(customerEmptyAddress, pizzas);
    }
}