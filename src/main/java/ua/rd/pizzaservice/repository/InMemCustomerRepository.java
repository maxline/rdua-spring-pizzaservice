package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Customer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Repository
public class InMemCustomerRepository implements CustomerRepository {

    private final Map<Integer, Customer> customers = new HashMap<>();

    @PostConstruct    //чтобы инит заработал через аннотакции
    public void init() {
        customers.put(1, new Customer("Adam", new Address("Earth")));
    }

    @Override
    public Customer find(Integer id) {
        return customers.get(id);
    }
}
