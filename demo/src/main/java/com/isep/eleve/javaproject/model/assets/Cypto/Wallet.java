package com.isep.eleve.javaproject.model.assets.Cypto;

import com.isep.eleve.javaproject.Tools.*;

public class Wallet {
  private String owner;
  public String getOwner(){
    return owner;
  }
  public void setOwner(String owner){
    this.owner = owner;
  }
  private int token;
  public int getToken(){
    return token;
  }
  public void setToken(int token){
    this.token = token;
  }
  private int coin;
  public int getCoin(){
    return coin;
  }
  public void setCoin(int coin){
    this.coin = coin;
  }
  public Wallet(String owner){
    this.token = UUIDUtils.getUUIDInOrderId();
    this.owner = owner;
  }
  @Override
  public boolean equals(Object obj){
    return this.token == ((Wallet) obj).getToken();
  }
}
