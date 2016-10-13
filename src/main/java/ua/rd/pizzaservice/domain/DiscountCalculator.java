package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * 1. скидка в 30% на самую дорогую пиццу в заказе, если количество пицц больше 4-х
 *
 * 2. из стоимости заказа вычитается 10% от общей суммы на накопительной карте,
 * но не больше чем 30% стоимости заказа
 */

class DiscountCalculator {

    private static final int MIN_PIZZAS_FOR_DISCOUNT = 4;
    private static final BigDecimal DISCOUNT_PERCENT_FOR_MORE_THEN_FOUR = new BigDecimal("0.3");
    private static final BigDecimal DISCOUNT_FOR_CARD_BALANCE = new BigDecimal("0.1");
    private static final BigDecimal DISCOUNT_PERCENT_FOR_ORDER_PRICE = new BigDecimal("0.3");

    private static boolean isDiscountNeeded(Order order) {
        return order.getPizzas().size() > MIN_PIZZAS_FOR_DISCOUNT;
    }

    private static BigDecimal calculateDiscountMoreThenFour(Order order) {
        if (!isDiscountNeeded(order)){
            return new BigDecimal("0.0");
        }

        BigDecimal maxPrice = new BigDecimal("0.0");
        for (Pizza pizza : order.getPizzas()) {
            maxPrice = maxPrice.max(pizza.getPrice());
        }

        return maxPrice.multiply(DISCOUNT_PERCENT_FOR_MORE_THEN_FOUR);
    }

    private static BigDecimal calculateDiscountTenPercentFromCardBalance(Order order) {
        BigDecimal cardBalanceDiscount = order.getCustomer().getCardBalance().multiply(DISCOUNT_FOR_CARD_BALANCE);
        BigDecimal orderPriceDiscount = order.getPrice().multiply(DISCOUNT_PERCENT_FOR_ORDER_PRICE);

        return cardBalanceDiscount.min(orderPriceDiscount);
    }

    protected static BigDecimal calculateDiscount(Order order) {
        BigDecimal totalDiscount = new BigDecimal("0.0");
        totalDiscount = totalDiscount.add(calculateDiscountMoreThenFour(order));
        totalDiscount = totalDiscount.add(calculateDiscountTenPercentFromCardBalance(order));

        return totalDiscount;
    }
}