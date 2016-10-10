package ua.rd.pizzaservice.infrastructure;

/**
 *
 * @author andrii
 */
public interface Context {
    <T> T getBean(String beanName) throws Exception;
}
