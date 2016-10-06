package ua.rd.pizzaservice04.infrastructure;

public interface Config {
    public Class<?> getImpl(String name);
}
