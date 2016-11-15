package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.repository.CustomerRepository;

import static org.mockito.Mockito.verify;

/**
 * @author Sergey Mikhluk.
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleCustomerServiceTest {
    @Spy
    SimpleCustomerService customerServiceSpy;
    @Mock
    CustomerRepository customerRepositoryMock;

    @Before
    public void setup() {
        customerServiceSpy = new SimpleCustomerService(customerRepositoryMock);
    }

    @Test
    public void findCustomerById() throws Exception {
        customerServiceSpy.findById(1);
        verify(customerRepositoryMock).findById(1);
    }
}