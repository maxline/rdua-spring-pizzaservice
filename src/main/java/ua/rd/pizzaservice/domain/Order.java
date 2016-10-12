package ua.rd.pizzaservice.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.StatusManager.Status;

import java.math.BigDecimal;
import java.util.*;

import static ua.rd.pizzaservice.domain.DiscountCalculator.calculateDiscount;
import static ua.rd.pizzaservice.domain.StatusManager.Status.NEW;
import static ua.rd.pizzaservice.domain.StatusManager.isTransitionAllowed;


/**
 * @author andrii
 */
@Component
@Scope("prototype")
public class Order {

    private Long id;
    private List<Pizza> pizzas;
    private Customer customer;
    private Status status;

    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzas, Status status) {
        if (customer == null) {
            throw new NullPointerException("Exception! Customer can not be null!");
        }
        if (customer.getAddress() == null || customer.getAddress().equals("")) {
            throw new NullPointerException("Exception! Customer address can not be empty!");
        }

        this.pizzas = pizzas;
        this.customer = customer;
        this.status = status;
    }


    public Order(Customer customer, List<Pizza> pizzas) {
        this(customer, pizzas, NEW);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getPrice() {
        BigDecimal orderPrice = new BigDecimal("0.0");

        for (Pizza pizza : pizzas) {
            orderPrice = orderPrice.add(pizza.getPrice());
        }

        orderPrice = orderPrice.subtract(calculateDiscount(this));
        return orderPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus(Status statusTo) {
        if (isTransitionAllowed(status, statusTo)){
            status = statusTo;
        } else {
            throw new IllegalArgumentException("It is not allowed moving from " + status + " to " + statusTo + " status!");
        }
    }

    @Override
    public String toString() {
        return "Order{pizzas=" + pizzas + ". Price=" + getPrice() + ". Status=" + getStatus() + '}';
    }
}

