package ua.rd.pizzaservice.infrastructure;

/**
 * @author andrii
 */
public interface Config {

    public Class<?> getImpl(String name);

}
