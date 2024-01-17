package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

public class BuyAssetEvent extends ApplicationEvent {
    public BuyAssetEvent(Object object, Object object2) {
        super(object, null);
    }
}
