package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderedMenusTest {

    OrderedMenus orderedMenus = new OrderedMenus();

    @BeforeEach
    void setup() {
        orderedMenus.order(MenuRepository.menus().get(2), 1);
    }

    @Test
    @DisplayName("주문된 메뉴 등록 확인")
    void order() {
        assertThat(orderedMenus.getOrderedMenus().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("주문한 메뉴가 존재하는지 확인")
    void isMenuContain() {
        assertThat(orderedMenus.isMenuContain(MenuRepository.menus().get(2))).isTrue();
        assertThat(orderedMenus.isMenuContain(MenuRepository.menus().get(1))).isFalse();
    }

    @Test
    @DisplayName("주문한 메뉴 또 주문시 수량만 추가")
    void orderMore() {
        orderedMenus.order(MenuRepository.menus().get(2), 1);
        assertThat(orderedMenus.getOrderedMenus().size()).isEqualTo(1);
    }
}

