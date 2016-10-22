package ua.rd.pizzaservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author andrii
 */
@Entity
public class Pizza implements Serializable {

    public enum PizzaType {
        VEGETARIAN, SEA, MEAT;
    }

    //@Id @GeneratedValue(strategy = GenerationType.TABLE)   //создается отельная табличка hybernate_sequences, можно делать несколько сиквенсов, в табличке разными строчками
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "PizzaSEQ", allocationSize = 10)
//    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "PizzaSEQ")
    private Integer id;
    private String name;
    private PizzaType pizzaType;
    private BigDecimal price;


    public Pizza() {
    }

    public Integer getId() {
        return id;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pizza{" + "name=" + name + ", pizzaType=" + pizzaType + '}';
    }
}