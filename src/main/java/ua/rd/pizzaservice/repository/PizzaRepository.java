package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;

import java.util.List;

public interface PizzaRepository {

    Pizza findById(Integer id);

    List<Pizza> findAll();

    Pizza save(Pizza pizza);

}
