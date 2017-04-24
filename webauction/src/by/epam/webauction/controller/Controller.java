package by.epam.webauction.controller;

import by.epam.webauction.logic.CommandException;
import by.epam.webauction.logic.CommandHelper;
import by.epam.webauction.logic.ICommand;
import by.epam.webauction.logic.JspPageName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 17.02.2017.
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        ICommand command = CommandHelper.getInstance().getCommand(commandName);
        String page = null;
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            // Переписать
            page = JspPageName.ERROR_PAGE;
        } catch (Exception e) {
            page = JspPageName.ERROR_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            errorMessageDirectlyFromResponse(response);
        }
    }

    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }
}
