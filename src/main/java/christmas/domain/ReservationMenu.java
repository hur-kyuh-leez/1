package christmas.domain;

import christmas.domain.Category;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationMenu {

    private static final int MIN_MENU_COUNT = 1;
    private static final int MAX_TOTAL_MENU_COUNT = 20;
    private static final String INVALID_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private Map<String, Integer> reservation;

    public ReservationMenu(String input) {
        List<String> splitInput = splitInput(input);
        validateByRegex(splitInput);
        Map<String, Integer> reservationMap = makeReservation(splitInput);
        validateByMenuCount(reservationMap);
        validateByBeverageOnly(reservationMap);
        validateByUnderZero(reservationMap);
        this.reservation = reservationMap;
    }

    public List<String> splitInput(String input) {
        return Arrays.asList(input.split(","));
    }

    public List<String> validateByRegex(List<String> splitInput) {
        String pattern = "^[가-힣]+-\\d+$";
        Pattern regex = Pattern.compile(pattern);
        for (String s : splitInput) {
            Matcher matcher = regex.matcher(s);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(INVALID_ERROR_MESSAGE + " - 올바르지 않은 형태[메뉴이름(한글)-숫자]");
            }
        }
        return splitInput;
    }

    public void validateByMenuCount(Map<String, Integer> makeReservation) {
        int count = 0;
        for (String s : makeReservation.keySet()) {
            count += makeReservation.get(s);
        }
        if (count > MAX_TOTAL_MENU_COUNT) {
            throw new IllegalArgumentException(INVALID_ERROR_MESSAGE + " - 주문 수가 20을 넘어감");
        }
    }

    public void validateByBeverageOnly(Map<String, Integer> makeReservation) {
        int count = 0;
        for (String s : makeReservation.keySet()) {
            if (Category.findByMenu(s).equals(Category.BEVERAGE)) {
                count++;
            }
        }
        if (count == makeReservation.size()) {
            throw new IllegalArgumentException(INVALID_ERROR_MESSAGE + " - 음료만 주문 할 수 없음");
        }
    }

    public void validateByUnderZero(Map<String, Integer> makeReservation) {
        for (String s : makeReservation.keySet()) {
            if (makeReservation.get(s) < MIN_MENU_COUNT) {
                throw new IllegalArgumentException(INVALID_ERROR_MESSAGE + " - 주문 수가 1보다 작음");
            }
        }
    }

    public Map<String, Integer> makeReservation(List<String> menuList) {
        Map<String, Integer> reservationMap = new HashMap<>();
        for (String s : menuList) {
            String menuName = s.split("-")[0];
            int menuCount = Integer.parseInt(s.split("-")[1]);
            if (reservationMap.containsKey(s)) {
                throw new IllegalArgumentException(INVALID_ERROR_MESSAGE + " - 중복된 메뉴");
            }
            reservationMap.put(menuName, menuCount);
        }
        return reservationMap;
    }

    public Set<Category> getCategorySet() {
        Set<Category> set = new HashSet<>();
        for (String menuName : getReservation().keySet()) {
            set.add(Category.findByMenu(menuName));
        }
        return set;
    }

    public Map<String, Integer> getReservation() {
        return Collections.unmodifiableMap(this.reservation);
    }

}
