package ua.rd.pizzaservice04.infrastructure;

/**
 *
 * @author andrii
 */
public interface Config {

    public Class<?> getImpl(String name);
    
}
