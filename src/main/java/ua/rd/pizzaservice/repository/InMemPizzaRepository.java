package ua.rd.pizzaservice.repository;

/**
 * @author andrii
 */

import ua.rd.pizzaservice.domain.Pizza;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author andrii
 */
public class InMemPizzaRepository implements PizzaRepository {

    private final Map<Integer, Pizza> pizzas = new HashMap<>();

    public void init() {
        pizzas.put(1, new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.put(2, new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal(200.00)));
        pizzas.put(3, new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal(100.00)));
    }

    @Override
    public Pizza find(Integer id) {
        return pizzas.get(id);
    }

}
