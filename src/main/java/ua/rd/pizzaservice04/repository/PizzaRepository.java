package ua.rd.pizzaservice04.repository;

import ua.rd.pizzaservice04.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaRepository {

    Pizza find(Integer id);
    
}
