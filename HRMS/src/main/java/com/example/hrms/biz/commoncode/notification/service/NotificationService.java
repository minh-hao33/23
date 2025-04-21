package com.example.hrms.biz.commoncode.notification.service;

import com.example.hrms.biz.commoncode.notification.model.Notification;
import com.example.hrms.biz.commoncode.notification.model.dto.NotificationDTO;
import com.example.hrms.biz.commoncode.notification.repository.NotificationMapper;
import com.example.hrms.biz.commoncode.notification.model.WebSocketNotification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

  private final NotificationMapper notificationMapper;
  private final SimpMessagingTemplate messagingTemplate;

  public NotificationService(NotificationMapper notificationMapper, SimpMessagingTemplate messagingTemplate){
    this.notificationMapper = notificationMapper;
    this.messagingTemplate = messagingTemplate;
  }

  /**
   * Tạo thông báo mới
   */
  public Notification createNotification(Notification notification) {
    // Đảm bảo createdAt được thiết lập
    if (notification.getCreatedAt() == null) {
      notification.setCreatedAt(LocalDateTime.now());
    }

    notificationMapper.insert(notification);

    // Đổi từ Entity sang DTO để thêm thông tin bổ sung
    NotificationDTO.Resp dto = convertToResp(notificationMapper.findById(notification.getId()));

    // Gửi thông báo qua WebSocket đến người nhận cụ thể
    messagingTemplate.convertAndSendToUser(
        notification.getReceiver(),
        "/queue/notifications",
        new WebSocketNotification("NEW_NOTIFICATION", dto)
    );

    return notification;
  }

  /**
   * Tạo thông báo từ Req DTO
   */
  public NotificationDTO.Resp createNotification(NotificationDTO.Req request) {
    Notification notification = request.toNotification();
    createNotification(notification);
    return NotificationDTO.Resp.toResponse(notification);
  }

  /**
   * Lấy danh sách thông báo của người dùng
   */
  public List<NotificationDTO.Resp> getNotificationsByReceiver(String receiver) {
    List<NotificationDTO> notifications = notificationMapper.findByReceiver(receiver);
    return notifications.stream()
        .map(this::convertToResp)
        .collect(Collectors.toList());
  }

  /**
   * Lấy thông tin chi tiết của một thông báo
   */
  public NotificationDTO.Resp getNotificationById(Long id) {
    NotificationDTO notification = notificationMapper.findById(id);
    if (notification != null) {
      return convertToResp(notification);
    }
    return null;
  }

  /**
   * Đánh dấu thông báo đã đọc và gửi cập nhật WebSocket
   */
  public void markAsRead(Long id) {
    NotificationDTO notification = notificationMapper.findById(id);
    notificationMapper.markAsRead(id);

    // Gửi cập nhật trạng thái qua WebSocket
    messagingTemplate.convertAndSendToUser(
        notification.getReceiver(),
        "/queue/notifications",
        new WebSocketNotification("MARK_READ", id)
    );
  }

  /**
   * Đánh dấu tất cả thông báo đã đọc và gửi cập nhật WebSocket
   */
  public void markAllAsRead(String receiver) {
    notificationMapper.markAllAsRead(receiver);

    // Gửi cập nhật trạng thái qua WebSocket
    messagingTemplate.convertAndSendToUser(
        receiver,
        "/queue/notifications",
        new WebSocketNotification("MARK_ALL_READ", null)
    );
  }

  /**
   * Lấy số lượng thông báo chưa đọc
   */
  public int getUnreadCount(String receiver) {
    return notificationMapper.countUnread(receiver);
  }

  /**
   * Xóa thông báo và gửi cập nhật WebSocket
   */
  public void deleteNotification(Long id) {
    NotificationDTO notification = notificationMapper.findById(id);
    notificationMapper.delete(id);

    // Gửi cập nhật xóa thông báo qua WebSocket
    messagingTemplate.convertAndSendToUser(
        notification.getReceiver(),
        "/queue/notifications",
        new WebSocketNotification("DELETE_NOTIFICATION", id)
    );
  }

  /**
   * Lấy danh sách thông báo của một phòng ban
   */
  public List<NotificationDTO.Resp> getNotificationsByDepartment(Long departmentId) {
    List<NotificationDTO> notifications = notificationMapper.findByDepartment(departmentId);
    return notifications.stream()
        .map(this::convertToResp)
        .collect(Collectors.toList());
  }

  /**
   * Chuyển đổi NotificationDTO legacy sang NotificationDTO.Resp
   */
  private NotificationDTO.Resp convertToResp(NotificationDTO oldDto) {
    if (oldDto == null) return null;

    NotificationDTO.Resp resp = new NotificationDTO.Resp();
    resp.setId(oldDto.getId());
    resp.setTitle(oldDto.getTitle());
    resp.setContent(oldDto.getContent());
    resp.setSender(oldDto.getSender());
    resp.setSenderName(oldDto.getSenderName());
    resp.setReceiver(oldDto.getReceiver());
    resp.setCreatedAt(oldDto.getCreatedAt());
    resp.setRead(oldDto.isRead());
    resp.setTimeAgo(oldDto.getTimeAgo());
    resp.setType(oldDto.getType() != null ? oldDto.getType() : "info");

    return resp;
  }

  /**
   * Tạo thông báo cho nhiều người nhận với WebSocket
   */
  public void createBulkNotifications(String title, String content, String sender, List<String> receivers) {
    for (String receiver : receivers) {
      Notification notification = new Notification();
      notification.setTitle(title);
      notification.setContent(content);
      notification.setSender(sender);
      notification.setReceiver(receiver);
      notification.setCreatedAt(LocalDateTime.now());
      createNotification(notification);
    }
  }

  /**
   * Tạo thông báo từ BulkReq DTO
   */
  public int createBulkNotifications(NotificationDTO.BulkReq request) {
    if (request.getReceivers() == null || request.getReceivers().length == 0) {
      return 0;
    }

    for (String receiver : request.getReceivers()) {
      Notification notification = new Notification();
      notification.setTitle(request.getTitle());
      notification.setContent(request.getContent());
      notification.setSender(request.getSender());
      notification.setReceiver(receiver);
      notification.setCreatedAt(LocalDateTime.now());
      createNotification(notification);
    }

    return request.getReceivers().length;
  }

  /**
   * Tạo thông báo cho toàn bộ phòng ban
   */
  public void createDepartmentNotification(String title, String content, String sender, Long departmentId,
      List<String> usernames) {
    for (String username : usernames) {
      Notification notification = new Notification();
      notification.setTitle(title);
      notification.setContent(content);
      notification.setSender(sender);
      notification.setReceiver(username);
      notification.setCreatedAt(LocalDateTime.now());
      createNotification(notification);
    }
  }
}