package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 *
 */
public class DiscountCardBalance implements Discount {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        return null;
    }
}
