package by.tc.speq.controller.command.impl;

import by.tc.speq.bean.User;
import by.tc.speq.controller.command.Command;
import by.tc.speq.service.exception.ServiceException;
import by.tc.speq.service.factory.ServiceFactory;
import by.tc.speq.service.ClientService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 17.01.2017.
 */
public class Register implements Command {
    @Override
    public String execute(String request) {

        String login = null;
        String password = null;

        String response = null;

        // получаем параметры из request и инициализируем переменные логин и пароль
        Pattern p = Pattern.compile("(\\S)+");
        Matcher m = p.matcher(request);

        m.find();
        login = request.substring(m.start(), m.end());

        m.find();
        password = request.substring(m.start(), m.end());

        User user = new User(login, password);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            clientService.registration(user);
            response = "Thanks for registration";
        } catch (ServiceException e) {
            // здесь запись в лог

            response = "Error during registration procedure";
        }

        return response;
    }
}
