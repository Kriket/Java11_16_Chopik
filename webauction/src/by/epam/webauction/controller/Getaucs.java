package by.epam.webauction.controller;

import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.CommandHelper;
import by.epam.webauction.controller.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Getaucs extends HttpServlet {

    public Getaucs() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        ICommand command = CommandHelper.getInstance().getCommand(commandName);

        String page = null;

        try {
            page = command.execute(request, response);
        } catch (CommandException e) {
            page = JspPageName.ERROR_PAGE;
        }

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println(page);
        pw.close();
    }

}
