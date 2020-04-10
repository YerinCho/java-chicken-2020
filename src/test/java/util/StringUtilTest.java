package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringUtilTest {

    @Test
    @DisplayName("문자열 입력 숫자로 바꾸기")
    void toInteger(){
        assertThat(StringUtil.toInteger("3")).isEqualTo(3);
        assertThatThrownBy(()->StringUtil.toInteger("a")).isInstanceOf(NumberFormatException.class);
    }
}
