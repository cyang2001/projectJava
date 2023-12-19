package com.isep.eleve.javaproject.service.userServices;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {
  @InjectMocks
  private AuthenticationService authenticationService;
  @Mock
  private SecurityService securityService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private User user;
  @Mock
  private User user2;
  @Test
  public void testAuthenticate() throws IOException {
    // Arrange
    String username1 = "My Username1";
    String password1 = "My Password1";
    String passwordHashed1 = "My Encrypted Password1";
    String username2 = "My Username2";
    String password2 = "My Password2";
    String passwordHashed2 = "My Encrypted Password2";
    List<User> users = new ArrayList<User>();
    users.add(user);
    users.add(user2);
    when(userRepository.findAll()).thenReturn(users);
    when(user.getUserName()).thenReturn(username1);
    when(user.getPasswordHash()).thenReturn(passwordHashed1);
    when(user2.getUserName()).thenReturn(username2);
    when(user2.getPasswordHash()).thenReturn(passwordHashed2);
    when(securityService.encryptData(password1, Constants.ENCRYPT_TYPE.MD5)).thenReturn("My Encrypted Password1");
    when(securityService.encryptData(password2, Constants.ENCRYPT_TYPE.MD5)).thenReturn("My Encrypted Password2");
    // Act
    authenticationService.authenticate(username2, password2);
    authenticationService.authenticate(username1, password1);
    // Assert
  }

}
