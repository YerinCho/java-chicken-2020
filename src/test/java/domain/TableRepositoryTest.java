package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TableRepositoryTest {

    @Test
    @DisplayName("테이블 입력이 유효한지 테스트")
    void validateTableNumber() {
        assertThatThrownBy(() -> TableRepository.validateTableNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
