package project.drawwordchain.controller;

import java.io.IOException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/project/drawwordchain/startbroadcast")
public class StartWebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    @OnOpen
    public void connect(Session session) {
        sessions.add(session);
        broadcast();
    }

    public void broadcast() {
        for ( Session s : sessions ) {
            s.getAsyncRemote().sendText("message");
        }
    }
}
