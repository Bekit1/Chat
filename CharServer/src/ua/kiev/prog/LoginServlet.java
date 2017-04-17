package ua.kiev.prog;

/**
 * Created by Александр on 16.04.2017.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.servlet.http.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private UsersMap um = new UsersMap();
    private int success = 0;


//        response.sendRedirect("");


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text");
        PrintWriter pw = response.getWriter();

        Set<Map.Entry<String, String>> hms = um.getHm().entrySet();
        for (Map.Entry<String, String> hmse : hms) {
            if (login.equalsIgnoreCase(hmse.getKey()) && password.equalsIgnoreCase(hmse.getValue())) {
                success = 1;
                break;
            } else {
                success = 0;
            }
        }
        if (success == 1) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);
            pw.println("You are logged as " + login);
        } else {
            pw.println("Wrong login or password");
            response.sendRedirect("/login");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String exit = request.getParameter("exit");
        if (exit.equalsIgnoreCase("yes")) {
            HttpSession session = request.getSession(false);
            session.removeAttribute("user_login");
        }

    }
}
