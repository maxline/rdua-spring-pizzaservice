package ua.rd.pizzaservice.infrastructure;

import ua.rd.pizzaservice.repository.InMemCustomerRepository;
import ua.rd.pizzaservice.repository.InMemOrderRepository;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.services.SimpleOrderService;
import ua.rd.pizzaservice.services.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author andrii
 */
public class JavaConfig implements Config {

    private Map<String, Class<?>> classes = new HashMap<>();

    {
        classes.put("pizzaRepository", InMemPizzaRepository.class);
        classes.put("orderRepository", InMemOrderRepository.class);
        classes.put("customerRepository", InMemCustomerRepository.class);
        classes.put("orderService", SimpleOrderService.class);
        classes.put("pizzaService", SimplePizzaService.class);
    }

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }

}
