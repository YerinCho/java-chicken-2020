package domain;

public enum PaymentMethod {

    CASH("현금"),
    CARD("카드");

    private final String name;

    PaymentMethod(final String name) {
        this.name = name;
    }
}
