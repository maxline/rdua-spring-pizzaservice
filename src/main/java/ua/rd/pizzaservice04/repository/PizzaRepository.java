package ua.rd.pizzaservice04.repository;

import ua.rd.pizzaservice04.domain.Pizza;

public interface PizzaRepository {
    Pizza find(Integer id);
}
