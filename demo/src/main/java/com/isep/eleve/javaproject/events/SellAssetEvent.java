package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

public class SellAssetEvent extends ApplicationEvent{
  public SellAssetEvent(Object source) {
    super(source);
  }
}
