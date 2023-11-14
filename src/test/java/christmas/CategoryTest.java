package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Category;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    void 메뉴_있니() {
        assertThat(Category.findByMenu("양송이수프")).isEqualTo(Category.APPETIZER);
    }
    @Test
    void 없는_메뉴() {
        assertThatThrownBy(() -> Category.findByMenu("없는메뉴"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]", "없는 메뉴");
    }

    @Test
    void 메뉴_가격() {
        assertThat(Category.menuPrice("바비큐립")).isEqualTo(54000);
    }

}
