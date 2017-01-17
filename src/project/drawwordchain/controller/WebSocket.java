package project.drawwordchain.controller;

import java.io.IOException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.Session;

public class WebSocket {

    protected static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

}
