package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.repository.CustomerRepository;

@Service
public class SimpleCustomerService implements CustomerService {
    private CustomerRepository customerRepository;

    public SimpleCustomerService() {
    }

    @Autowired
    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id);
    }
}
