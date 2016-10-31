package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static ua.rd.pizzaservice.domain.StatusManager.Status;


/**
 * @author andrii
 */
@Service("orderService")
public class SimpleOrderService implements OrderService {

    public static final int MAX_ORDER_COUNT_DEFAULT = 10;
    private final OrderRepository orderRepository;  //тут ссылка на интерфейс, завязка не на конкретный экземпляр, а на абстакцию - на это направлен IoC
    private final PizzaService pizzaService;


    private int maxOrderCount;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.maxOrderCount = MAX_ORDER_COUNT_DEFAULT;
    }

    @Benchmark
    @Override
    public Order placeNewOrder(Customer customer, int... pizzaID) {
        if (checkParameters(pizzaID)) {
            throw new IllegalArgumentException();
        }
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzaID) {
            pizzas.add(findPizzaByID(id));  // get Pizza from predefined in-memory list
        }

        //Order newOrder = new Order(customer, pizzas);
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list

        //todo можно ли дергать кастомера напрямую (увеличивать его счет) или надо как-то через сервисы?
        customer.getCustomerCard().increaseBalance(newOrder.getPriceWithDiscount());
        return newOrder;
    }


    @Lookup
    protected Order createNewOrder() {
        throw new IllegalStateException("Container can not get bean Order!");
//        try {
//            return (Order) context.getBean("order");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }


    @Override
    public void changeOrderStatus(Order order, Status status) {
        order.changeStatus(status);
    }

    private boolean checkParameters(int[] pizzaID) {
        return (pizzaID.length < 1) || (pizzaID.length > maxOrderCount);
    }

    private Pizza findPizzaByID(Integer id) {
        return pizzaService.find(id);
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }

}
