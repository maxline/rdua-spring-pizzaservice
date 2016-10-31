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
    private SimpleOrderService orderServiceSpy;
    private Customer customer;
    private Order orderMock;
    private Order order;
    private Pizza pizza;

    @Before
    public void setup() {
        customer = new Customer("Adam", new Address("Earth"), new CustomerCard());
        order = new Order();

        orderMock = mock(Order.class);
        pizzaServiceMock = mock(PizzaService.class);
        orderRepositoryMock = mock(OrderRepository.class);
        //orderServiceSpy = new SimpleOrderService(orderRepositoryMock, pizzaServiceMock);
        orderServiceSpy = spy(new SimpleOrderService(orderRepositoryMock, pizzaServiceMock));

        //when(orderRepositoryMock.save(any(Order.class))).thenReturn(orderMock);
    }


    @Test(expected = NullPointerException.class)
    public void placeNewOrderNullCustomer() {
        doReturn(orderMock).when(orderServiceSpy).createNewOrder();
        orderServiceSpy.placeNewOrder(null, 1);
        verify(pizzaServiceMock).find(1);
    }


//    @Test(expected = NullPointerException.class)
//    public void placeNewOrderNullCustomer() {
//        orderServiceSpy.placeNewOrder(null, 1);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderMoreThenTen() {
        orderServiceSpy.placeNewOrder(customer, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);  //pizza ids
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderLessThenOne() {
        orderServiceSpy.placeNewOrder(customer);
    }


    @Test
    public void increaseCustomerCardBalanceOnePizza() {
        // given
        pizza = new Pizza(1, "Sea", Pizza.PizzaType.SEA, new BigDecimal("100.00"));
        //Pizza pizzaMock = mock(Pizza.class);
//        when(pizzaMock.getPrice()).thenReturn(new BigDecimal("100.00"));
//        when(pizzaServiceMock.find(PIZZA_ID)).thenReturn(pizzaMock);
        //orderMock = new Order();
        doReturn(order).when(orderServiceSpy).createNewOrder();
        when(pizzaServiceMock.find(1)).thenReturn(pizza);

        //when(orderServiceSpy.createNewOrder()).thenReturn(order);
        //doReturn(customer).when(orderMock).getCustomer();

        Order newOrder = orderServiceSpy.placeNewOrder(customer, 1);
        orderServiceSpy.doPayment(newOrder);
        assertEquals(new BigDecimal("100.00"), customer.getCustomerCard().getBalance());
    }


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

        doReturn(order).when(orderServiceSpy).createNewOrder();
        Order newOrder = orderServiceSpy.placeNewOrder(customer, PIZZA_ID2);
        orderServiceSpy.doPayment(newOrder);

        newOrder = orderServiceSpy.placeNewOrder(customer, PIZZA_ID1, PIZZA_ID2);
        orderServiceSpy.doPayment(newOrder);

        assertEquals(new BigDecimal("480.00"), customer.getCustomerCard().getBalance());
    }


    @Test(expected = NullPointerException.class)
    public void setNullCustomer() throws Exception {
        doReturn(order).when(orderServiceSpy).createNewOrder();
        orderServiceSpy.placeNewOrder(null, 1);
    }


    @Test(expected = NullPointerException.class)
    public void setEmptyCustomerAddress() throws Exception {
        Customer customerEmptyAddress = new Customer("Adam", null, null);
        doReturn(order).when(orderServiceSpy).createNewOrder();
        orderServiceSpy.placeNewOrder(customerEmptyAddress, 1);

        //new Order(customerEmptyAddress, defaultPizzas);
    }


}