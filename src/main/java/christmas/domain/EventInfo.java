package christmas.domain;

import java.util.*;
import java.util.function.Function;

public enum EventInfo {
    CHRISTMAS("크리스마스 디데이 할인"
            , Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
            , date -> (date - 1) * 100
            , Category.ALL)
    , WEEKDAY("평일 할인"
            , Arrays.asList(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21)
            , amount -> 2023
            , Category.DESSERT)
    , WEEKEND("주말 할인"
            , Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23)
            , amount -> 2023
            , Category.MAIN)
    , SPECIAL("특별 할인"
            , Arrays.asList(3, 10, 17, 24, 31)
            , amount -> 1000
            , Category.ALL)
    , GIFT("증정 이벤트"
            , Collections.emptyList()
            , amount -> 0
            , Category.BEVERAGE);

    private String eventName;
    private List<Integer> eventDate;
    private Function<Integer, Integer> discountAmount;
    private Category targetCategory;

    EventInfo(String eventName, List<Integer> eventDate, Function<Integer, Integer> discountAmount, Category targetCategory) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.targetCategory = targetCategory;
        this.discountAmount = discountAmount;
    }

    public int calculate(int amount) {
        return discountAmount.apply(amount);
    }

    public boolean hasDate(int date) {
        return eventDate.stream().anyMatch(d -> d == date);
    }

    public static List<EventInfo> findAllByDate(int date) {
        List<EventInfo> list = new ArrayList<>();
        for (EventInfo eventInfo : EventInfo.values()) {
            if (eventInfo.hasDate(date)) {
                list.add(eventInfo);
            }
        }
        return list;
    }

    public String getEventName() {
        return eventName;
    }

    public List<Integer> getEventDate() {
        return eventDate;
    }

    public int getDiscountAmount(int date) {
        return calculate(date);
    }

    public Category getTargetCategory() {
        return targetCategory;
    }
}
