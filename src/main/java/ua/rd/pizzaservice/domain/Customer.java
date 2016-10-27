package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * @author andrii
 */
public class Customer {
    private String name;
    private Address address;
    private BigDecimal cardBalance;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
        this.cardBalance = new BigDecimal("0.0");
    }

    public void increaseCardBalance(BigDecimal sum) {
        cardBalance = cardBalance.add(sum);
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" + name + '\'' +
                ", address='" + address + '\'' +
                ", card balance ='" + cardBalance + '\'' +
                '}';
    }
}
