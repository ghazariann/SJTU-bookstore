// package com.bookstore.bookstore_backend.controller;

// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// public class WebSocketHandler extends TextWebSocketHandler {
// private static final Logger logger =
// LoggerFactory.getLogger(WebSocketHandler.class);

// @Override
// public void afterConnectionEstablished(WebSocketSession session) throws
// Exception {
// logger.info("New WebSocket connection established");
// session.sendMessage(new TextMessage("Hello!"));
// }
// }