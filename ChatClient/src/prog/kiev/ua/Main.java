package prog.kiev.ua;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Instruction inst=new Instruction();
        inst.instruction();
        try {
            GetThread gt = new GetThread();
//            gt.login();
//            String login = gt.getLogin();
//            Thread th = new Thread(gt);
//            th.setDaemon(true);
//            th.start();
            HelpClass help = new HelpClass();
            help.start(gt);

            System.out.println("Enter your message: ");
            help.actions(gt, gt.getLogin(), scanner);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

}
