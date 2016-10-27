package ua.rd.pizzaservice.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rd.pizzaservice.repository.OrderRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.PizzaService;
import ua.rd.pizzaservice.services.SimpleOrderService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SimpleOrderServiceTest extends Mockito {

    private PizzaService pizzaService;
    private OrderRepository orderRepository;
    private OrderService orderService;
    private Customer customer;

    @Before
    public void setup() {
        customer = new Customer("Adam", new Address("Earth"), new CustomerCard());
        pizzaService = mock(PizzaService.class);
        orderRepository = mock(OrderRepository.class);
        orderService = new SimpleOrderService(orderRepository, pizzaService);
    }

    @Test(expected = NullPointerException.class)
    public void placeNewOrderNullCustomer() {
        orderService.placeNewOrder(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderMoreThenTen() {
        orderService.placeNewOrder(customer, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);  //pizza ids
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderLessThenOne() {
        orderService.placeNewOrder(customer);
    }

    @Test
    public void increaseCustomerCardBalanceOnePizza() {
        // given
        int PIZZA_ID = 1;
        Pizza pizza = mock(Pizza.class);
        when(pizza.getPrice()).thenReturn(new BigDecimal("100.00"));
        when(pizzaService.find(PIZZA_ID)).thenReturn(pizza);

        orderService.placeNewOrder(customer, PIZZA_ID);

        assertEquals(new BigDecimal("100.00"), customer.getCustomerCard().getBalance());
    }

    @Test
    public void increaseCustomerCardBalanceTwoPizza() {
        // given
        int PIZZA_ID1 = 1;
        Pizza pizza1 = mock(Pizza.class);
        when(pizza1.getPrice()).thenReturn(new BigDecimal("100.00"));
        when(pizzaService.find(PIZZA_ID1)).thenReturn(pizza1);

        int PIZZA_ID2 = 2;
        Pizza pizza2 = mock(Pizza.class);
        when(pizza2.getPrice()).thenReturn(new BigDecimal("200.00"));
        when(pizzaService.find(PIZZA_ID2)).thenReturn(pizza2);

        orderService.placeNewOrder(customer, PIZZA_ID2);
        orderService.placeNewOrder(customer, PIZZA_ID1, PIZZA_ID2);

        assertEquals(new BigDecimal("480.00"), customer.getCustomerCard().getBalance());
    }
}