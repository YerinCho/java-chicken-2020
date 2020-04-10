package domain;

import java.util.Map;

public class Payment {

    private static final double CASH_SALE_RATE = 0.95;
    private static final int MIN_SALE_APPLY_BOUGHT_NUMBER = 10;
    private static final int SALE_MONEY_WHEN_BOUGHT_OVER_MIN_NUMBER = 10000;

    public static double calculatePay(Map<Menu, OrderedMenu> orderedMenus, PaymentMethod paymentMethod) {
        double price = 0;
        int boughtNumbers = 0;
        for (OrderedMenu orderedMenu : orderedMenus.values()) {
            boughtNumbers += numbersWhenChicken(orderedMenu);
            price += orderedMenu.calculatePrice();
        }
        price = discountWhenMoreTen(price, boughtNumbers);
        price = discountCash(paymentMethod, price);
        return price;
    }

    private static int numbersWhenChicken(OrderedMenu orderedMenu) {
        if (orderedMenu.isChicken()) {
            return orderedMenu.getNumber();
        }
        return 0;
    }

    private static double discountWhenMoreTen(double price, int boughtNumbers) {
        if (boughtNumbers >= MIN_SALE_APPLY_BOUGHT_NUMBER) {
            return price - SALE_MONEY_WHEN_BOUGHT_OVER_MIN_NUMBER;
        }
        return price;
    }

    private static double discountCash(PaymentMethod paymentMethod, double price) {
        if (PaymentMethod.CASH.equals(paymentMethod)) {
            price = price * CASH_SALE_RATE;
        }
        return price;
    }
}
