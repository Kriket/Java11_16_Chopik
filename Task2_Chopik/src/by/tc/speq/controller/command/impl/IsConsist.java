package by.tc.speq.controller.command.impl;

import by.tc.speq.bean.Category;
import by.tc.speq.bean.SportEquipment;
import by.tc.speq.controller.command.Command;
import by.tc.speq.service.ShopService;
import by.tc.speq.service.exception.ServiceException;
import by.tc.speq.service.factory.ServiceFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 17.01.2017.
 */
public class IsConsist implements Command {
    @Override
    public String execute(String request) {
        String title = null;
        Category category = Category.SNOWBOARDING;
        int price = 0;

        String response = null;

        // получаем параметры из request и инициализируем переменные title, category, price

        Pattern p = Pattern.compile("(\\S)+");
        Matcher m = p.matcher(request);

        m.find();
        title = request.substring(m.start(), m.end());

        m.find();
        String temp = request.substring(m.start(), m.end());
        if (temp.toUpperCase().equals("SNOWBOARDING")) {
            category = Category.SNOWBOARDING;
        } else if (temp.toUpperCase().equals("AIRSOFT")) {
            category = Category.AIRSOFT;
        }

        m.find();
        price = Integer.parseInt(request.substring(m.start(), m.end()));

        SportEquipment sportEquipment = new SportEquipment(title, category, price);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ShopService shopService = serviceFactory.getShopService();
        try {
            if (shopService.isConsistEquipment(sportEquipment)) {
                response = "Consist";
            } else {
                response = "Non consist";
            }
        } catch (ServiceException e) {
            // здесь запись в лог

            response = "Incorrect data";
        }

        return response;
    }
}
