package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;

/**
 * @author andrii
 */
public interface PizzaService {

    Pizza save(Pizza pizza);
    Pizza find(Integer id);

}
