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

import project.drawwordchain.model.FirstChar;

@ServerEndpoint("/project/drawwordchain/updatebroadcast")
public class UpdateWebSocket extends StartWebSocket {

    @OnOpen
    public void connect(Session session) {
        // String FirstChar = (new FirstChar() ).getChar();

    }

    @OnClose
    public void onClose(Session session) {
        // System.out.println("close : " + session.getId());
        // //セッションIDをとり　それでセッション判別して対応しているnameを削除する
        // for (int i = 0; i < userList.size(); i++) {
        //     if( userList.get(i).getSessionId() == session.getId() ) {
        //         userList.remove(i);
        //     }
        // }
        // sessions.remove(session);
        // userNameBroadcast();
    }

    @OnMessage
    public void echoUserName(String userName, Session session) {
        // System.out.println("メッセージの確認");
        // WebSocketUser wUser = new WebSocketUser();
        // wUser.setName(userName);
        // wUser.setSessionId(session.getId());
        // userList.add(wUser);
        // System.out.println("サイズ: "+userList.size());
        // userNameBroadcast();
    }

    public void firstCharBroadcast() {
        // System.out.println("全プレイヤーに最初の文字を返却");
        // String firstChar = (new FirstChar() ).getChar();
        // for ( Session s : sessions ) {
        //     s.getAsyncRemote().sendText();
        // }
    }
}