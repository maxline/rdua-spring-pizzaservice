package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Pizza;
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
        customer = new Customer("Adam", "Earth");
        pizzaService = mock(PizzaService.class);
        orderRepository = mock(OrderRepository.class);
        orderService = new SimpleOrderService(orderRepository, pizzaService);
    }


    @Test(expected = NullPointerException.class)
    public void placeNewOrderNullCustomer() {
        orderService.placeNewOrder(null, 1);
    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void placeOrderMoreThenTen() {
//        OrderService orderService = (OrderService) appContext.getBean("orderService");
//        orderService.placeNewOrder(DEFAULT_CUSTOMER, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void placeOrderLessThenOne() {
//        OrderService orderService = (OrderService) appContext.getBean("orderService");
//        orderService.placeNewOrder(DEFAULT_CUSTOMER);
//    }

    //todo разнести тесты на два (1 пицца, 2 пиццы)
    @Test
    public void increaseCustomerCardBalance() {
        // given
        int PIZZA_ID = 1;
        Pizza pizza = mock(Pizza.class);
        when(pizza.getPrice()).thenReturn(new BigDecimal("100.00"));
        when(pizzaService.find(PIZZA_ID)).thenReturn(pizza);


        //todo 1 подставит пиццу из рабочего репозитория, а нам по идее надо сделать для тестирования свой тестовый репозиторий
        orderService.placeNewOrder(customer, PIZZA_ID);

        assertEquals(new BigDecimal("100.00"), customer.getCardBalance());
//
//        orderService.placeNewOrder(customer, 1, 2);
//        assertEquals(new BigDecimal("400.00"), customer.getCardBalance());
    }
}