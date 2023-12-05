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

public class ManageTransactions extends InventoryManager { 
    // list of all the transactions the store have 
    List <Transaction> TransactionsList= new ArrayList <> ();  
    
    // construct initial list of transactions we have
    public  ManageTransactions (List <Product> ProductList, List <Transaction> TransactionsList ){
    super(ProductList);
    for (Transaction trans:TransactionsList)
      {this.TransactionsList.add(trans);
       int pr_Id= trans.getProductId();
       for (Product product:ProductList)
      {
          int ProdId =product.getProductId(); 
          if (pr_Id == ProdId) {
              // id is valid so we update the quantity for this object
              product.updateQuantity(trans.getTransactionQuantity());
            }}
      }
    }
    
    // everytime there is new transaction, we call this method (we can add date as parameter
    public void NewTransaction (int id, int quantity ){
    boolean found = false; 
    for (Product product:this.ProductList)
      {
          int ProductId =product.getProductId(); 
          if (ProductId == id) {
              // id is valid so we update the quantity for this object
              product.updateQuantity(quantity);
              // id is valid so we create a new transaction (new transaction object), and we add it to the list
              Transaction transaction = new Transaction (this.ProductList,id, quantity);
              TransactionsList.add(transaction);
              found=true;}
      }
    if (found == false) { System.out.println("wrong id entered!");}
    }
    
}

