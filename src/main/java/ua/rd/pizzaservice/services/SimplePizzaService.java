package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

/**
 * @author andrii
 */
@Service
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;

    @Autowired
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
