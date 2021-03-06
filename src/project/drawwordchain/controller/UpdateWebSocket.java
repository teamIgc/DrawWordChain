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
import project.drawwordchain.model.DataManager;
import project.drawwordchain.model.Info;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/project/drawwordchain/updatebroadcast")
public class UpdateWebSocket extends WebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    private static boolean userListFlag = true;

    private boolean redirectFlag = false;

    @OnOpen
    public void connect(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("updateWSclose : " + session.getId());
        // 最後の時(全員が離脱するときはallclear)
        if(redirectFlag) {
            userList.clear();
            userListFlag = true;
        } else {
            for (Session s : sessions) {
                for (WebSocketUser wsUser : userList) {
                    if( s.getId() == wsUser.getSessionId() ) {
                        userList.remove(wsUser);
                    }
                }
            }
        }
        sessions.remove(session);
    }

    // json = {"playerName":"myName","userList":["test","test2"],"imgName":"","img":""}
    @OnMessage
    public void onMessage(String json, Session session) {

        // jsonデータの取り出し
        Info info = new Info();
        try {
            ObjectMapper mapper = new ObjectMapper();
            info = mapper.readValue(json, Info.class);
            System.out.println(info.playerName);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }

        // playerName,userListのとき(1回目の受信/開始ボタンが押された時)
        if(info.imgName.length() == 0) {

            // 送られてきたuserListを自クラスのuserListに挿入
            // sessionをuserNameに合わせて挿入
            setUserInfo(info, session);

            String firstChar = (new FirstChar()).getChar();
            // Jsonの作成
            // {"playerName" : "Name", "firstChar" : "文字"}
            StringBuilder builder = new StringBuilder();
            builder.append('{');
            builder.append('\"').append("playerName").append('\"');
            builder.append(':');
            // wUserにはsessionがあるので，wUser自体を削除することはできない->取得条件(終わった人は名前を空文字にするなど)をここで判断する必要がある
            builder.append('\"').append(userList.get(0).getName()).append('\"');
            builder.append(',');
            builder.append('\"').append("firstChar").append('\"');
            builder.append(':');
            builder.append('\"').append(firstChar).append('\"');
            builder.append('}');

            String sendJson = builder.toString();
            messageBroadcast(sendJson);

        // playerName,img,imgNameのとき(2回目以降の受信/送信ボタンが押された時)
        } else {
            // DataManagerに登録
            DataManager dataMgr = new DataManager();
            dataMgr.setStatement(info.playerName, info.imgName, info.img);

            // 現在のプレイヤー名を削除
            for (int i = 0; i < userList.size(); i++) {
                if( (userList.get(i).getName().equals(info.playerName) ) ) {
                    userList.get(i).setName("");
                    break;
                }
            }

            // 次のプレイヤー名を取得
            String playerName = "";
            int count = userList.size();
            for (int i = 0; i < userList.size(); i++) {
                if( !(userList.get(i).getName() == "") ) {
                    playerName = userList.get(i).getName();
                    break;
                }
                count--;
                if (count == 0) {
                    redirectFlag = true;
                }
            }

            // Jsonの作成
            // {"img":"画像データ","playerName":"プレイヤー名","redirectFlag":"終了する"}
            StringBuilder builder = new StringBuilder();
            builder.append('{');
            builder.append('\"').append("img").append('\"');
            builder.append(':');
            builder.append('\"').append(info.img).append('\"');
            builder.append(',');
            builder.append('\"').append("playerName").append('\"');
            builder.append(':');
            builder.append('\"').append(playerName).append('\"');
            builder.append(',');
            builder.append('\"').append("redirectFlag").append('\"');
            builder.append(':');
            builder.append(redirectFlag);
            builder.append('}');
            String sendJson = builder.toString();
            messageBroadcast(sendJson);
        }

    }


    public void messageBroadcast(String message) {
        System.out.println("全プレイヤーにmessageを返却");
        for ( Session s : sessions ) {
            System.out.println("保存しているID " + s.getId());
            s.getAsyncRemote().sendText(message);
        }
    }

    private void setUserInfo(Info info, Session session) {
        // 送られてきたuserListを保存
        if (userListFlag) {
            for (int i = 0; i < info.userList.length; i++) {
                WebSocketUser wUser = new WebSocketUser();
                wUser.setName(info.userList[i]);
                userList.add(wUser);
            }
            userListFlag = false;
        }

        // userListの中にある名前と一致した名前のセッションを登録
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getName().equals(info.playerName)) {
                userList.get(i).setSessionId(session.getId());
            }
        }

        System.out.println("セッションのsize"+sessions.size());
        System.out.println("UserListのsize"+userList.size());
    }
}
