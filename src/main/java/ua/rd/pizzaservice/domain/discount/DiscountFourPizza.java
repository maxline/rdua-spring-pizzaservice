package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.order.Order;

import java.math.BigDecimal;

public class DiscountFourPizza implements Discount {
    private static final int MIN_PIZZAS_FOR_DISCOUNT = 4;
    private static final BigDecimal DISCOUNT_PERCENT_FOR_MORE_THEN_FOUR = new BigDecimal("0.3");

    private static boolean isDiscountNeeded(Order order) {
        return order.getPizzas().size() > MIN_PIZZAS_FOR_DISCOUNT;
    }

    @Override
    public BigDecimal calculateDiscount(Order order) {
        if (!isDiscountNeeded(order)) {
            return new BigDecimal("0.00");
        }

        BigDecimal maxPrice = new BigDecimal("0.00");
        for (Pizza pizza : order.getPizzas()) {
            maxPrice = maxPrice.max(pizza.getPrice());
        }

        return maxPrice.multiply(DISCOUNT_PERCENT_FOR_MORE_THEN_FOUR).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
