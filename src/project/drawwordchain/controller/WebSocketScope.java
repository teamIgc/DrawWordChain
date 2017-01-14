package project.drawwordchain.controller;

import java.util.ArrayList;
import java.util.List;

public class WebSocketScope {

    private List<String> userList = new ArrayList<String>();

    protected void setUser(String userName) {
        userList.add(userName);
    }

    protected String getUser() {
        return userList.get(0);
    }
}
