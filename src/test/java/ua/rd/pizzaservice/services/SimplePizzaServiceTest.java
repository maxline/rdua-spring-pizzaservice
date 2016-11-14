package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

/**
 * @author Sergey Mikhluk.
 */
@RunWith(MockitoJUnitRunner.class)
public class SimplePizzaServiceTest {
    SimplePizzaService pizzaService;
    @Mock
    PizzaRepository pizzaRepositoryMock;

    @Before
    public void setup() {
        pizzaService = spy(new SimplePizzaService(pizzaRepositoryMock));
    }

    @Test
    public void find() throws Exception {
        pizzaService.findById(1);
        verify(pizzaRepositoryMock).findById(1);
        //todo корректно ли тут просто проверять что вызывается метод pizzaRepozitory, а сам тест уже же писать в классе JPAPizzaRepositoryTest?
        //или тут тоже надо было проверить положить что-то в репозиторий и проверить что найдет findById()?
    }

    @Test
    public void save() throws Exception {
        Pizza pizza = new Pizza("Sea", Pizza.PizzaType.SEA, BigDecimal.ZERO);

        pizzaService.save(pizza);
        verify(pizzaRepositoryMock).save(pizza);
    }

}