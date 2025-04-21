package com.example.hrms.biz.commoncode.notification.model;

public class WebSocketNotification {
  private String type; // Loại thông báo: NEW_NOTIFICATION, MARK_READ, DELETE, v.v.
  private Object data; // Dữ liệu thông báo

  public WebSocketNotification() {
  }

  public WebSocketNotification(String type, Object data) {
    this.type = type;
    this.data = data;
  }

  // Getters và Setters
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}