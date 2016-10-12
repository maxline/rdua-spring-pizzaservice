package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.services.CustomerService;
import ua.rd.pizzaservice.services.OrderService;

import java.util.Arrays;

import static ua.rd.pizzaservice.domain.StatusManager.Status.IN_PROGRESS;

/**
 * @author andrii
 */
public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext(
                        "repoContext.xml");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext appContext =
                new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"}, repoContext);
        System.out.println("appContext.xml: " + Arrays.toString(appContext.getBeanDefinitionNames()));

        OrderService orderService = (OrderService) appContext.getBean("orderService");
        //((SimpleOrderService)orderService).setContext(appContext);

        CustomerService customerService = (CustomerService) appContext.getBean("simpleCustomerService");

        Order order = orderService.placeNewOrder(customerService.find(1), 1, 1, 1, 2, 3);
        System.out.println(order);
        System.out.println(orderService.getClass());  // сделает прокси

        orderService.changeOrderStatus(order, IN_PROGRESS);
        System.out.println(order);
        System.out.println(customerService.find(1));

        repoContext.close();
        appContext.close();
    }

}
