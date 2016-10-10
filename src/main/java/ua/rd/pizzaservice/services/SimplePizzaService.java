package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

/**
 * @author andrii
 */
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;


    public SimplePizzaService(PizzaRepository pizzaRepository) {
        // InitialContext context = new InitialContext();
        // this.pizzaRepository = (PizzaRepository) context.getInstance("pizzaRepository");
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }

}
