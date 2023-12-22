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
  private int coin;
  public int getCoin(){
    return coin;
  }
  private boolean payed = false;
  public boolean getPayed(){
    return payed;
  }
  public void setPayed(boolean payed){
    this.payed = payed;
  }
  public Transaction(Wallet originWallet, Wallet destinationWallet, int coin){
    this.originWallet = originWallet;
    this.destinationWallet = destinationWallet;
    this.coin = coin;
  }
  public boolean pay(){
    if (this.getOriginWallet().getCoin() < coin){
      System.out.println("You don't have enough coin!");
      return false;
    }
    if(this.getOriginWallet().equals(this.getDestinationWallet())){
      System.out.println("You can't pay yourself!");
      return false;
    }
    this.getOriginWallet().setCoin(this.getOriginWallet().getCoin() - coin);
    this.getDestinationWallet().setCoin(this.getDestinationWallet().getCoin() + coin);
    this.setPayed(true);
    System.out.println("Transaction success!");
    return true;
  }
  @Override
  public String toString(){
    return "Transaction{" +
                "originWallet=" + originWallet.getToken() +
                ", destinationWallet=" + destinationWallet.getToken() +
                ", isepCoin=" + coin +
                ", payed=" + payed +
                '}';
  }
}
