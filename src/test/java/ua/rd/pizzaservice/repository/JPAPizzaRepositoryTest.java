package ua.rd.pizzaservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.pizzaservice.domain.Pizza;

import static org.junit.Assert.*;

//будет использовать не свой раннер а чужой, для того чтобы поднять спринговый контейнер
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repoContext.xml"})
public class JPAPizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;

    //нужно чтобы во время этого теста поднялся реальный спринговый контейнер
    @Test
    public void save() throws Exception {

        Pizza pizza = new Pizza();

        pizza.setName("Sea");
        pizza.setPizzaType(Pizza.PizzaType.SEA);

        pizza = pizzaRepository.save(pizza);

        assertNotNull(pizza.getId());
    }

}