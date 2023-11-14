package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private InputView inputView;
    private OutputView outputView;

    public void run() {
        inputView.hello();
        inputView.readDate();
        inputView.parseCategory(inputView.readMenu());

    }
}
