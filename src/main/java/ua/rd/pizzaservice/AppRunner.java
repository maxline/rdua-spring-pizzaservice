package ua.rd.pizzaservice;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.ApplicationContext;
import ua.rd.pizzaservice.infrastructure.Context;
import ua.rd.pizzaservice.infrastructure.JavaConfig;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;

/**
 * @author andrii
 */
public class AppRunner {
    public static void main(String[] args) throws Exception {
        System.out.println("Pizza Srervice");

        Customer customer = null;
//        Order order;
//        
//        SimpleOrderService orderService = new SimpleOrderService();
//        order = orderService.placeNewOrder(customer, 1, 2, 3);
//        
//        System.out.println(order);

        Context context = new ApplicationContext(new JavaConfig());

        PizzaRepository pizzaRepository = context.getBean("pizzaRepository");
        System.out.println(pizzaRepository.find(1));

        OrderService orderService = context.getBean("orderService");
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);
    }

}
