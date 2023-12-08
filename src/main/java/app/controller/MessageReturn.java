package app.controller;

import java.util.List;
import java.util.Map;

public class MessageReturn {
    public String msg;
    public List<Map<String, Object>>  listCables;
    public MessageReturn(String msg, List<Map<String, Object>>  listCables) {
        this.msg = msg;
        this.listCables =listCables;
    }

}
