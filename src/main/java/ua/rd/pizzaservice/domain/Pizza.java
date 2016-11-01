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


    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    @Override
    public String toString() {
        return "Pizza{" + id + ", name=" + name + ", pizzaType=" + pizzaType + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (id != null ? !id.equals(pizza.id) : pizza.id != null) return false;
        if (!name.equals(pizza.name)) return false;
        if (pizzaType != pizza.pizzaType) return false;
        return price.equals(pizza.price);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + pizzaType.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}