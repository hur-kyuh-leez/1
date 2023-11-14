package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Category;
import christmas.domain.ReservationDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReservationDateTest {

    @Test
    void 올바르지_않은_입력_숫자가_아님() {
        assertThatThrownBy(() -> new ReservationDate("숫자가 아님"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-10", "0", "35"})
    void 올바르지_않은_입력_범위밖(String input) {
        assertThatThrownBy(() -> new ReservationDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜");
    }
}
