package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * 1. скидка в 30% на самую дорогую пиццу в заказе, если количество пицц больше 4-х
 * <p>
 * 2. из стоимости заказа вычитается 10% от общей суммы на накопительной карте,
 * но не больше чем 30% стоимости заказа
 */

public interface Discount {
    BigDecimal calculateDiscount(Order order);
}
