package ua.rd.pizzaservice.repository;

/**
 *
 * @author andrii
 */
import java.util.HashMap;
import java.util.Map;
import ua.rd.pizzaservice.domain.Pizza;


/**
 *
 * @author andrii
 */
public class InMemPizzaRepository implements PizzaRepository {

    private final Map<Integer, Pizza> pizzas = new HashMap<>();

    public void init() {
        pizzas.put(1, new Pizza(1, "sea", Pizza.PizzaType.SEA));
        pizzas.put(2, new Pizza(2, "meat", Pizza.PizzaType.MEAT));
        pizzas.put(3, new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN));
    }
    
    @Override
    public Pizza find(Integer id) {
        return pizzas.get(id);
    }

}
