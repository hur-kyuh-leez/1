package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum Category {
    APPETIZER("애피타이저", Arrays.asList("양송이수프", "타파스", "시저샐러드"), Arrays.asList(6000, 5500, 8000)),
    MAIN("메인", Arrays.asList("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"), Arrays.asList(55000, 54000, 35000, 25000)),
    DESSERT("디저트", Arrays.asList("초코케이크", "아이스크림"), Arrays.asList(15000, 5000)),
    Beverage("음료", Arrays.asList("제로콜라", "레드와인", "샴페인"), Arrays.asList(3000, 60000, 25000));

    private static final String INVALID_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private String korean;
    private List<String> menu;
    private List<Integer> price;

    Category(String korean, List<String> menu, List<Integer> price) {
        this.korean = korean;
        this.menu = menu;
        this.price = price;
    }

    public String getKorean() {
        return korean;
    }

    public List<String> getMenu() {
        return menu;
    }

    public List<Integer> getPrice() {
        return price;
    }

    public boolean hasMenu(String menuName) {
        return menu.stream().anyMatch(name -> name.equals(menuName));
    }

    public static Category findByMenu(String menuName) {
        return Arrays.stream(values())
                .filter(menu -> menu.hasMenu(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ERROR_MESSAGE + " - 없는 메뉴"));
    }
}
