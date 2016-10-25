package ua.rd.pizzaservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ua.rd.pizzaservice.domain.Pizza;

import static org.junit.Assert.assertNotNull;

public class JPAPizzaRepositoryTest extends RepositoryTestConfig {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void find() throws Exception {
        //jdbcTemplate.query()   и т.д. чтобы проверять насколько правильно отработали запросы
    }

    @Test
    @Rollback(false)
    public void save() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setName("Sea");
        pizza.setPizzaType(Pizza.PizzaType.SEA);
        pizza = pizzaRepository.save(pizza);

        assertNotNull(pizza.getId());
    }
}