import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice04.domain.Order;
import ua.rd.pizzaservice04.repository.OrderRepository;
import ua.rd.pizzaservice04.repository.PizzaRepository;
import ua.rd.pizzaservice04.services.OrderService;
import ua.rd.pizzaservice04.services.SomeService;

import java.util.Arrays;

public class SpringAppRunner {
    public static void main(String[] args) {
        //передаем несколько конфигураций xml


        //в контексте может быть только один бин, overriding bean T1

//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml", "appContext1.xml");  //запускает создание контейнера и считывает все бины
        //в веб приложении не будем вручную запускать спринг контейнер (выше)

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");  //запускает создание контейнера и считывает все бины
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        ConfigurableApplicationContext context1 = new ClassPathXmlApplicationContext(
                new String[]{"appContext1.xml"});  //запускает создание контейнера и считывает все бины
        //context1 не включает бины из context
//context1.setParent(context);


        System.out.println(Arrays.toString(context1.getBeanDefinitionNames()));
        System.out.println("concext getBean: " + context.getBean("T1", SomeService.class).getString());  //поздний заменяет ранний T1, вывело Test2
        System.out.println("concext1 getBean: " + context1.getBean("T1", SomeService.class).getString());  //покажет Test2


        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository"); //имя плюс тип тогда не нужно будет делать каст
        System.out.println(pizzaRepository.find(1));  // null т.к. не вызвался метод init


        OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
        //System.out.println(orderRepository.find());

        OrderService orderService = (OrderService) context.getBean("orderService");
        Order order = orderService.placeNewOrder(null, 1, 3);
        System.out.println(order);


        context.close();

    }
}