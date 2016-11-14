package ua.rd.pizzaservice.repository.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JPAPizzaRepositoryIT extends RepositoryTestConfig {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void findPizzaById() throws Exception {
        Pizza pizza = new Pizza("Sea", Pizza.PizzaType.SEA, BigDecimal.ZERO);
        //jdbcTemplate.query()   чтобы проверять насколько правильно отработали запросы
        jdbcTemplate.update("INSERT INTO pizza (pizzaId, name, price, pizzaType) VALUES (1,?,?,?)",
                pizza.getName(), pizza.getPrice(), pizza.getPizzaType().ordinal());
        //todo корректно ли тут использовать ordinal()? в таблице pizza pizzaType имеет тип int

        pizza = pizzaRepository.findById(1);
        Integer id = pizza.getPizzaId();
        assertEquals(new Integer(1), id);
    }

    @Test
    @Rollback(false)
    public void savePizza() throws Exception {
        Pizza pizza = new Pizza("Sea", Pizza.PizzaType.SEA, BigDecimal.ZERO);
        pizza = pizzaRepository.save(pizza);

        assertNotNull(pizza.getPizzaId());
    }


}