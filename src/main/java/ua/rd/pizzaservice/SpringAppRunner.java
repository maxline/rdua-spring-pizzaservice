package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.SimpleOrderService;

import java.util.Arrays;

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
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));


        OrderService orderService = (OrderService) appContext.getBean("orderService");
        ((SimpleOrderService)orderService).setContext(appContext);

        Order order = orderService.placeNewOrder(null, 1, 1, 1, 2, 3);
        System.out.println(order);

        repoContext.close();
        appContext.close();
    }

}
