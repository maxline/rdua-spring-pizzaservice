package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author andrii
 */
public class Order {

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

    public BigDecimal getPrice(){
        BigDecimal totalPrice = new BigDecimal("0.0");
        for(Pizza pizza:pizzas){
            totalPrice = totalPrice.add(pizza.getPrice());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{pizzas=" + pizzas + ". Price="+ getPrice() + '}';
    }

}

