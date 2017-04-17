package prog.kiev.ua;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private int pn;
    private String login;
    private String to;

    public GetThread() {
        gson = new GsonBuilder().create();
    }

    @Override
    public void run() {
        try {

            while (!Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + n);

                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream is = http.getInputStream();
                try {
                    byte[] buf = requestBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        int i = 0;
                        for (Message m : list.getList()) {
                            try {
                                if (((list.getList().get(i).getTo() == null) || list.getList().get(i).getTo().equalsIgnoreCase(login) || list.getList().get(i).getTo().equalsIgnoreCase(to))) {
                                    System.out.println(m);
                                }
                            }catch(NullPointerException e){
                                System.out.print(" ");
                            }
                                n++;
                                i++;
                        }
                    }
                } finally {
                    is.close();
                }

                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void read(InputStream is) {
        try {
            byte[] buf = requestBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            System.out.println(strBuf);
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public void login() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input your login:");
            String login = sc.nextLine();
            System.out.println("Input your password:");
            String password = sc.nextLine();
            URL url = new URL(Utils.getURL() + "/login?login=" + login + "&" + "password=" + password);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream is = http.getInputStream();
            this.login = login;
            try {
                byte[] buf = requestBodyToArray(is);
                String strBuf = new String(buf, StandardCharsets.UTF_8);
                System.out.print(strBuf);
            } finally {
                is.close();
            }
        } catch (Exception e) {
            System.out.println("Wrong login or password");
            login();
        }
    }


    public String getLogin() {
        return login;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}