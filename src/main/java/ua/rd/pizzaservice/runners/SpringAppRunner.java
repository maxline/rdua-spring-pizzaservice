package ua.rd.pizzaservice.runners;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.services.CustomerService;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.PizzaService;

import java.util.Arrays;

import static ua.rd.pizzaservice.domain.order.StatusManager.Status.IN_PROGRESS;

public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext(
                        "repoContext.xml");
        System.out.println("repoContext.xml: " + Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext appContext =
                new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"}, repoContext);
        System.out.println("appContext.xml: " + Arrays.toString(appContext.getBeanDefinitionNames()));

        OrderService orderService = (OrderService) appContext.getBean("orderService");
        //((SimpleOrderService)orderService).setContext(appContext);

        CustomerService customerService = (CustomerService) appContext.getBean("simpleCustomerService");
        System.out.println("customerService.find(1): " + customerService.find(1));

        PizzaService pizzaService = (PizzaService) appContext.getBean("simplePizzaService");
        System.out.println("pizzaService.find(1): " + pizzaService.find(1));

        Order order = orderService.placeNewOrder(customerService.find(1), 1, 1, 1, 2, 3);
        System.out.println("order 11123: " + order);
        System.out.println(orderService.getClass());  // сделает прокси

        order = orderService.placeNewOrder(customerService.find(1), 1);
        System.out.println("order 1: " + order);

        orderService.changeOrderStatus(order, IN_PROGRESS);
        System.out.println(order);
        System.out.println(customerService.find(1));

        repoContext.close();
        appContext.close();
    }

}
