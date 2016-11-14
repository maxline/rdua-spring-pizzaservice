package ua.rd.pizzaservice.domain.customer;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CustomerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    private BigDecimal balance = BigDecimal.ZERO;

    public CustomerCard() {
    }

    public CustomerCard(BigDecimal balance) {
        this.balance = balance;
    }

    public void increaseBalance(BigDecimal sum) {
        balance = balance.add(sum);
    }

    public int getId() {
        return id;
    }

    //todo unitTest
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("Card balance must be positive number!");
        }
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerCard that = (CustomerCard) o;

        if (id != that.id) return false;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + balance.hashCode();
        return result;
    }
}
