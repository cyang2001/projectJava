package com.isep.eleve.javaproject.events;

import java.time.Clock;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.User;

public class UserCreatedEvent extends ApplicationEvent {
  private User user;
  public UserCreatedEvent(Object source, User user) {
    super(source);
    this.user = user;
  }
  public User getUser() {
    return user;
  }
  
}
