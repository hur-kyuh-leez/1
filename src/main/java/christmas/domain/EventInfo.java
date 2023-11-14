package christmas.domain;

public enum EventInfo {
    CHRISTMAS("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    GIFT("증정 이벤트");

    EventInfo(String eventName) {
        this.eventName = eventName;
    }

    private String eventName;
//    private Category[] targetCategory;
//    private int targetDate;
//    private int discountAmount;

//    private int discountForChristmas(int targetDate) {
//        return 1000 + targetDate * 100;
//    }

}
