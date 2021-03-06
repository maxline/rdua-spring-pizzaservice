package ua.rd.pizzaservice.repository.memory;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.PizzaRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository("pizzaRepository")
public class InMemPizzaRepository implements PizzaRepository {

    private final Map<Integer, Pizza> pizzas = new HashMap<>();

    @PostConstruct    //чтобы инит заработал через аннотакции
    public void init() {
        pizzas.put(1, new Pizza(1, "sea", Pizza.PizzaType.SEA, new BigDecimal(100.00)));
        pizzas.put(2, new Pizza(2, "meat", Pizza.PizzaType.MEAT, new BigDecimal(200.00)));
        pizzas.put(3, new Pizza(3, "vega", Pizza.PizzaType.VEGETARIAN, new BigDecimal(100.00)));
    }

    @Benchmark
    @Override
    public Pizza findById(Integer id) {
        return pizzas.get(id);
    }

    @Override
    public List<Pizza> findAll() {
        return null;
    }

    @Override
    public Pizza save(Pizza pizza) {
        return null;
    }

    @Override
    public void delete(Integer pizzaId) {
    }
}
