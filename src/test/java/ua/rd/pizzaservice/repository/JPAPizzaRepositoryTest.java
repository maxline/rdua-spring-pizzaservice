package ua.rd.pizzaservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;

import static org.junit.Assert.assertNotNull;

public class JPAPizzaRepositoryTest extends RepositoryTestConfig {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void save() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setName("Sea");
        pizza.setPizzaType(Pizza.PizzaType.SEA);
        pizza = pizzaRepository.save(pizza);

        assertNotNull(pizza.getId());
    }
}