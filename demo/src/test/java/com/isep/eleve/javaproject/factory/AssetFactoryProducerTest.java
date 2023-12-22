package com.isep.eleve.javaproject.factory;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;

@RunWith(MockitoJUnitRunner.class)
public class AssetFactoryProducerTest {
  @InjectMocks
  private AssetFactoryProducer assetFactoryProducer;

  @Mock
  private CashFactory mockCashFactory;

  @Mock
  private FixedDepositFactory mockFixedDepositFactory;

  @Test
  public void testGetFactory() {
    // Arrange
    Map<ASSET_TYPE, AssetFactory> factories = new HashMap<>();
    factories.put(ASSET_TYPE.CASH, mockCashFactory);
    factories.put(ASSET_TYPE.FIXED_DEPOSIT, mockFixedDepositFactory);

    assetFactoryProducer = new AssetFactoryProducer(factories); 

    // Act
    AssetFactory cashFactory = assetFactoryProducer.getFactory(ASSET_TYPE.CASH);
    AssetFactory fixedDepositFactory = assetFactoryProducer.getFactory(ASSET_TYPE.FIXED_DEPOSIT);

    // Assert
    Assert.assertEquals(mockCashFactory.getClass(), cashFactory.getClass());
    Assert.assertEquals(mockFixedDepositFactory.getClass(), fixedDepositFactory.getClass());
  }
}
