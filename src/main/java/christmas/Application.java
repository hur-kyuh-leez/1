package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.Controller;

public class Application {
    public static void main(String[] args) {
        try {
            Controller.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
