package ua.rd.pizzaservice.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.StatusManager.Status;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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

    List<Discount> discountList = Arrays.asList(new DiscountFourPizza(), new DiscountCardBalance());

    public Order() {
        id = 1L;  //todo
    }

    public Order(Customer customer, List<Pizza> pizzas, Status status) {
        this.pizzas = pizzas;
        this.customer = customer;
        this.status = status;
    }

    public Order(Customer customer, List<Pizza> pizzas) {
        this(customer, pizzas, NEW);
    }

    @PostConstruct    //чтобы инит заработал через аннотакции
    public void init() {
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

    public BigDecimal getPrice() {
        BigDecimal orderPrice = new BigDecimal("0.0");
        for (Pizza pizza : pizzas) {
            orderPrice = orderPrice.add(pizza.getPrice());
        }
        return orderPrice;
    }

    public BigDecimal getPriceWithDiscount() {
        return getPrice().subtract(calculateTotalDiscount());
    }

    private BigDecimal calculateTotalDiscount() {
        BigDecimal discountTotal = new BigDecimal("0.00");

        for (Discount discount : discountList) {
            discountTotal = discountTotal.add(discount.calculateDiscount(this));
        }
        return discountTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus(Status statusTo) {
        if (isTransitionAllowed(status, statusTo)) {
            status = statusTo;
        } else {
            throw new IllegalArgumentException("It is not allowed moving from " + status + " to " + statusTo + " status!");
        }
    }

    @Override
    public String toString() {
        return "Order{pizzas=" + pizzas + ". Price=" + getPriceWithDiscount() + ". Status=" + getStatus() + '}';
    }
}

