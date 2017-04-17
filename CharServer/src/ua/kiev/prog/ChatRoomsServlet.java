package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Александр on 17.04.2017.
 */
public class ChatRoomsServlet extends HttpServlet {
    private ChatRooms rooms = new ChatRooms();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String room = req.getParameter("room");
        String newRoom = req.getParameter("new");
        String show = req.getParameter("show");
        PrintWriter pw = resp.getWriter();
        if (newRoom.equalsIgnoreCase("yes")) {
            int yes = 0;
            for (int i = 0; i < rooms.getList().size(); i++) {
                if (room.equalsIgnoreCase(rooms.getList().get(i))) {
                    yes++;
                }
                if (yes != 0) {
                    rooms.getList().add(room);
                    pw.println("New room " + room + " was created");
                }
            }
        } else {
            int yes = 0;
            for (int i = 0; i < rooms.getList().size(); i++) {
                if (room.equalsIgnoreCase(rooms.getList().get(i))) {
                    yes++;
                }
                if (yes != 0) {
                    pw.println("You are joined the room " + room);

                }
                else{
                    pw.println("There is no such rooms");
                }
            }
        }
        if (show.equalsIgnoreCase("yes")) {

            pw.println("List of available rooms:");

                pw.write(rooms.toJSON());

        }


    }
}
