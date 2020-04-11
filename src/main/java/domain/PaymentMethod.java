package domain;

import java.util.Arrays;

public enum PaymentMethod {

    CASH("현금", 2),
    CARD("카드", 1);

    private final String name;
    private final int methodNumber;

    PaymentMethod(final String name, int methodNumber) {
        this.name = name;
        this.methodNumber = methodNumber;
    }

    public static PaymentMethod getPaymentMethodByNumber(int methodNumber) {
        return Arrays.stream(PaymentMethod.values())
                .filter(paymentMethod -> paymentMethod.methodNumber == methodNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


}
