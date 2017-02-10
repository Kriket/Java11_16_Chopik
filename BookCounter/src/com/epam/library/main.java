package com.epam.library;

import com.epam.library.controller.Controller;

/**
 * Created by User on 10.02.2017.
 */
public class main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.executeTask("READED_MORE_THEN_ONE_BOOK "));
        System.out.println();
        System.out.println(controller.executeTask("READED_TWO_OR_MORE_BOOK "));
        System.out.println();
        System.out.println(controller.executeTask("RENAME_BOOK JAVA2 K8M8L"));
        System.out.println();
        System.out.println(controller.executeTask("DELETE_BOOK 3"));
        System.out.println();
        System.out.println(controller.executeTask("ADD_BOOK 310 KKKKK 2017 Bruse"));
    }

}
