package ua.rd.pizzaservice.runners;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.util.Arrays;

public class SpringJPAAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext(
                        "repoContext.xml");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext appContext =
                new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"}, repoContext);
        System.out.println("appContext.xml: " + Arrays.toString(appContext.getBeanDefinitionNames()));


        //по типу не сможем получить
        PizzaRepository pizzaRepository = (PizzaRepository) appContext.getBean("pizzaRepository"); //см аннотацию Repository

        Pizza pizza = new Pizza();
        pizza.setName("Sea");
        pizza.setPizzaType(Pizza.PizzaType.SEA);

        pizza = pizzaRepository.save(pizza);

        System.out.println(pizza);


//        OrderService orderService = (OrderService) appContext.getBean("orderService");
//        //((SimpleOrderService)orderService).setContext(appContext);
//
//        CustomerService customerService = (CustomerService) appContext.getBean("simpleCustomerService");
//
//        Order order = orderService.placeNewOrder(customerService.findById(1), 1, 1, 1, 2, 3);
//        System.out.println(order);
//        System.out.println(orderService.getClass());  // сделает прокси
//
//        orderService.changeOrderStatus(order, IN_PROGRESS);
//        System.out.println(order);
//        System.out.println(customerService.findById(1));

        repoContext.close();
        appContext.close();
    }

}
