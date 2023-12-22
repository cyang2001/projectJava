package com.isep.eleve.javaproject.model.assets.Cypto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockchainUtility {
  public static void printLastNBlocks(int n) {
    List<Block> blocks = Block.blockChain;
    for (int i = Math.max(blocks.size() - n, 0); i < blocks.size(); i++) {
        System.out.println("Block " + i + ": \n" + blocks.get(i).toString());
    }
}
public static Map<Integer, Integer[]> getUserTransactionStats() {
  Map<Integer, Integer[]> userStats = new HashMap<>();
  for (Block block : Block.blockChain) {
      for (Transaction transaction : block.getTransactions()) {
          updateStats(userStats, transaction.getOriginWallet(), transaction.getIsepCoin());
          updateStats(userStats, transaction.getDestinationWallet(), transaction.getIsepCoin());
      }
  }
  return userStats;
}

private static void updateStats(Map<Integer, Integer[]> stats, Wallet wallet, int amount) {
  Integer[] data = stats.getOrDefault(wallet.getToken(), new Integer[]{0, 0});
  data[0]++; 
  data[1] += amount; 
  stats.put(wallet.getToken(), data);
}
public static int[][] generateAdjacencyMatrix() {
int size = Configuration.STUDENTS.length;
int[][] matrix = new int[size][size];

for (Block block : Block.blockChain) {
    for (Transaction transaction : block.getTransactions()) {
        int origin = transaction.getOriginWallet().getToken();
        int destination = transaction.getDestinationWallet().getToken();
        matrix[origin][destination]++; 
    }
}
return matrix;
}
}
