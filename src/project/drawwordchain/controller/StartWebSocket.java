package project.drawwordchain.controller;

import java.io.IOException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import project.drawwordchain.model.WebSocketUser;

@ServerEndpoint("/project/drawwordchain/startbroadcast")
public class StartWebSocket extends WebSocketScope {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    private final List<WebSocketUser> userList = new ArrayList<WebSocketUser>();

    @OnOpen
    public void connect(Session session) {
        System.out.println("open : " + session.getId());
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("close : " + session.getId());
    }

    @OnMessage
    public void echoUserName(String userName) {
        System.out.println("メッセージの確認");
        WebSocketUser wUser = new WebSocketUser();
        userList.add(userName);
        userNameBroadcast();
    }

    public void userNameBroadcast() {
        System.out.println("全プレイヤーにプレイヤー名を返却");
        String userNameColumn = "";
        Iterator<String> iterator = userList.iterator();
        while(iterator.hasNext()){
            userNameColumn += iterator.next();
            if(iterator.hasNext()) {
                userNameColumn += ",";
            }
        }
        System.out.println("送信データ:" + userNameColumn);
        for ( Session s : sessions ) {
            s.getAsyncRemote().sendText(userNameColumn);
        }
    }
}
