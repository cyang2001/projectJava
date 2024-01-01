package com.isep.eleve.javaproject.listener;

import java.io.IOException;
import java.math.BigDecimal;

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
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.session.AssetSession;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
// ToDo change UI
@Component
public class DataUpdateListener {

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

    @EventListener
    public void onPortfolioCreated(PortfolioCreatedEvent event) {
        userSession.getCurrentUser().addPortfolio(event.getPortfolio());
        portfolioSession.setCurrentPortfolio(event.getPortfolio());
    }
    @EventListener
    public void onCashCreated(CashCreatedEvent event) {
        cashSession.setCash(event.getCash());
    }
    // ToDo
    @EventListener
    public void onPortfolioChanged(PortfolioChangedEvent event) {
        portfolioSession.setCurrentPortfolio(event.getPortfolio());
    }
    @EventListener
    public void onAssetAddedToPortoflio(AssetAddedToPortfolioEvent event) {
        portfolioSession.getCurrentPortfolio().addAsset(event.getAsset());
        assetSession.setCurrentAsset(event.getAsset());
    }



    @EventListener
    public void onAssetCreated(AssetCreatedEvent event) {
        portfolioSession.getCurrentPortfolio().addAsset(event.getAsset());
        assetSession.setCurrentAsset(event.getAsset());
    }
    @EventListener
    public void onAssetChanged(AssetChangedEvent event) {
        assetSession.setCurrentAsset(event.getAsset());
    }
    @EventListener
    public void onAssetQuantityChanged(AssetQuantityChangedEvent event) throws IOException {
        if (event.getChangeType() == Constants.CHANGE_TYPE.ADD) {
            assetSession.getCurrentAsset().setQuantity(assetSession.getCurrentAsset().getQuantity() + event.getQuantity());
        } else {
            assetSession.getCurrentAsset().setQuantity(assetSession.getCurrentAsset().getQuantity() - event.getQuantity());
        }
        assetsRepository.save(assetSession.getCurrentAsset());
    }


    @EventListener
    public void onUserCreated(UserCreatedEvent event) {
        userSession.setCurrentUser(event.getUser());
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
