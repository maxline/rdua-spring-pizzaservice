package ua.rd.pizzaservice.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Serhii_Mykhliuk
 */
public class DiscountCardBalanceTest {
    private CustomerCard customerCardMock;
    private Order orderMock;

    @Before
    public void setup() {
        customerCardMock = mock(CustomerCard.class);
        orderMock = mock(Order.class);
    }

    @Test
    public void calculateDiscountNotMore30PercentFromPrice() throws Exception {
        when(customerCardMock.getBalance()).thenReturn(new BigDecimal("5000.00"));

        Customer defaultCustomer = new Customer("Adam", new Address("Earth"), customerCardMock);

        when(orderMock.getPrice()).thenReturn(new BigDecimal("300.00"));
        when(orderMock.getCustomer()).thenReturn(defaultCustomer);

        assertEquals(new BigDecimal("90.00"), new DiscountCardBalance().calculateDiscount(orderMock));
    }

    @Test
    public void calculateDiscount10PercentFromCardBalance() throws Exception {
        when(customerCardMock.getBalance()).thenReturn(new BigDecimal("100.00"));

        Customer defaultCustomer = new Customer("Adam", new Address("Earth"), customerCardMock);

        when(orderMock.getPrice()).thenReturn(new BigDecimal("300.00"));
        when(orderMock.getCustomer()).thenReturn(defaultCustomer);

        assertEquals(new BigDecimal("10.00"), new DiscountCardBalance().calculateDiscount(orderMock));
    }
}