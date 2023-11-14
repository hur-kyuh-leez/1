package christmas.domain;

import java.text.DecimalFormat;
import java.util.*;

public class ReservationResult {

    private final String DECIMAL_FORMAT = "###,###";
    private final DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
    private final String NONE = "없음";
    private final int GIFT_EVENT_STANDARD = 120000;

    private int amountBeforeDiscount;
    private Map<EventInfo, Integer> eventList; // <이벤트명, 적용금액>

    public ReservationResult() {
    }

    public ReservationResult(ReservationMenu reservationMenu, int date) {
        this.eventList = new HashMap<>();
        this.amountBeforeDiscount = calculateBeforeDiscount(reservationMenu);
        calculateGiftMenu();
        calculateEventList(reservationMenu, date);
    }

    //할인전금액
    public int calculateBeforeDiscount(ReservationMenu reservationMenu) {
        int sum = 0;
        for (String menuName : reservationMenu.getReservation().keySet()) {
            int menuCount = reservationMenu.getReservation().get(menuName);
            sum += sum += Category.menuPrice(menuName) * menuCount;
        }
        return sum;
    }

    //증정메뉴
    public void calculateGiftMenu() {
        if (this.amountBeforeDiscount >= GIFT_EVENT_STANDARD) {
            this.eventList.put(EventInfo.GIFT, Category.menuPrice("샴페인"));
        }
    }

    //혜택내역
    public void calculateEventList(ReservationMenu reservationMenu, int date) {
        //예약날짜의 할인 대상 카테고리와 주문한 메뉴의 카테고리가 일치하면 eventList에 추가
        List<EventInfo> fromDate = EventInfo.findAllByDate(date);
        Set<Category> categorySet = reservationMenu.getCategorySet();
        for (EventInfo eventInfo : fromDate) {
            if (categorySet.contains(eventInfo.getTargetCategory())) {
                this.eventList.put(eventInfo, eventInfo.getDiscountAmount(date));
                categorySet.remove(eventInfo.getTargetCategory());
            }
        }
    }

    //총혜택금액
    public int calculateTotalDiscount() {
        int sum = 0;
        for (EventInfo eventInfo : this.eventList.keySet()) {
            sum += this.eventList.get(eventInfo);
        }
        return sum;
    }

    //할인후예상결제금액
    public int amountAfterDiscount() {
        return this.amountBeforeDiscount - calculateTotalDiscount();
    }

    //배지
    public String calculateBadge() {
        return Badge.calculateBadge(this.calculateTotalDiscount());
    }

    public int getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    public Map<EventInfo, Integer> getEventList() {
        return Collections.unmodifiableMap(eventList);
    }

}
