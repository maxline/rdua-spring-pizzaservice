package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Before
    public void setup() {
    }

    @Test
    public void getPriceWithoutDiscountTest() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal(200.00)));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal(100.00)));

        Order order = new Order(null, pizzas);

        assertEquals(new BigDecimal("400.0"), order.getPrice());
    }

    @Test
    public void getPriceWithDiscountTest() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(3);

        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal(200.00)));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal(100.00)));

        Order order = new Order(null, pizzas);

        //assertEquals(new BigDecimalCloseTo(new BigDecimal("540.0"), new BigDecimal("0.00001")), order.getPrice());
        assertEquals(new BigDecimal("540.0"), order.getPrice());
    }
}