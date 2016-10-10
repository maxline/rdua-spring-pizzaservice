package ua.rd.pizzaservice04.domain;

/**
 *
 * @author andrii
 */
public class Pizza {

    public enum PizzaType {

        VEGETARIAN, SEA, MEAT;

    }

    private Integer id;
    private String name;
    private PizzaType pizzaType;

    public Pizza(Integer id, String name, PizzaType pizzaType) {
        this.id = id;
        this.name = name;
        this.pizzaType = pizzaType;
    }

    @Override
    public String toString() {
        return "Pizza{" + "name=" + name + ", pizzaType=" + pizzaType + '}';
    }
}