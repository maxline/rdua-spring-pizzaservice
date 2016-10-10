package ua.rd.pizzaservice04.infrastructure;

/**
 *
 * @author andrii
 */
public interface Context {
    <T> T getBean(String beanName) throws Exception;
}
