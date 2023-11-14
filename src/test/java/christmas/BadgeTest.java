package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.Badge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BadgeTest {

    @ValueSource(ints = {1000, 1500, 2000})
    @ParameterizedTest
    void 배지_이름_테스트(int input) {
        assertThat(Badge.calculateBadge(input)).isEqualTo("없음");
    }

}
