package com.isep.eleve.javaproject.model.assets.Cypto;

public class Transaction{
  private Wallet originWallet;
  public Wallet getOriginWallet(){
    return originWallet;
  }
  private Wallet destinationWallet;
  public Wallet getDestinationWallet(){
    return destinationWallet;
  }
  private int isepCoin;
  public int getIsepCoin(){
    return isepCoin;
  }
  private boolean payed = false;
  public boolean getPayed(){
    return payed;
  }
  public void setPayed(boolean payed){
    this.payed = payed;
  }
  public Transaction(Wallet originWallet, Wallet destinationWallet, int isepCoin){
    this.originWallet = originWallet;
    this.destinationWallet = destinationWallet;
    this.isepCoin = isepCoin;
  }
  public boolean pay(){
    if (this.getOriginWallet().getIsepCoin() < isepCoin){
      System.out.println("You don't have enough ISEP Coin!");
      return false;
    }
    if(this.getOriginWallet().equals(this.getDestinationWallet())){
      System.out.println("You can't pay yourself!");
      return false;
    }
    this.getOriginWallet().setIsepCoin(this.getOriginWallet().getIsepCoin() - isepCoin);
    this.getDestinationWallet().setIsepCoin(this.getDestinationWallet().getIsepCoin() + isepCoin);
    this.setPayed(true);
    System.out.println("Transaction success!");
    return true;
  }
  @Override
  public String toString(){
    return "Transaction{" +
                "originWallet=" + originWallet.getToken() +
                ", destinationWallet=" + destinationWallet.getToken() +
                ", isepCoin=" + isepCoin +
                ", payed=" + payed +
                '}';
  }
}
