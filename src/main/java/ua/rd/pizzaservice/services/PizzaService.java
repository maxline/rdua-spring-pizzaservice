package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;

import java.util.List;

public interface PizzaService {

    Pizza findById(Integer id);

    List<Pizza> findAll();

    Pizza save(Pizza pizza);

    void delete(Integer pizzaId);
}
