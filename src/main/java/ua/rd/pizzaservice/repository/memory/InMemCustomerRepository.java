package ua.rd.pizzaservice.repository.memory;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.customer.Address;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.customer.CustomerCard;
import ua.rd.pizzaservice.repository.CustomerRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class InMemCustomerRepository implements CustomerRepository {

    private final Map<Integer, Customer> customers = new HashMap<>();

    @PostConstruct    //чтобы инит заработал через аннотакции
    public void init() {
        customers.put(1, new Customer("Adam", new Address("Earth"), new CustomerCard()));
    }

    @Override
    public Customer findById(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer findByName(String name) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }
}
