package christmas.controller;

import christmas.domain.ReservationDate;
import christmas.domain.ReservationMenu;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    public static void run() {
        InputView inputView = new InputView();
        int date = inputView.runInputView();
        ReservationMenu reservationMenu = inputView.readMenu();
        OutputView outputView = new OutputView();
        outputView.runOutputView(reservationMenu, date);
    }
}
