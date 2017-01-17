package by.tc.speq.controller.command.impl;

import by.tc.speq.bean.SportEquipment;
import by.tc.speq.controller.command.Command;
import by.tc.speq.service.ShopService;
import by.tc.speq.service.exception.ServiceException;
import by.tc.speq.service.factory.ServiceFactory;

import java.util.Map;

/**
 * Created by User on 17.01.2017.
 */
public class GetStockInfo implements Command {
    @Override
    public String execute(String request) {

        StringBuilder response = new StringBuilder();

        response.append(System.getProperty("line.separator"));
        response.append("Info about goods in stock:");
        response.append(System.getProperty("line.separator"));

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ShopService shopService = serviceFactory.getShopService();
            for (Map.Entry<SportEquipment, Integer> pair : shopService.getStockInfo().entrySet()) {
                response.append(pair.getKey().getInfo() + ", quantity: " + pair.getValue());
                response.append(System.getProperty("line.separator"));
            }
        } catch (ServiceException e) {
            // здесь запись в лог

            response.append("Error during getting info procedure");
        }

        return response.toString();
    }
}
