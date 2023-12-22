package com.isep.eleve.javaproject.service.userServices;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.repository.UserRepository;
import com.isep.eleve.javaproject.service.userServices.RegistrationService.RegistrationResult;
import com.isep.eleve.javaproject.Tools.*;
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
  @InjectMocks
  private RegistrationService registrationService;
  @Mock
  private SecurityService securityService;
  @Mock
  private UserRepository userRepository;
  @Mock 
  private RegistrationResult registrationResult;
  @Mock 
  private RegistrationResult registrationResult2;
  @Mock
  private User user;
  @Mock
  private User user2;
  @Test
  public void testRegistrationServiceTest() throws IOException {
    // Arrange
    String username1 = "My Username1";
    String password1 = "My Password1";
    String username2 = "My Username2";
    String password2 = "My Password2";
    List<User> users = new ArrayList<User>();
    when(userRepository.findAll()).thenReturn(users);
    when(securityService.encryptData(password1, Constants.ENCRYPT_TYPE.MD5)).thenReturn("My Encrypted Password1");

    when(registrationResult.isSuccess()).thenReturn(true);
    //when(registrationResult.getFailureType()).thenReturn(null);
    //when(registrationResult.getUser()).thenReturn(user);

    when(registrationResult2.isSuccess()).thenReturn(false);
    when(registrationResult2.getFailureType()).thenReturn(Constants.REGISTRATION_FAILIURE_TYPE.PASSWORD_NOT_SAME);
    //when(registrationResult2.getUser()).thenReturn(null);
    // Act
    registrationService.register(username1, password1, password1);
    registrationService.register(username2, password2, password1);
    // Assert
    assertEquals(registrationService.register(username1, password1, password1).isSuccess(), registrationResult.isSuccess());
    
    assertEquals(registrationService.register(username2, password2, password1).isSuccess(), registrationResult2.isSuccess());
    assertEquals(registrationService.register(username2, password1, password2).getFailureType(), registrationResult2.getFailureType());
    // assertEquals(registrationService.authenticate(username1, password1), user);
  }
}
