package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

class DiscountCalculator {

    private static final int MIN_PIZZAS_FOR_DISCOUNT = 4;
    private static final String DISCOUNT_PERCENT = "0.3";

    private static boolean isDiscountNeeded(Order order) {
        return order.getPizzas().size() > MIN_PIZZAS_FOR_DISCOUNT;
    }

    protected static BigDecimal calculateDiscount(Order order) {
        if (!isDiscountNeeded(order)){
            return new BigDecimal("0.0");
        }

        BigDecimal maxPrice = new BigDecimal("0.0");
        for (Pizza pizza : order.getPizzas()) {
            maxPrice = maxPrice.max(pizza.getPrice());
        }

        return maxPrice.multiply(new BigDecimal(DISCOUNT_PERCENT));
    }

}