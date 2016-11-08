package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.customer.Address;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.customer.CustomerCard;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceTest extends Mockito {

    @Mock
    PizzaService pizzaServiceMock;
    @Mock
    OrderRepository orderRepositoryMock;

    private SimpleOrderService orderServiceSpy;

    private Customer defaultCustomer;

    private Order defaultOrder;
    private static final int PIZZA_ID_1 = 1;
    private static final int PIZZA_ID_2 = 2;

    @Before
    public void setup() {
        defaultCustomer = new Customer("Adam", new Address("Earth"), new CustomerCard());
        defaultOrder = new Order();

        orderServiceSpy = spy(new SimpleOrderService(orderRepositoryMock, pizzaServiceMock));
    }

    @Test(expected = NullPointerException.class)
    public void placeNewOrderNullCustomer() {
        doReturn(defaultOrder).when(orderServiceSpy).createNewOrder();
        orderServiceSpy.placeNewOrder(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderMoreThenTen() {
        orderServiceSpy.placeNewOrder(defaultCustomer, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);  //defaultPizzaId1 ids
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderLessThenOne() {
        orderServiceSpy.placeNewOrder(defaultCustomer);
    }

    @Test(expected = NullPointerException.class)
    public void setNullCustomer() throws Exception {
        doReturn(defaultOrder).when(orderServiceSpy).createNewOrder();
        orderServiceSpy.placeNewOrder(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void setEmptyCustomerAddress() throws Exception {
        Customer customerEmptyAddress = new Customer("Adam", null, null);
        doReturn(defaultOrder).when(orderServiceSpy).createNewOrder();
        orderServiceSpy.placeNewOrder(customerEmptyAddress, 1);
    }

    @Test
    public void increaseCustomerCardBalanceOnePizza() {
        //given
        Pizza defaultPizzaId1 = new Pizza(PIZZA_ID_1, "Sea", Pizza.PizzaType.SEA, new BigDecimal("100.00"));
        when(pizzaServiceMock.find(PIZZA_ID_1)).thenReturn(defaultPizzaId1);

        doReturn(defaultOrder).when(orderServiceSpy).createNewOrder();

        Order newOrder = orderServiceSpy.placeNewOrder(defaultCustomer, PIZZA_ID_1);
        orderServiceSpy.doPayment(newOrder);
        assertEquals(new BigDecimal("100.00"), defaultCustomer.getCustomerCard().getBalance());
    }

    @Test
    public void increaseCustomerCardBalanceTwoPizza() {
        // given
        Pizza defaultPizzaId1 = new Pizza(PIZZA_ID_1, "Sea", Pizza.PizzaType.SEA, new BigDecimal("100.00"));
        when(pizzaServiceMock.find(PIZZA_ID_1)).thenReturn(defaultPizzaId1);

        Pizza defaultPizzaId2 = new Pizza(PIZZA_ID_1, "Sea", Pizza.PizzaType.SEA, new BigDecimal("200.00"));
        when(pizzaServiceMock.find(PIZZA_ID_2)).thenReturn(defaultPizzaId2);

        doReturn(defaultOrder).when(orderServiceSpy).createNewOrder();
        Order newOrder = orderServiceSpy.placeNewOrder(defaultCustomer, PIZZA_ID_2);
        orderServiceSpy.doPayment(newOrder);

        newOrder = orderServiceSpy.placeNewOrder(defaultCustomer, PIZZA_ID_1, PIZZA_ID_2);
        orderServiceSpy.doPayment(newOrder);

        assertEquals(new BigDecimal("480.00"), defaultCustomer.getCustomerCard().getBalance());
    }
}