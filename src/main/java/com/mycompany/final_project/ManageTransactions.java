/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project;

/**
 *
 * @author Manel Saddouki
 */
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class ManageTransactions extends InventoryManager {
  // list of all the transactions the store have
  List<Transaction> TransactionsList = new ArrayList<>();

  public List<Transaction> getTransactionsList() {
    return this.TransactionsList;
  }

  // construct initial list of transactions we have
  public ManageTransactions(List<Product> ProductList, List<Transaction> TransactionsList) {
    super(ProductList);
    for (Transaction trans : TransactionsList) {
      this.TransactionsList.add(trans);
      int pr_Id = trans.getProductId();
      for (Product product : ProductList) {
        int ProdId = product.getProductId();
        if (pr_Id == ProdId) {
          // id is valid so we update the quantity for this object
          product.updateQuantity(trans.getTransactionQuantity());
        }
      }
    }
  }

  // add docs files to the list:
  public void InitialTransaction (Date date, int id, int quantity) {
    for (Product product : this.ProductList) {
      int ProductId = product.getProductId();
      if (ProductId == id) {
        // id is valid so we create a new transaction (new transaction object), and we
        // add it to the list
        Transaction transaction = new Transaction(this.ProductList, date, id, quantity);
        TransactionsList.add(transaction);
    }
  }

  // everytime there is new transaction, we call this method (we can add date as
  // parameter
  public void NewTransaction(int id, int quantity) {
    boolean found = false;
    for (Product product : this.ProductList) {
      int ProductId = product.getProductId();
      if (ProductId == id) {
        // id is valid so we update the quantity for this object
        product.updateQuantity(quantity);

        // create today date as object
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        java.util.Date dateObject = java.sql.Date.valueOf(currentDate);

        // id is valid so we create a new transaction (new transaction object), and we
        // add it to the list
        Transaction transaction = new Transaction(this.ProductList, dateObject, id, quantity);
        TransactionsList.add(transaction);
        found = true;
      }
    }
    if (found == false) {
      System.out.println("wrong id entered!");
    }
  }

}
