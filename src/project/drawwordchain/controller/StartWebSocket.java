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

@ServerEndpoint("/project/drawwordchain/startbroadcast")
public class StartWebSocket extends WebSocketScope {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    private static final List<String> playerList = new ArrayList<String>();

    @OnOpen
    public void connect(Session session) {
        System.out.println("接続の確認/sessionの保存");
        sessions.add(session);
        for ( Session s : sessions ) {
                System.out.println("全体 : " + s.getId());
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("close : " + session.getId());
    }

    @OnMessage
    public void echoPlayerName(String playerName) {
        System.out.println("メッセージの確認");
        playerList.add(playerName);
        playerNameBroadcast();
    }

    public void playerNameBroadcast() {
        System.out.println("全プレイヤーにプレイヤー名を返却");
        String playerNameColumn = "";
        Iterator<String> iterator = playerList.iterator();
        while(iterator.hasNext()){
            playerNameColumn += iterator.next();
            if(iterator.hasNext()) {
                playerNameColumn += ",";
            }
        }
        System.out.println("送信データ:" + playerNameColumn);
        for ( Session s : sessions ) {
            s.getAsyncRemote().sendText(playerNameColumn);
        }
    }
}
