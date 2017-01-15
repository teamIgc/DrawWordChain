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

public class WebSocketScope {

    protected static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    // 各jsでWebSocketをNewしているため，staticにしないと共有できない？
    protected static final List<WebSocketUser> userList = new ArrayList<WebSocketUser>();

    // StartとUpdateのSessionを入れ替えるメソッド
}
