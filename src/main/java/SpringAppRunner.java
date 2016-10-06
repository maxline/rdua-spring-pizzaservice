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

//        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml", "appContext.xml");  //запускает создание контейнера и считывает все бины
        //в веб приложении не будем вручную запускать спринг контейнер (выше)

        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");  //запускает создание контейнера и считывает все бины
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[]{"appContext.xml"}, repoContext);  //запускает создание контейнера и считывает все бины
        //appContext не включает бины из repoContext
//appContext.setParent(repoContext);


        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));
        //System.out.println("concext getBean: " + repoContext.getBean("T1", SomeService.class).getString());  //поздний заменяет ранний T1, вывело Test2
        System.out.println("context1 getBean: " + appContext.getBean("T1", SomeService.class).getString());  //покажет Test2


        PizzaRepository pizzaRepository = (PizzaRepository) repoContext.getBean("pizzaRepository"); //имя плюс тип тогда не нужно будет делать каст
        System.out.println(pizzaRepository.find(1));  // null т.к. не вызвался метод init


        OrderRepository orderRepository = (OrderRepository) repoContext.getBean("orderRepository");
        //System.out.println(orderRepository.find());

        OrderService orderService = (OrderService) appContext.getBean("orderService");
        Order order = orderService.placeNewOrder(null, 1, 3);
        System.out.println(order);

        //пересоздание бина после close родителя
        repoContext.close();  //вызывает метод destroy у бина, но не удаляет, можем пользоваться им!

        //у родителя repo есть бины T1 и T2, у потомка app их нет
        //после вызова appContext.getBean() он увидит бин T1 родителя, хотя и закрыли его
        //вызывается init() метод
        System.out.println("context1 getBean: " + appContext.getBean("T1", SomeService.class).getString());
        appContext.close();
    }
}