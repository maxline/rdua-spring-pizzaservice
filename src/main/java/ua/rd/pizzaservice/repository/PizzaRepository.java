package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaRepository {

    Pizza find(Integer id);
    
}
