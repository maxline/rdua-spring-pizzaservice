package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;

public interface PizzaService {

    Pizza save(Pizza pizza);

    Pizza find(Integer id);
}
