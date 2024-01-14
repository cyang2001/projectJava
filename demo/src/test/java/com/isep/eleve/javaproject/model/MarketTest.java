package com.isep.eleve.javaproject.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.isep.eleve.javaproject.repository.MarketRepository;
import com.isep.eleve.javaproject.repository.MarketTransactionRepository;
import com.isep.eleve.javaproject.service.marketService.MarketService;
import com.isep.eleve.javaproject.session.MarketSession;

@RunWith(MockitoJUnitRunner.class)
public class MarketTest {
  @InjectMocks
  private MarketService marketService;
  @Mock
  private MarketTransactionRepository marketTransactionRepository;
  @Mock
  MarketSession marketSession;
  @Test
    public void testAddTransaction() throws IOException {
        Market market = new Market("Test Market");
        
        MarketTransaction transaction = new MarketTransaction(new BigDecimal(1), 1, 1, "testAssetName", "testMarketName", 1, 1);
        doNothing().when(marketTransactionRepository).save(any(MarketTransaction.class));
        marketService.addNewMarketTransaction(transaction);
        assertTrue(market.getMarketTransactionIds().contains(transaction.getMarketTransactionId()));
        verify(marketTransactionRepository).save(any(MarketTransaction.class));

}
}
