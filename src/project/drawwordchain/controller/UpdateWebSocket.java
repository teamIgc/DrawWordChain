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
import project.drawwordchain.model.WebSocketUser;
import project.drawwordchain.model.Info;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/project/drawwordchain/updatebroadcast")
public class UpdateWebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    // 各jsでWebSocketをNewしているため，staticにしないと共有できない？
    private static final List<WebSocketUser> userList = new ArrayList<WebSocketUser>();

    @OnOpen
    public void connect(Session session) {
        String json = "{\"name\":\"Nobunaga\", \"email\":\"nobunaga@gmail.com\"}";
        try {

            ObjectMapper mapper = new ObjectMapper();
            Info info = mapper.readValue(json, Info.class);
            System.out.println(info.name);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close : " + session.getId());
    }

    @OnMessage
    public void onMessage(String userName, Session session) {

        System.out.println("open : " + session.getId());
        String firstChar = (new FirstChar() ).getChar();

        // Jsonの作成
        // {"playerName" : "Name", "firstChar" : "文字"}
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        builder.append('\"').append("playerName").append('\"');
        builder.append(':');
        builder.append('\"').append(userList.get(0).getName()).append('\"');
        builder.append(',');
        builder.append('\"').append("firstChar").append('\"');
        builder.append(':');
        builder.append('\"').append(firstChar).append('\"');
        builder.append('}');

        String json = builder.toString();
        System.out.println("jsonデータ: "+json);
        System.out.println("いま使用しているsessionID : " + session.getId());
        messageBroadcast(json);
    }

    public void messageBroadcast(String message) {
        System.out.println("全プレイヤーにmessageを返却");
        for ( Session s : sessions ) {
            System.out.println("保存しているID " + s.getId());
            s.getAsyncRemote().sendText(message);
        }
    }
}
