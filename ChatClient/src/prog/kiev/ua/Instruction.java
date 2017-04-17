package prog.kiev.ua;

/**
 * Created by Александр on 17.04.2017.
 */
public class Instruction {
    public Instruction() {
    }
    public void instruction(){
        System.out.println("At first you should type your right login and password");
        System.out.println("If you want send private messege, than type in chat 'private', and then type nick of your friend ");
        System.out.println("If you want see all users that registered in the chat than type '/getUsers'");
        System.out.println("If you want logout than type'/exit'");
    }
}
