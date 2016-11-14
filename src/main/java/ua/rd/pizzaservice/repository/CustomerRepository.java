package ua.rd.pizzaservice.repository;

import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.customer.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer findById(Integer id);

    Customer findByName(String name);

    List<Customer> findAll();

    @Transactional
    Customer save(Customer customer);
}
