package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderedMenuTest {

    @Test
    @DisplayName("주문한 음식이 메뉴에 존재하는지 유효성 검사")
    void orderMenuTest() {
        assertThatThrownBy(() -> new OrderedMenu(new Menu(4, "통구이", Category.BEVERAGE, 16000), 3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문한 수량이 범위 내인지 유효성 검사")
    void orderNumberTest() {
        assertThatThrownBy(() -> new OrderedMenu(MenuRepository.menus().get(1), 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderedMenu(MenuRepository.menus().get(1), 100))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("추가주문시 수량 증가 확인")
    void orderMore(){
        OrderedMenu orderedMenu = new OrderedMenu(MenuRepository.menus().get(1),1);
        orderedMenu.orderMore(4);
        assertThat(orderedMenu.getNumber()).isEqualTo(5);
    }

    @Test
    @DisplayName("주문한 음식 확인")
    void isContain() {
        OrderedMenu orderedMenu = new OrderedMenu(MenuRepository.menus().get(1), 1);
        assertThat(orderedMenu.isContain(MenuRepository.menus().get(1))).isTrue();
        assertThat(orderedMenu.isContain(MenuRepository.menus().get(2))).isFalse();
    }

    @Test
    @DisplayName("가격 반환")
    void calculatePrice() {
        OrderedMenu orderedMenu = new OrderedMenu(MenuRepository.menus().get(1), 1);
        orderedMenu.orderMore(4);
        assertThat(orderedMenu.calculatePrice()).isEqualTo(5 * 16000);
    }

}
