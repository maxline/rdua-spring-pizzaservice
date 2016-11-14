package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Sergey Mikhluk.
 */
@Repository("customerRepository")
public class JPACustomerRepository implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer findById(Integer id) {
        return em.find(Customer.class, id);
    }

    @Override
    public Customer findByName(String name) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName", Customer.class);
        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        Customer newCustomer = em.merge(customer);
        return newCustomer;
    }
}
