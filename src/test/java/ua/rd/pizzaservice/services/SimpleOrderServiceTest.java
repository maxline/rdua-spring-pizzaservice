package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.*;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceTest extends Mockito {

    private PizzaService pizzaServiceMock;
    private OrderRepository orderRepositoryMock;
    private SimpleOrderService orderService;
    private Customer customer;
    private Order orderMock;

    @Before
    public void setup() {
        customer = new Customer("Adam", new Address("Earth"), new CustomerCard());
        pizzaServiceMock = mock(PizzaService.class);
        orderRepositoryMock = mock(OrderRepository.class);
        //orderService = new SimpleOrderService(orderRepositoryMock, pizzaServiceMock);
        orderService = spy(new SimpleOrderService(orderRepositoryMock, pizzaServiceMock));
        when(orderRepositoryMock.save(any(Order.class))).thenReturn(orderMock);
    }


    @Test(expected = NullPointerException.class)
    public void placeNewOrderNullCustomer() {
        doReturn(orderMock).when(orderService).createNewOrder();
        orderService.placeNewOrder(null, 1);
        verify(pizzaServiceMock).find(1);
    }


//    @Test(expected = NullPointerException.class)
//    public void placeNewOrderNullCustomer() {
//        orderService.placeNewOrder(null, 1);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderMoreThenTen() {
        orderService.placeNewOrder(customer, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);  //pizza ids
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderLessThenOne() {
        orderService.placeNewOrder(customer);
    }

    @Ignore //todo
    @Test
    public void increaseCustomerCardBalanceOnePizza() {
        // given
        int PIZZA_ID = 1;
        Pizza pizza = mock(Pizza.class);
        when(pizza.getPrice()).thenReturn(new BigDecimal("100.00"));
        when(pizzaServiceMock.find(PIZZA_ID)).thenReturn(pizza);

        doReturn(orderMock).when(orderService).createNewOrder();
        orderService.placeNewOrder(customer, PIZZA_ID);

        assertEquals(new BigDecimal("100.00"), customer.getCustomerCard().getBalance());
    }

    @Ignore //todo
    @Test
    public void increaseCustomerCardBalanceTwoPizza() {
        // given
        int PIZZA_ID1 = 1;
        Pizza pizza1 = mock(Pizza.class);
        when(pizza1.getPrice()).thenReturn(new BigDecimal("100.00"));
        when(pizzaServiceMock.find(PIZZA_ID1)).thenReturn(pizza1);

        int PIZZA_ID2 = 2;
        Pizza pizza2 = mock(Pizza.class);
        when(pizza2.getPrice()).thenReturn(new BigDecimal("200.00"));
        when(pizzaServiceMock.find(PIZZA_ID2)).thenReturn(pizza2);

        orderService.placeNewOrder(customer, PIZZA_ID2);
        orderService.placeNewOrder(customer, PIZZA_ID1, PIZZA_ID2);

        assertEquals(new BigDecimal("480.00"), customer.getCustomerCard().getBalance());
    }
}