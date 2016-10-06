package ua.rd.pizzaservice04.services;

import ua.rd.pizzaservice04.domain.Pizza;
import ua.rd.pizzaservice04.infrastructure.BenchMark;
import ua.rd.pizzaservice04.repository.PizzaRepository;

public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;

    public SimplePizzaService(PizzaRepository pizzaRepository) {
        // InitialContext context = new InitialContext();
        // this.pizzaRepository = (PizzaRepository) context.getInstance("pizzaRepository");
        this.pizzaRepository = pizzaRepository;
    }

    @BenchMark(false)
    @Override
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }
}
