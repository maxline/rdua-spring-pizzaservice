package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.customer.Customer;

public interface CustomerService {
    Customer findById(Integer id);
}
