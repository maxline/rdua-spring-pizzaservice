package ua.rd.pizzaservice.repository.jpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.customer.Address;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.customer.CustomerCard;
import ua.rd.pizzaservice.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Sergey Mikhluk.
 */
public class JPACustomerRepositoryIT extends RepositoryTestConfig {

    private static final int DEFAULT_ADDRESS_ID = 1122;
    private static final int DEFAULT_CUSTOMER_CARD_ID = 1122;
    private static final int DEFAULT_CUSTOMER_ID = 1122;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setup() {
        Address address = new Address("Earth");
        //todo это интеграционный тест? подымаем реальную базу mysql, если @Rollback(false) то останутся записи
        jdbcTemplate.update("INSERT INTO address (id, address) VALUES (" + DEFAULT_ADDRESS_ID + ", ?)",
                address.getAddress());

        CustomerCard customerCard = new CustomerCard(BigDecimal.ZERO);
        jdbcTemplate.update("INSERT INTO customercard (id, balance) VALUES (" + DEFAULT_CUSTOMER_CARD_ID + ", ?)",
                customerCard.getBalance());
    }

//    @After
//    public void tearDown() {
//        jdbcTemplate.update("DELETE FROM customer");
//    }

    @Test
    //@Rollback(false)  // jdbcTemplate записывает в реальную базу, но делает откат, в противном случае будет ошибка с дублирующими ключами
    public void findCustomerById() throws Exception {
        Customer customer = new Customer("Adam1", new Address("Earth"), new CustomerCard());
        jdbcTemplate.update("INSERT INTO customer (id, address_id, customerCard_id, name) VALUES (?,?,?,?)",
                DEFAULT_CUSTOMER_ID, DEFAULT_ADDRESS_ID, DEFAULT_CUSTOMER_CARD_ID, customer.getName());

        customer = customerRepository.findById(DEFAULT_CUSTOMER_ID);
        Integer id = customer.getId();
        assertEquals(new Integer(DEFAULT_CUSTOMER_ID), id);
    }

    @Test
    public void findAllCustomers() throws Exception {
        List<Customer> list = customerRepository.findAll();
        int beforeSize = list.size();

        Customer customer = new Customer("Adam1", new Address("Earth"), new CustomerCard());
        jdbcTemplate.update("INSERT INTO customer (id, address_id, customerCard_id, name) VALUES (?,?,?,?)",
                DEFAULT_CUSTOMER_ID, DEFAULT_ADDRESS_ID, DEFAULT_CUSTOMER_CARD_ID, customer.getName());

        customer = new Customer("Adam2", new Address("Earth"), new CustomerCard());
        jdbcTemplate.update("INSERT INTO customer (id, address_id, customerCard_id, name) VALUES (?,?,?,?)",
                DEFAULT_CUSTOMER_ID + 1, DEFAULT_ADDRESS_ID, DEFAULT_CUSTOMER_CARD_ID, customer.getName());

        list = customerRepository.findAll();
        int afterSize = list.size();

        assertTrue(afterSize - beforeSize >= 2);
    }


    @Test
    public void findCustomerByName() throws Exception {
        String customerName = "Adam1";

        Customer customer = new Customer(customerName, new Address("Earth"), new CustomerCard());
        jdbcTemplate.update("INSERT INTO customer (id, address_id, customerCard_id, name) VALUES (?,?,?,?)",
                DEFAULT_CUSTOMER_ID, DEFAULT_ADDRESS_ID, DEFAULT_CUSTOMER_CARD_ID, customer.getName());

        customer = customerRepository.findByName(customerName);
        String name = customer.getName();
        assertEquals(customerName, name);
    }

    @Test
    public void saveCustomer() throws Exception {
        String customerName = "Adam1";

        Customer customer = new Customer(customerName, new Address("Earth"), new CustomerCard());
        customer = customerRepository.save(customer);
        assertNotNull(customer.getId());
    }
}