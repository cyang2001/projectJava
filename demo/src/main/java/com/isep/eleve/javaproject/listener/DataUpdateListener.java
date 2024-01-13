package com.isep.eleve.javaproject.listener;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.events.AssetAddedToPortfolioEvent;
import com.isep.eleve.javaproject.events.AssetChangedEvent;
import com.isep.eleve.javaproject.events.AssetCreatedEvent;
import com.isep.eleve.javaproject.events.AssetQuantityChangedEvent;
import com.isep.eleve.javaproject.events.CashCreatedEvent;
import com.isep.eleve.javaproject.events.CashEarnedEvent;
import com.isep.eleve.javaproject.events.CashSpentEvent;
import com.isep.eleve.javaproject.events.PortfolioChangedEvent;
import com.isep.eleve.javaproject.events.PortfolioCreatedEvent;
import com.isep.eleve.javaproject.events.UserChangedEvent;
import com.isep.eleve.javaproject.events.UserCreatedEvent;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.repository.UserRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.AssetSession;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
// ToDo change UI
@Component
public class DataUpdateListener {
    private static final Logger logger = LoggerFactory.getLogger(DataUpdateListener.class);
    @Autowired
    private UserSession userSession;

    @Autowired
    private PortfolioSession portfolioSession;

    @Autowired
    private AssetSession assetSession;

    @Autowired
    private CashSession cashSession;

    @Autowired
    private AssetsRepository assetsRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private UserRepository userRepository;
    @EventListener
    public void onPortfolioCreated(PortfolioCreatedEvent event) throws IOException {
        //userSession.getCurrentUser().addPortfolio(event.getPortfolio());
        //userRepository.save(userSession.getCurrentUser());
        logger.info("Portfolio created_event: " + event.getPortfolio().getPortfolioName());
        portfolioSession.setCurrentPortfolio(event.getPortfolio());
        //cashSession.setCash(portfolioSession.getCurrentPortfolio().getCash());
    }

    @EventListener
    public void onCashCreated(CashCreatedEvent event) {
        cashSession.setCash(event.getCash());
        logger.info("Cash created_event: " + event.getCash().getAssetName());
    }
    // ToDo
    @EventListener
    public void onPortfolioChanged(PortfolioChangedEvent event) {
        portfolioSession.setCurrentPortfolio(event.getPortfolio());
        if (event.getPortfolio().getCash() != null){
          cashSession.setCash(event.getPortfolio().getCash());
          logger.info("Portfolio changed_event: new portfolioSession" + event.getPortfolio().getPortfolioId());
          logger.info("Portfolio changed_event: new cashSession" + event.getPortfolio().getCash().getAssetId());
        } else {
          logger.info("Portfolio changed_event: null cashSession");
        }
        
    }
    @EventListener
    public void onAssetAddedToPortoflio(AssetAddedToPortfolioEvent event) {
        portfolioSession.getCurrentPortfolio().addAsset(event.getAsset());
        assetSession.setCurrentAsset(event.getAsset());
    }

    @EventListener
    public void onAssetCreated(AssetCreatedEvent event) throws IOException {
        portfolioSession.getCurrentPortfolio().addAsset(event.getAsset());
        assetSession.setCurrentAsset(event.getAsset());
        List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
        boolean flag = false;
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getPortfolioName().equals(portfolioSession.getCurrentPortfolio().getPortfolioName())) {
                userSession.getCurrentUser().updatePortfolio(portfolioSession.getCurrentPortfolio());
                flag = true;
                logger.info("Asset created_event portfolio update: " + event.getAsset().getAssetName());
            }
        }
        if (!flag) {
            userSession.getCurrentUser().addPortfolio(portfolioSession.getCurrentPortfolio());
            logger.info("Asset created_event: " + event.getAsset().getAssetName());
        }
        
        assetsRepository.save(event.getAsset());
        portfolioRepository.save(portfolioSession.getCurrentPortfolio());
        userRepository.save(userSession.getCurrentUser());
    }
    @EventListener
    public void onAssetChanged(AssetChangedEvent event) {
        assetSession.setCurrentAsset(event.getAsset());
        if (event.getAsset() != null){

          logger.info("Asset changed_event: new assetSession" + event.getAsset().getAssetName());
        }
    }
    @EventListener
    public void onAssetQuantityChanged(AssetQuantityChangedEvent event) throws IOException {
        /*if (event.getChangeType() == Constants.CHANGE_TYPE.ADD) {
            assetSession.getCurrentAsset().setQuantity(assetSession.getCurrentAsset().getQuantity() + event.getQuantity());
        } else {
            assetSession.getCurrentAsset().setQuantity(assetSession.getCurrentAsset().getQuantity() - event.getQuantity());
        }*/
        assetSession.setCurrentAsset(event.getAsset());
        logger.info("Asset quantity changed_event: new assetSession" + event.getAsset().getAssetName());
        portfolioSession.getCurrentPortfolio().updateAsset(assetSession.getCurrentAsset());
        logger.info("Asset quantity changed_event: update portfolioSession" + portfolioSession.getCurrentPortfolio().getPortfolioName());
        userSession.getCurrentUser().updatePortfolio(portfolioSession.getCurrentPortfolio());
        logger.info("Asset quantity changed_event: portfolio in usersession" + userSession.getCurrentUser().getPortfolios());
        logger.info("Asset quantity changed_event: update userSession" + userSession.getCurrentUser().getUserName());
        assetsRepository.save(assetSession.getCurrentAsset());
        portfolioRepository.save(portfolioSession.getCurrentPortfolio());
        userRepository.save(userSession.getCurrentUser());
    }


    @EventListener
    public void onUserCreated(UserCreatedEvent event) throws IOException {
        userSession.setCurrentUser(event.getUser());
        userRepository.save(userSession.getCurrentUser());
        
    }
    @EventListener
    public void onUserChanged(UserChangedEvent event) {
        userSession.setCurrentUser(event.getUser());
        
    }

    @EventListener
    public void onCashSpent(CashSpentEvent event) throws IOException {
        cashSession.getCash().setPrice(cashSession.getCash().getPrice().subtract(event.getPrice()));
        assetsRepository.save(cashSession.getCash());
        
    }
    @EventListener
    public void onCashEarned(CashEarnedEvent event) throws IOException {
        cashSession.getCash().setPrice(cashSession.getCash().getPrice().add(event.getPrice()));
        assetsRepository.save(cashSession.getCash());
    }
}
