package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MenuRepositoryTest {

    @Test
    @DisplayName("메뉴 입력이 유효한지 테스트")
    void validateTableNumber() {
        assertThatThrownBy(() -> MenuRepository.validateMenuNumber(20))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
