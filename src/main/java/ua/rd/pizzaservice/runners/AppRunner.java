package ua.rd.pizzaservice.runners;

import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.infrastructure.ApplicationContext;
import ua.rd.pizzaservice.infrastructure.Context;
import ua.rd.pizzaservice.infrastructure.JavaConfig;
import ua.rd.pizzaservice.repository.CustomerRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;

public class AppRunner {
    public static void main(String[] args) throws Exception {
        System.out.println("Pizza Service");

//        Order order;
//        
//        SimpleOrderService orderService = new SimpleOrderService();
//        order = orderService.placeNewOrder(customer, 1, 2, 3);
//        
//        System.out.println(order);

        Context context = new ApplicationContext(new JavaConfig());

        PizzaRepository pizzaRepository = context.getBean("pizzaRepository");
        System.out.println(pizzaRepository.findById(1));

        CustomerRepository customerRepository = context.getBean("customerRepository");
        Customer customer = customerRepository.findById(1);
        System.out.println(customer);

        //Ошибка видимо из-за в SimpleOrderSercice в конструктор добавили новый параметр int maxOrderCount
        OrderService orderService = context.getBean("orderService");

        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);
    }

}
