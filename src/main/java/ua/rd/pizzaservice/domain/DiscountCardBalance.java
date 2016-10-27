package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 *
 */
public class DiscountCardBalance implements Discount {
    private static final BigDecimal DISCOUNT_FOR_CARD_BALANCE = new BigDecimal("0.1");
    private static final BigDecimal DISCOUNT_PERCENT_FOR_ORDER_PRICE = new BigDecimal("0.3");

    @Override
    public BigDecimal calculateDiscount(Order order) {
        BigDecimal cardBalanceDiscount = order.getCustomer().getCustomerCard().getBalance().multiply(DISCOUNT_FOR_CARD_BALANCE);
        BigDecimal orderPriceDiscount = order.getPrice().multiply(DISCOUNT_PERCENT_FOR_ORDER_PRICE);

        return cardBalanceDiscount.min(orderPriceDiscount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
