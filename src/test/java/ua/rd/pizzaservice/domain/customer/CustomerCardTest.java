package ua.rd.pizzaservice.domain.customer;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Serhii_Mykhliuk
 */
public class CustomerCardTest {
    @Test
    public void increaseCardBalance() throws Exception {
        CustomerCard customerCard = new CustomerCard();
        customerCard.setBalance(new BigDecimal("10.00"));
        customerCard.increaseBalance(new BigDecimal("20.00"));

        assertEquals(new BigDecimal("30.00"), customerCard.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeBalance() throws Exception {
        CustomerCard customerCard = new CustomerCard();
        customerCard.setBalance(new BigDecimal("-10.00"));
    }

}