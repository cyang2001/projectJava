package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.Transaction;
@Repository
public class TransactionRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseTransaction.json";

  private FileOperation fileOperation;
  
  @Autowired
  public TransactionRepository(FileOperation fileOperation) {
    this.fileOperation = fileOperation;
  }
  

  /**
   * Retrieves all transactions from the repository.
   *
   * @return a list of Transaction objects representing all transactions.
   * @throws IOException if an I/O error occurs while reading the transactions from the file.
   */
  public List<Transaction> findAll() throws IOException {
    TypeReference<List<Transaction>> typeReference = new TypeReference<List<Transaction>>() {};
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, typeReference);
  }


  /**
   * Saves a transaction to the repository.
   *
   * @param transaction The transaction to be saved.
   * @throws IOException If an I/O error occurs while saving the transaction.
   */
  public void save(Transaction transaction) throws IOException {
    List<Transaction> transactions = findAll();
    for (Transaction t : transactions) {
      if (t.getTransactionId() == transaction.getTransactionId()) {
        transactions.remove(t);
        break;
      }
    }
    transactions.add(transaction);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, transactions);
  }

  /**
   * Finds a transaction by its ID.
   *
   * @param transactionId The ID of the transaction to be found.
   * @return The transaction with the specified ID, or null if no transaction with the specified ID exists.
   * @throws IOException If an I/O error occurs while reading the transactions from the file.
   */
  public Transaction findByPortfolioId(int transactionId) throws IOException {
    List<Transaction> transactions = findAll();
    return transactions.stream().filter(t -> t.getTransactionId() == transactionId).findFirst().orElse(null);
  }

  public List<Transaction> findByOwnerId(int ownerId) throws IOException {
    List<Transaction> transactions = findAll();
    return transactions.stream().filter(t -> t.getOwnerId() == ownerId).collect(Collectors.toList());
  }
  
  public void delete(Transaction transaction) throws IOException {
    List<Transaction> transactions = findAll();
    transactions.remove(transaction);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, transactions);
  }
}
