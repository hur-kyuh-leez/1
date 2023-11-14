package christmas.view;

import christmas.domain.Category;
import christmas.domain.EventInfo;
import christmas.domain.ReservationMenu;
import christmas.domain.ReservationResult;

import java.text.DecimalFormat;

public class OutputView {

    private static final String NONE = "없음";
    private static final String COMMA_FORMAT = "###,###";
    private static final DecimalFormat df = new DecimalFormat(COMMA_FORMAT);

    public void runOutputView(ReservationMenu reservationMenu, int date) {
        printMenu(reservationMenu);
        printMoneyBeforeDiscount(reservationMenu, date);
        printGift(reservationMenu, date);
        printEvent(reservationMenu, date);
        printTotalDiscount(reservationMenu, date);
        printMoneyAfterDiscount(reservationMenu, date);
        printBadge(reservationMenu, date);
    }

    public void printMenu(ReservationMenu reservation) {
        System.out.println("<주문 메뉴>");
        for (String s : reservation.getReservation().keySet()) {
            System.out.println(s + " - " + reservation.getReservation().get(s) + "개");
        }
    }

    public void printMoneyBeforeDiscount(ReservationMenu reservationMenu, int date) {
        ReservationResult result = new ReservationResult(reservationMenu, date);
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(result.calculateBeforeDiscount(reservationMenu) + "원");
    }

    public void printGift(ReservationMenu reservationMenu, int date) {
        ReservationResult result = new ReservationResult(reservationMenu, date);
        System.out.println("<증정 메뉴>");
        if(result.getEventList().get(EventInfo.GIFT) == 0) {
            System.out.println(NONE);
        }
        if(result.getEventList().get(EventInfo.GIFT) != 0) {
            System.out.println("샴페인 1개");
        }
    }

    public void printEvent(ReservationMenu reservationMenu, int date) {
        ReservationResult result = new ReservationResult(reservationMenu, date);
        System.out.println("<혜택 내역>");
        for (EventInfo eventInfo : result.getEventList().keySet()) {
            System.out.println(toFormatString(result, eventInfo));
        }
    }

    public void printTotalDiscount(ReservationMenu reservationMenu, int date) {
        ReservationResult result = new ReservationResult(reservationMenu, date);
        System.out.println("<총혜택 금액>");
        System.out.println("-"+intToFormatString(result.calculateTotalDiscount())+"원");
    }

    public void printMoneyAfterDiscount(ReservationMenu reservationMenu, int date) {
        ReservationResult result = new ReservationResult(reservationMenu, date);
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(intToFormatString(result.amountAfterDiscount())+"원");
    }

    public void printBadge(ReservationMenu reservationMenu, int date) {
        ReservationResult result = new ReservationResult(reservationMenu, date);
        System.out.println("<12월 이벤트 배지>");
        System.out.println(result.calculateBadge());
    }

     public String toFormatString(ReservationResult result, EventInfo eventInfo) {
        String COMMA_FORMAT = "###,###";
        DecimalFormat df = new DecimalFormat(COMMA_FORMAT);
        return eventInfo.getEventName()+": "+"-"+df.format(result.getEventList().get(eventInfo));
    }

    public String intToFormatString(int number) {
        String COMMA_FORMAT = "###,###";
        DecimalFormat df = new DecimalFormat(COMMA_FORMAT);
        return df.format(number);
    }

}
