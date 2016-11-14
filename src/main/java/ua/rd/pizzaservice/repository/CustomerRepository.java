package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.customer.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer find(Integer id);

    Customer find(String name);

    List<Customer> findAll();
}
