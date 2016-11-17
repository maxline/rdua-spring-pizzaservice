package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.customer.Customer;

import java.util.List;

public interface CustomerService {
    Customer findById(Integer id);
    List<Customer> findAll();
}
