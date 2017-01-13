package project.drawwordchain.controller;

import java.util.ArrayList;
import java.util.List;

public class WebSocketScope {

    private List<String> playerList = new ArrayList<String>();

    protected void setPlayer(String playerName) {
        playerList.add(playerName);
    }

    protected String getPlayer() {
        return playerList.get(0);
    }
}
