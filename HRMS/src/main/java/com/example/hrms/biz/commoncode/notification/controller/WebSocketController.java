package com.example.hrms.biz.commoncode.notification.controller;

import com.example.hrms.biz.commoncode.notification.model.WebSocketNotification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
@Controller
public class WebSocketController {

  @MessageMapping("/notification.connect")
  public void connect(@Payload WebSocketNotification notification,
      SimpMessageHeaderAccessor headerAccessor) {
    // Lưu username vào WebSocket session để sử dụng sau này
    String username = (String) notification.getData();
    headerAccessor.getSessionAttributes().put("username", username);
  }

  @MessageMapping("/notification.read")
  public void markAsRead(@Payload WebSocketNotification notification) {
    // Xử lý thông báo đánh dấu đã đọc từ client
    // Có thể gọi notificationService ở đây nếu cần
  }
}