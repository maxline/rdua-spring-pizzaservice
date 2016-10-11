package ua.rd.pizzaservice.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
//import ua.rd.pizzaservice.infrastructure.ApplicationContext;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrii
 */
public class SimpleOrderService implements OrderService, ApplicationContextAware {

    private final OrderRepository orderRepository;  //тут ссылка на интерфейс, завязка не на конкретный экземпляр, а на абстакцию - на это направлен IoC
    private final PizzaService pizzaService;
    private ApplicationContext context;

    private int maxOrderCount;

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, int maxOrderCount) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.maxOrderCount = maxOrderCount;
    }

    @Override
    public Order placeNewOrder(Customer customer, int... pizzaID) {
        if (checkParameters(pizzaID)) {
            throw new IllegalArgumentException();
        }
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzaID) {
            pizzas.add(findPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private Order createNewOrder() {
        try {
            return (Order) context.getBean("order");
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

//    // контекст не может инжектить через аутовайред
//    public void setContext(ApplicationContext context) {
//        this.context = context;
//    }

    private boolean checkParameters(int[] pizzaID) {
        return (pizzaID.length < 1) || (pizzaID.length > maxOrderCount);
    }

    private Pizza findPizzaByID(Integer id) {
        return pizzaService.find(id);
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }

    //т.к в конструкте третий парамерт примитивного типа autowired нельзя использовать
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }
}
