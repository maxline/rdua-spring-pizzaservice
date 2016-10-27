package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * @author Serhii_Mykhliuk
 */
public class CustomerCard {
    private BigDecimal balance = new BigDecimal("0.00");

    public CustomerCard() {
    }

    public void increaseBalance(BigDecimal sum) {
        balance = balance.add(sum);
    }

    //todo unitTest
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if (balance.compareTo(new BigDecimal("0.0")) ==-1 ){
            throw new IllegalArgumentException("Card balance must be positive number!");
        }
        this.balance = balance;
    }
}
