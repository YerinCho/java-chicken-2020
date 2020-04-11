package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class PaymentMethodTest {

    @Test
    void getPaymentMethodByNumber() {
        assertThat(PaymentMethod.getPaymentMethodByNumber(1)).isEqualTo(PaymentMethod.CARD);
        assertThatThrownBy(() -> PaymentMethod.getPaymentMethodByNumber(3))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
