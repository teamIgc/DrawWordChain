package project.drawwordchain.controller;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/project/drawwordchain/startbroadcast")
public class SampleWebSocket {

    @OnMessage
    public void broadcast(String message, Session session) throws IOException {
        for ( Session s : session.getOpenSessions() ) {
            s.getAsyncRemote().sendText(message);
        }
    }
}
