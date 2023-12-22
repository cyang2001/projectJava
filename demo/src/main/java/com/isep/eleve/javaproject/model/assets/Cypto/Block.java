package com.isep.eleve.javaproject.model.assets.Cypto;

import java.util.LinkedList;
import java.util.Queue;
public class Block {
  private Queue<Transaction> transactions;
  public static LinkedList<Block> blockChain = new LinkedList<>();
  public Block(){
    this.transactions = new LinkedList<>();
  }
  public Queue<Transaction> getTransactions(){
    return transactions;
  }
  public Block add(Transaction transaction){
    this.transactions.add(transaction);
    if (transactions.size() >= 10){
      for(Transaction t:transactions){
        if (!t.getPayed()){
          t.pay();
        }
      }
      blockChain.add(new Block());
    }
    return this;
  }
  @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions) {
            sb.append(transaction.toString()).append("\n");
        }
        return sb.toString();
    }
  
}
