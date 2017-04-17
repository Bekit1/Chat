package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 17.04.2017.
 */
public class UsersServlet extends HttpServlet {
    private UsersMap um = new UsersMap();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("Users list:");
        Set<Map.Entry<String, String>> hms = um.getHm().entrySet();
        for (Map.Entry<String, String> hmse : hms) {
           pw.println(hmse.getKey().toString());
        }
    }
}
