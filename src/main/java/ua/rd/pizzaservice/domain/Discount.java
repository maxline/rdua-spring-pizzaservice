package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 *
 */
public interface Discount {
    BigDecimal calculateDiscount(Order order);
}
