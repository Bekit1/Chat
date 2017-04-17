package ua.kiev.prog;

import java.util.HashMap;

/**
 * Created by Александр on 16.04.2017.
 */
public class UsersMap {
    private HashMap<String, String> hm = new HashMap<>();

    public UsersMap() {
        this.create();
    }
private void create(){
    hm.put("bekit", "qwerty");
    hm.put("123", "123");
    hm.put("qwe", "qwe");
    hm.put("asd", "asd");
    hm.put("zxc", "zxc");
    hm.put("test1", "test2");
}

    public HashMap<String, String> getHm() {
        return hm;
    }

}
