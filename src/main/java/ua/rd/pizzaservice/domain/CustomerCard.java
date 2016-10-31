package ua.rd.pizzaservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author Serhii_Mykhliuk
 */
@Entity
public class CustomerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

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
