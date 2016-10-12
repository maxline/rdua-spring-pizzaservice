package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;

public interface CustomerService {
    Customer find(Integer id);
}
