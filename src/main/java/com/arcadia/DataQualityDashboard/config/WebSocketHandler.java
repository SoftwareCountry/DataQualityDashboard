package com.arcadia.DataQualityDashboard.config;

import com.arcadia.DataQualityDashboard.dto.ProgressMessage;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {
    Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @SneakyThrows
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        Gson gson = new Gson();
        ProgressMessage progressMessage = gson.fromJson(message.getPayload(), ProgressMessage.class);

        WebSocketSession destinationSession = sessions.get(progressMessage.getUserId());
        destinationSession.sendMessage(new TextMessage(progressMessage.getPayload()));
    }

    @SneakyThrows
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.getId(), session);
        session.sendMessage(new TextMessage(session.getId()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
       sessions.remove(session.getId());
    }
}
