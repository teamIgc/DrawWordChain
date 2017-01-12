package project.drawwordchain.controller;

import java.io.IOException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/project/drawwordchain/startbroadcast")
public class StartWebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();


    @OnOpen
    public void connect(Session session) {
        System.out.println("stest1");
        sessions.add(session);
    }

    @OnMessage
    public void echoPlayerName(String playerName) {
        System.out.println("stest2");
        playerNameBroadcast(playerName);
    }

    public void playerNameBroadcast(String playerName) {
        for ( Session s : sessions ) {
            s.getAsyncRemote().sendText(playerName);
        }
    }
}
