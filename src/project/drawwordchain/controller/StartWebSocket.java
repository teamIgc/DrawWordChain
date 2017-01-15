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

    @OnOpen
    public void connect(Session session) {
        System.out.println("open : " + session.getId());
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close : " + session.getId());
        //セッションIDをとり　それでセッション判別して対応しているnameを削除する
        for (int i = 0; i < userList.size(); i++) {
            if( userList.get(i).getSessionId() == session.getId() ) {
                userList.remove(i);
            }
        }
        sessions.remove(session);
        userNameBroadcast();
    }

    @OnMessage
    public void echoUserName(String userName, Session session) {
        System.out.println("メッセージの確認");
        WebSocketUser wUser = new WebSocketUser();
        wUser.setName(userName);
        wUser.setSessionId(session.getId());
        userList.add(wUser);
        System.out.println("サイズ: "+userList.size());
        userNameBroadcast();
    }

    public void userNameBroadcast() {
        System.out.println("全プレイヤーにプレイヤー名を返却");
        String userNameColumn = "";
        Iterator<WebSocketUser> iterator = userList.iterator();
        while(iterator.hasNext()){
            userNameColumn += iterator.next().getName();
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
