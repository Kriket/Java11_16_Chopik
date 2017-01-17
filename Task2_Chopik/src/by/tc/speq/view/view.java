package by.tc.speq.view;

import by.tc.speq.controller.Controller;

/**
 * Created by User on 17.01.2017.
 */
public class view {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.executeTask("STOCK_INFO"));
        System.out.println(controller.executeTask("RENT_INFO"));
        System.out.println(controller.executeTask("RENT Boots SNOWBOARDING 30"));
        System.out.println(controller.executeTask("RENT_INFO"));
        System.out.println(controller.executeTask("REGISTRATION Kri kriket"));
        System.out.println(controller.executeTask("SIGN_IN Kri kriket"));

        System.out.println(controller.executeTask("IS_CONSIST Boots SNOWBOARDING 30"));

        // Wrong_REQUEST
        System.out.println(controller.executeTask("RENT_INFOw"));
    }
}
