package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * @author andrii
 */
public class Pizza {

    public enum PizzaType {
        VEGETARIAN, SEA, MEAT;
    }

    private Integer id;
    private String name;
    private PizzaType pizzaType;
    private BigDecimal price;

    public Pizza(Integer id, String name, PizzaType pizzaType, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.pizzaType = pizzaType;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Pizza{" + "name=" + name + ", pizzaType=" + pizzaType + '}';
    }
}