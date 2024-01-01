package com.isep.eleve.javaproject.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.Asset;

@Component
@Scope("singleton")
public class AssetSession {
  private Asset currentAsset;
  public void setCurrentAsset(Asset currentAsset){
    this.currentAsset = currentAsset;
  }
  public Asset getCurrentAsset(){
    return this.currentAsset;
  }
}
