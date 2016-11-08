package ua.rd.pizzaservice.domain;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Pizza extends ResourceSupport implements Serializable {

    public enum PizzaType {
        VEGETARIAN, SEA, MEAT;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy = GenerationType.TABLE)   //создается отельная табличка hybernate_sequences, можно делать несколько сиквенсов, в табличке разными строчками
    //@SequenceGenerator(name = "PizzaSEQ", allocationSize = 10)
    //@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "PizzaSEQ")
    private Integer pizzaId;
    private String name;
    private PizzaType pizzaType;
    private BigDecimal price;

    public Pizza() {
    }

    public Pizza(String name, PizzaType pizzaType, BigDecimal price) {
        this.name = name;
        this.pizzaType = pizzaType;
        this.price = price;
    }

    public Pizza(Integer pizzaId, String name, PizzaType pizzaType, BigDecimal price) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.pizzaType = pizzaType;
        this.price = price;
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    @Override
    public String toString() {
        return "Pizza{" + pizzaId + ", name=" + name + ", pizzaType=" + pizzaType + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (pizzaId != null ? !pizzaId.equals(pizza.pizzaId) : pizza.pizzaId != null) return false;
        if (!name.equals(pizza.name)) return false;
        if (pizzaType != pizza.pizzaType) return false;
        return price.equals(pizza.price);
    }

    @Override
    public int hashCode() {
        int result = pizzaId != null ? pizzaId.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + pizzaType.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}