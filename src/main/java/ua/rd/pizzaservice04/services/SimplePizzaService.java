package ua.rd.pizzaservice04.services;

import ua.rd.pizzaservice04.domain.Pizza;
import ua.rd.pizzaservice04.repository.PizzaRepository;

/**
 *
 * @author andrii
 */
public class SimplePizzaService implements PizzaService  {

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
