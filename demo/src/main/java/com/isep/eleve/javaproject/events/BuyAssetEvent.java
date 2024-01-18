package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

public class BuyAssetEvent extends ApplicationEvent {
    private boolean isSuccessful;
    private int transactionId;
    public BuyAssetEvent(Object object, boolean isSuccessful, int transactionId) {
        super(object);
        this.isSuccessful = isSuccessful;
        this.transactionId = transactionId;
    }
    public boolean getIsSuccessful() {
        return isSuccessful;
    }
    public int getTransactionId() {
        return transactionId;
    }
}
