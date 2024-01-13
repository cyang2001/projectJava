package com.isep.eleve.javaproject.service.portfolioService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;


import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.service.portfolioServices.PortfolioService;
import com.isep.eleve.javaproject.session.UserSession;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioServiceTest {

    @InjectMocks
    private PortfolioService portfolioService;

    @Mock
    private PortfolioRepository portfolioRepository;
    @Mock
    private UserSession userSession;
    @Mock
    private User user;
    @Mock
    private ApplicationEventPublisher eventApplication;
@Test 
public void testCreatePortfolio() throws IOException {
    // Arrange
    String portfolioName = "My Portfolio";
    int ownerId = 1;
    userSession.setCurrentUser(user);
    User mockUser = mock(User.class);
    when(mockUser.getUserId()).thenReturn(ownerId);
    when(userSession.getCurrentUser()).thenReturn(mockUser);
    when(user.getUserId()).thenReturn(2);
    // Act
    portfolioService.createPortfolio(portfolioName);

    // Assert
    // Verify that the repository's save method was called with a portfolio having the expected properties
    verify(portfolioRepository).save(argThat(portfolio -> 
        portfolio.getPortfolioName().equals(portfolioName) && portfolio.getOwnerId() == ownerId
    ));
}


    @Test(expected = RuntimeException.class)
    public void testCreatePortfolioThrowsException() throws IOException {
        // Arrange
        doThrow(new RuntimeException()).when(portfolioRepository).save(any(Portfolio.class));

        // Act
        portfolioService.createPortfolio("My Portfolio");

        // Assert
        // The test will fail if the exception is not thrown

    }
}
