package prog.kiev.ua;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Александр on 17.04.2017.
 */
public class HelpClass {
    private ChatRooms chatrooms = new ChatRooms();
    private final Gson gson;

    public HelpClass() {
        gson = new GsonBuilder().create();
    }



    public void start(GetThread gt) {
        gt.login();
        String login = gt.getLogin();
        Thread th = new Thread(gt);
        th.setDaemon(true);
        th.start();
    }

    public void actions(GetThread gt, String login, Scanner scanner) throws IOException {
        while (true) {
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("private")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter nickname: ");
                String pr = sc.nextLine();
                gt.setTo(pr);
                System.out.println("Enter your message: ");
                String mes = sc.nextLine();
                Message m = new Message(login, pr, mes);
                int res = m.send(Utils.getURL() + "/add");
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            } else {
                if (text.equalsIgnoreCase("/getUsers")) {
                    URL url = new URL(Utils.getURL() + "/users");
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    InputStream is = http.getInputStream();
                    gt.read(is);
                }
                if (text.isEmpty()) break;
                Message m = new Message(login, text);
                int res = m.send(Utils.getURL() + "/add");
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
            if (text.equalsIgnoreCase("/exit")) {
                URL obj = new URL(Utils.getURL() + "/login?exit=yes");
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                // this.setGt(new GetThread());
                this.start(gt);
                this.actions(gt, gt.getLogin(), scanner);
            }
            if (text.equalsIgnoreCase("/getRooms")) {
                URL url = new URL(Utils.getURL() + "/rooms?room=null&new=no&show=yes");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();
                try {
                    byte[] buf = requestBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    chatrooms = gson.fromJson(strBuf, ChatRooms.class);
                   for(String s:chatrooms.getList()){
                       System.out.println(s);
                   }

                } finally {
                    is.close();
                }
            }
            if (text.equalsIgnoreCase("/joinRoom")) {
                System.out.println("Enter the name of room:");
                String room = scanner.nextLine();

                URL url = new URL(Utils.getURL() + "/rooms?room=" + room + "&new=no&show=yes");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();
                gt.read(is);
            }
        }
    }

    private byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

}