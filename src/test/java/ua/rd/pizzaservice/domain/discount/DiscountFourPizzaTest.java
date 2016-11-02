package ua.rd.pizzaservice.domain.discount;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.customer.Address;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.customer.CustomerCard;
import ua.rd.pizzaservice.domain.discount.DiscountFourPizza;
import ua.rd.pizzaservice.domain.order.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Serhii_Mykhliuk
 */
public class DiscountFourPizzaTest {
    private Customer defaultCustomer;

    @Before
    public void setup() {
        defaultCustomer = new Customer("Adam", new Address("Earth"), new CustomerCard());
    }

    @Test
    public void calculateDiscountFivePizza() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(5);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal("200.00")));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal("100.00")));

        Order order = new Order(defaultCustomer, pizzas);

        assertEquals(new BigDecimal("60.00"), new DiscountFourPizza().calculateDiscount(order));
    }

    @Test
    public void calculateThreePizza() throws Exception {
        List<Pizza> pizzas = new ArrayList<>(5);
        pizzas.add(new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal("100.00")));
        pizzas.add(new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal("200.00")));
        pizzas.add(new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal("100.00")));

        Order order = new Order(defaultCustomer, pizzas);

        assertEquals(new BigDecimal("0.00"), new DiscountFourPizza().calculateDiscount(order));
    }


}