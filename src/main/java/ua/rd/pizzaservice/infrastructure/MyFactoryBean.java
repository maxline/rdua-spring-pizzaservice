package ua.rd.pizzaservice.infrastructure;

import org.springframework.beans.factory.FactoryBean;
import ua.rd.pizzaservice.domain.Pizza;

import java.math.BigDecimal;

/**
 *
 */
public class MyFactoryBean implements FactoryBean<Pizza> {

    @Override
    public Pizza getObject() throws Exception {
        return new Pizza(1, "pizza1", Pizza.PizzaType.SEA, new BigDecimal("10.0"));
    }

    @Override
    public Class<?> getObjectType() {
        return Pizza.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
