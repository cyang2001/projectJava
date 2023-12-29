package com.isep.eleve.javaproject.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.events.AssetCreatedEvent;
import com.isep.eleve.javaproject.events.PortfolioCreatedEvent;
import com.isep.eleve.javaproject.events.UserCreatedEvent;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;

@Component
public class DataUpdateListener {

    @Autowired
    private UserSession userSession;

    @Autowired
    private PortfolioSession portfolioSession;

    @EventListener
    public void onPortfolioCreated(PortfolioCreatedEvent event) {
        userSession.getCurrentUser().addPortfolio(event.getPortfolio());
        portfolioSession.setCurrentPortfolio(event.getPortfolio());
    }

    @EventListener
    public void onAssetCreated(AssetCreatedEvent event) {
        portfolioSession.getCurrentPortfolio().addAsset(event.getAsset());
    }
    @EventListener
    public void onUserCreated(UserCreatedEvent event) {
        userSession.setCurrentUser(event.getUser());
    }
}
