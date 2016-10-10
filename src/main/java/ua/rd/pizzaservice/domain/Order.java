package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author andrii
 */
public class Order {

    public static final int MIN_PIZZAS_FOR_DISCOUNT = 4;
    public static final String DISCOUNT_PERCENT = "0.3";
    private Long id;
    private List<Pizza> pizzas;
    private Customer customer;

    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzas) {
        this.pizzas = pizzas;
        this.customer = customer;
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
        for(Pizza pizza:pizzas){
            maxPrice = maxPrice.max(pizza.getPrice());
        }

        return maxPrice.multiply(new BigDecimal(DISCOUNT_PERCENT));
    }

    public BigDecimal getPrice(){
        BigDecimal orderPrice = new BigDecimal("0.0");

        for(Pizza pizza:pizzas){
            orderPrice = orderPrice.add(pizza.getPrice());
        }

        if (isDiscountNeeded()){
            orderPrice = orderPrice.subtract(calculateDiscount());
        }

        return orderPrice;
    }

    @Override
    public String toString() {
        return "Order{pizzas=" + pizzas + ". Price="+ getPrice() + '}';
    }
}

