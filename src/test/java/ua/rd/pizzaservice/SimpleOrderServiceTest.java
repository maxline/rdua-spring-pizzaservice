package ua.rd.pizzaservice;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.services.OrderService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SimpleOrderServiceTest {

    private static final Customer DEFAULT_CUSTOMER;

    private ConfigurableApplicationContext appContext;

    static {
        DEFAULT_CUSTOMER = new Customer("Adam", "Earth");
    }

    @Before
    public void setup() {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext(
                        "repoContext.xml");

        appContext = new ClassPathXmlApplicationContext(
                new String[]{"appContext.xml"}, repoContext);
    }

    @Test
    public void placeSimpleNewOrder() {
// если через new то не отрабатывает <lookup-method name="createNewOrder" bean="order"/>  в appContext
//        OrderService orderService = new SimpleOrderService(
//                new InMemOrderRepository(),
//                new SimplePizzaService(new InMemPizzaRepository()));

        OrderService orderService = (OrderService) appContext.getBean("orderService");
        orderService.placeNewOrder(DEFAULT_CUSTOMER, 1);
    }

    @Test
    @Ignore
    public void placeNewOrderNullCustomer() {
        OrderService orderService = (OrderService) appContext.getBean("orderService");
        //todo должно быть нельзя создать Order с null заказчиком
        orderService.placeNewOrder(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderMoreThenTen() {
        OrderService orderService = (OrderService) appContext.getBean("orderService");
        orderService.placeNewOrder(DEFAULT_CUSTOMER, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeOrderLessThenOne() {
        OrderService orderService = (OrderService) appContext.getBean("orderService");
        orderService.placeNewOrder(DEFAULT_CUSTOMER);
    }

    @Test
    public void increaseCustomerCardBalance() {
        Customer customer = new Customer(DEFAULT_CUSTOMER.getName(), DEFAULT_CUSTOMER.getAddress());

        OrderService orderService = (OrderService) appContext.getBean("orderService");
        orderService.placeNewOrder(customer, 1);

        assertEquals(new BigDecimal("100.0"), customer.getCardBalance());

        orderService.placeNewOrder(customer, 1, 2);
        assertEquals(new BigDecimal("400.0"), customer.getCardBalance());
    }
}