package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.BeforeClass;
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

    private Customer customer;
    private static List<Pizza> defaultPizzas;

    @BeforeClass
    public static void setupOnce() {
        defaultPizzas = new ArrayList<>(1);
        defaultPizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
    }

    @Before
    public void setup() {
        customer = new Customer("Adam", "Earth");
    }

    @Test
    public void getPriceWithoutDiscount() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal("200.00")));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal("100.00")));

        Order order = new Order(customer, pizzas);
        assertEquals(new BigDecimal("400.00"), order.getPriceWithDiscount());
    }

    //todo добавить тест второй скидки
    @Test
    public void getPriceWithDiscountFourPizza() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(5);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal("200.00")));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal("100.00")));

        Order order = new Order(customer, pizzas);

        //assertEquals(new BigDecimalCloseTo(new BigDecimal("540.0"), new BigDecimal("0.00001")), order.getPriceWithDiscount());
        assertEquals(new BigDecimal("540.00"), order.getPriceWithDiscount());
    }

    @Test
    public void createOrderWithStatus() throws Exception {
        Order order = new Order(customer, defaultPizzas);
        assertEquals(NEW, order.getStatus());

        order = new Order(customer, defaultPizzas, IN_PROGRESS);
        assertEquals(IN_PROGRESS, order.getStatus());

        order = new Order(customer, defaultPizzas, DONE);
        assertEquals(DONE, order.getStatus());
    }

    @Test
    public void changeStatus() throws Exception {
        Order order = new Order(customer, defaultPizzas, NEW);
        order.changeStatus(CANCELED);
        assertEquals(CANCELED, order.getStatus());

        order = new Order(customer, defaultPizzas, NEW);
        order.changeStatus(IN_PROGRESS);
        assertEquals(IN_PROGRESS, order.getStatus());

        order.changeStatus(DONE);
        assertEquals(DONE, order.getStatus());

        order = new Order(customer, defaultPizzas, IN_PROGRESS);
        order.changeStatus(CANCELED);
        assertEquals(CANCELED, order.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeWrongStatusFromNew() throws Exception {
        Order order = new Order(customer, defaultPizzas, NEW);
        order.changeStatus(DONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromInProgress() throws Exception {
        Order order = new Order(customer, defaultPizzas, IN_PROGRESS);
        order.changeStatus(NEW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromCanceled() throws Exception {
        Order order = new Order(customer, defaultPizzas, CANCELED);
        order.changeStatus(NEW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongStatusFromDone() throws Exception {
        Order order = new Order(customer, defaultPizzas, DONE);
        order.changeStatus(NEW);
    }

    @Test(expected = NullPointerException.class)
    public void setNullCustomer() throws Exception {
        new Order(null, defaultPizzas);
    }

    @Test(expected = NullPointerException.class)
    public void setEmptyCustomerAddress() throws Exception {
        Customer customerEmptyAddress = new Customer("Adam", "");
        new Order(customerEmptyAddress, defaultPizzas);
    }
}