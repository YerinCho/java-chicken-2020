package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    private OrderedMenus orderedMenus = new OrderedMenus();

    @BeforeEach
    void setUp() {
        orderedMenus.order(MenuRepository.menus().get(2), 1);
        orderedMenus.order(MenuRepository.menus().get(2), 1);
        orderedMenus.order(MenuRepository.menus().get(7), 3);
    }

    @Test
    @DisplayName("주문한 내역에 따라서 최종 결제금액 반환")
    void calculatePay() {
        orderedMenus.order(MenuRepository.menus().get(2), 1);
        orderedMenus.order(MenuRepository.menus().get(2), 1);
        orderedMenus.order(MenuRepository.menus().get(7), 3);
        assertThat(Payment.calculatePay(orderedMenus.getOrderedMenus(), PaymentMethod.CARD))
                .isEqualTo(16000 * 2 + 3000);
    }

    @Test
    @DisplayName("현금결제 5% 할인 추가")
    void discountCash() {
        assertThat(Payment.calculatePay(orderedMenus.getOrderedMenus(), PaymentMethod.CASH))
                .isEqualTo(16000 * 2 + 3000 * 0.95);
    }

    @Test
    @DisplayName("10개 이상 구매시 10000원 할인")
    void saleWhenBuyMoreTen() {
        orderedMenus.order(MenuRepository.menus().get(2), 10);
        assertThat(Payment.calculatePay(orderedMenus.getOrderedMenus(), PaymentMethod.CARD))
                .isEqualTo(16000 * 12 + 3000 - 10000);
    }

    @Test
    @DisplayName("음료 10 개 이상 구매했을 때 할인이 안들어가는지 확인")
    void beverageTenTest() {
        OrderedMenus orderedBeverageMenus = new OrderedMenus();
        orderedBeverageMenus.order(MenuRepository.menus().get(6), 12);
        assertThat(Payment.calculatePay(orderedBeverageMenus.getOrderedMenus(), PaymentMethod.CARD)).isEqualTo(1000 * 12);

    }

    @Test
    @DisplayName("현금할인 복리확인")
    void cashAndTen() {
        orderedMenus.order(MenuRepository.menus().get(2), 10);
        assertThat(Payment.calculatePay(orderedMenus.getOrderedMenus(), PaymentMethod.CASH))
                .isEqualTo((16000 * 12 + 3000 - 10000) * 0.95);
    }
}
