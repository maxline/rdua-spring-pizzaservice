package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.customer.Customer;

/**
 *
 */
public interface CustomerRepository {
    Customer find(Integer id);
}
