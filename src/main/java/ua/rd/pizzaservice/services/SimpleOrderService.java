package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ua.rd.pizzaservice.domain.order.StatusManager.Status;


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
        List<Pizza> pizzas = getPizzasByIds(pizzaID);

        //Order newOrder = new Order(customer, pizzas);
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private boolean checkParameters(int[] pizzaID) {
        return (pizzaID.length < 1) || (pizzaID.length > maxOrderCount);
    }

    private List<Pizza> getPizzasByIds(int... pizzaID) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Integer id : pizzaID) {
            pizzas.add(findPizzaByID(id));  // get Pizza from predefined in-memory list
        }
        return pizzas;
    }

    @Override
    public Order doPayment(Order order) {
        //todo можно ли дергать кастомера напрямую (увеличивать его счет) или надо как-то через сервисы?
        BigDecimal price = order.getPriceWithDiscount();
        order.getCustomer().getCustomerCard().increaseBalance(price);
        return null;
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

    private Pizza findPizzaByID(Integer id) {
        return pizzaService.find(id);
    }

    private void saveOrder(Order newOrder) {
        checkCustomer(newOrder);
        checkAddress(newOrder);
        orderRepository.save(newOrder);
    }

    private void checkCustomer(Order newOrder) {
        if (newOrder.getCustomer() == null) {
            throw new NullPointerException("Exception! Customer can not be null!");
        }
    }

    private void checkAddress(Order newOrder) {
        if (newOrder.getCustomer().getAddress() == null || newOrder.getCustomer().getAddress().equals("")) {
            throw new NullPointerException("Exception! Customer address can not be empty!");
        }
    }
}
