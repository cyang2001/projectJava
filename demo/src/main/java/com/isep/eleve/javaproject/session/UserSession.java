package com.isep.eleve.javaproject.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.User;

@Component
@Scope("singleton")
public class UserSession {
  private User currentUser;
  
  public void setCurrentUser(User currentUser){
    this.currentUser = currentUser;
  }

  public User getCurrentUser(){
    return this.currentUser;
  }
}
