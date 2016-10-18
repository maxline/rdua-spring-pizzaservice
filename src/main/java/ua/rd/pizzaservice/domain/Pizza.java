package ua.rd.pizzaservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author andrii
 */
@Entity
public class Pizza implements Serializable{

    public enum PizzaType {
        VEGETARIAN, SEA, MEAT;
    }

    @Id
    private Integer id;
    private String name;
    private PizzaType pizzaType;
    private BigDecimal price;


    public Pizza() {
    }

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