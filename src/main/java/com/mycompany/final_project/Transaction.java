/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project;

import java.util.List;

/**
 *
 * @author Manel Saddouki
 */
public class Transaction extends InventoryManager{
    // each transaction has the following attributes (we can add date and auto-generated id for each transaction
    final private int ProductId;
    private String ProductName;
    private String ProductCategory;
    final private int TransactionQuantity; 
    private double UnitPrice;
    private double UnitProfit; 
    private double TotalRevenue;
    private double TotalProfit;
    
     //Constructor
   public Transaction ( List <Product> ProductList, int ProductId, int quantity)
   {
       super(ProductList);
       this.ProductId = ProductId;
       this.TransactionQuantity = quantity; 
       for (Product product:ProductList)
      {
          int id = product.getProductId(); 
          if (ProductId == id) {
              this.ProductCategory=product.getProductCategory();
              this.ProductName=product.getProductName();
              this.UnitPrice=product.getUnitPrice();
              this.UnitProfit=product.getUnitProfit();
              this.TotalProfit= this.TransactionQuantity * this.UnitProfit ;
              this.TotalRevenue= this.TransactionQuantity * this.UnitPrice ;}
      }
       
    }
   
   // getters
   public int getProductId() {
        return this.ProductId; 
        }
   public String getProductName() {
        return this.ProductName; 
        }
   public String getProductCategory() {
        return this.ProductCategory; 
        }
   public int getTransactionQuantity() {
        return this.TransactionQuantity; 
        }
   public double getUnitPrice() {
        return this.UnitPrice; 
        }
   public double getUnitProfit() {
        return this.UnitProfit; 
        }
   public double getTotalRevenue() {
        return this.TotalRevenue; 
        }
   public double getTotalProfit() {
        return this.TotalProfit; 
        }
}
