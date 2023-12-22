package com.isep.eleve.javaproject.service.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }
  
  
}
