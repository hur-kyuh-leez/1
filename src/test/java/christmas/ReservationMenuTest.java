package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.ReservationMenu;
import org.junit.jupiter.api.Test;

public class ReservationMenuTest {

    @Test
    void 아무거나_입력하면() {
        assertThatThrownBy(() -> new ReservationMenu("ㅁㄴ이라ㅓㅁㄴ이라ㅓ민아럼ㄴ"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("올바르지 않은 형태");
    }

    @Test
    void 예약_테스트_없는메뉴_주문() {
        assertThatThrownBy(() -> new ReservationMenu("첫번째없는메뉴-1,두번째없는메뉴-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없는 메뉴");
    }

    @Test
    void 예약_테스트_음료만_주문() {
        assertThatThrownBy(() -> new ReservationMenu("제로콜라-1,샴페인-4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만");
    }

    @Test
    void 예약_테스트_주문가능수_초과() {
        assertThatThrownBy(() -> new ReservationMenu("티본스테이크-10,아이스크림-4,양송이수프-8"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문 수가 20을 넘어감");
    }

    @Test
    void 예약_테스트_주문이_0() {
        assertThatThrownBy(() -> new ReservationMenu("티본스테이크-0,아이스크림-4,양송이수프-8"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문 수가 1보다");
    }



}
