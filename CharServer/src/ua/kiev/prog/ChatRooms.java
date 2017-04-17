package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 17.04.2017.
 */
public class ChatRooms {
    private List<String> list=new ArrayList<>();

    public ChatRooms() {
        this.add();
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
    public static ChatRooms fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, ChatRooms.class);
    }
    public void add(){

        list.add("1");
        list.add("2");
    }
}
