package project.drawwordchain.controller;

import java.io.IOException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/project/drawwordchain/startbroadcast")
public class StartWebSocket extends WebSocketScope {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    private static final List<String> playerList = new ArrayList<String>();

    @OnOpen
    public void connect(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void echoPlayerName(String playerName) {
        setPlayer(playerName);
        playerNameBroadcast(getPlayer());
    }

    public void playerNameBroadcast(String playerName) {
        for ( Session s : sessions ) {
            s.getAsyncRemote().sendText(playerName);
        }
    }
}
