package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.Asset;

public class AssetClonedEvent extends ApplicationEvent{
  private Asset asset;

    public AssetClonedEvent(Object source, Asset asset) {
        super(source);
        this.asset = asset;
    }

    public Asset getAsset() {
        return asset;
    }
  
}