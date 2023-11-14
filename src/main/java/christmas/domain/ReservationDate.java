package christmas.domain;

public class ReservationDate {
    private static final int DECEMBER_START_DATE = 1;
    private static final int DECEMBER_END_DATE = 31;
    private static final String INVALID_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private int date;

    public ReservationDate(String input) {
        this.date = validateDateByRange(validateDateByNotNumber(input));
    }

    public int validateDateByNotNumber(String input) {
        int date = 0;
        try {
            date = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
        return date;
    }

    public int validateDateByRange(int date) {
        if (date < DECEMBER_START_DATE || date > DECEMBER_END_DATE) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
        return date;
    }

    public int getDate() {
        return date;
    }

}
