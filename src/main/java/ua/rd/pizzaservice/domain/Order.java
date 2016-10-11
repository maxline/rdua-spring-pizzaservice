package ua.rd.pizzaservice.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static ua.rd.pizzaservice.domain.Order.Status.*;

/**
 *
 * @author andrii
 */
@Component
@Scope("prototype")
public class Order {

    private static final int MIN_PIZZAS_FOR_DISCOUNT = 4;
    private static final String DISCOUNT_PERCENT = "0.3";
    private Long id;
    private List<Pizza> pizzas;
    private Customer customer;
    private Status status;

    public enum Status {
        NEW, IN_PROGRESS, CANCELED, DONE
    }

    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzas) {
        if (customer == null) {
            throw new NullPointerException("Exception! Customer can not be null!");
        }
        if (customer.getAddress() == null || customer.getAddress().equals("")) {
            throw new NullPointerException("Exception! Customer address can not be empty!");
        }

        this.pizzas = pizzas;
        this.customer = customer;
        this.status = NEW;
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

    private boolean isDiscountNeeded() {
        return pizzas.size() > MIN_PIZZAS_FOR_DISCOUNT;
    }

    private BigDecimal calculateDiscount() {
        BigDecimal maxPrice = new BigDecimal("0.0");
        for (Pizza pizza : pizzas) {
            maxPrice = maxPrice.max(pizza.getPrice());
        }

        return maxPrice.multiply(new BigDecimal(DISCOUNT_PERCENT));
    }

    public BigDecimal getPrice() {
        BigDecimal orderPrice = new BigDecimal("0.0");

        for (Pizza pizza : pizzas) {
            orderPrice = orderPrice.add(pizza.getPrice());
        }

        if (isDiscountNeeded()) {
            orderPrice = orderPrice.subtract(calculateDiscount());
        }

        return orderPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        switch (this.status){
            case NEW:
                if (status != IN_PROGRESS && status !=CANCELED){
                    throw new IllegalArgumentException("From NEW you can move to IN_PROGRESS or to CANCELED!");
                }
                break;
            case IN_PROGRESS:
                if (status != DONE && status !=CANCELED){
                    throw new IllegalArgumentException("From IN_PROGRESS you can move to DONE or to CANCELED!");
                }
                break;
            case CANCELED:
                throw new IllegalArgumentException("Order status CANCELED can not be changed!");
            case DONE:
                throw new IllegalArgumentException("Order status DONE can not be changed!");
        }

        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{pizzas=" + pizzas + ". Price=" + getPrice() + ". Status=" + getStatus() + '}';
    }
}

