package com.isep.eleve.javaproject.service.portfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.service.portfolioServices.PortfolioService;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        portfolioService = new PortfolioService(portfolioRepository);
    }

    @Test
    void testCreatePortfolio() throws IOException {
        // Arrange
        String portfolioName = "My Portfolio";
        int ownerId = 1;
        Portfolio newPortfolio = new Portfolio(portfolioName, ownerId, new ArrayList<>(), new ArrayList<>());

        // Act
        //when(portfolioRepository.save(newPortfolio)).thenReturn(newPortfolio);
        Portfolio createdPortfolio = portfolioService.createPortfolio(portfolioName, ownerId);

        // Assert
        assertEquals(newPortfolio, createdPortfolio);
        verify(portfolioRepository, times(1)).save(newPortfolio);
    }
}