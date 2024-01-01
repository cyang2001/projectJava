package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.User;
public class UserChangedEvent extends ApplicationEvent{
  private User user;

    public UserChangedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
  
}