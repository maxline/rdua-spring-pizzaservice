package ua.rd.pizzaservice04.infrastructure;

import java.lang.reflect.InvocationTargetException;

public interface Context {
    //объекты которыми управляет кОнтекст будем называть бинами
    <T> T getBean(String beanName) throws IllegalAccessException, InvocationTargetException, InstantiationException;
}
