package ua.rd.pizzaservice04;

import java.util.Arrays;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice04.domain.Order;
import ua.rd.pizzaservice04.repository.PizzaRepository;
import ua.rd.pizzaservice04.services.OrderService;
import ua.rd.pizzaservice04.services.SomeService;

/**
 *
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
      
        System.out.println(repoContext.getBean("T1", SomeService.class).getString());
        System.out.println(appContext.getBean("T1", SomeService.class).getString());
        
        PizzaRepository pizzaRepository = (PizzaRepository) repoContext.getBean("pizzaRepository");
        System.out.println(pizzaRepository.find(1));
        
        OrderService orderService = (OrderService) appContext.getBean("orderService");
        Order order = orderService.placeNewOrder(null, 1, 2, 3);
        System.out.println(order);
        
        repoContext.close();        
        appContext.close();
    }
    
}
