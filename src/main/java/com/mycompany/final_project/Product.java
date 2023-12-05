/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project;

/**
 *
 * @author Manel Saddouki
 */
public class Product {
    private int ProductId;
    private String ProductName;
    private String ProductCategory;
    private int Quantity; 
    private double UnitPrice;
    private double UnitProfit; 
    
    //Constructor
   public Product (int id, String name, String category, int quantity, double unitprice, double unitprofit)
   {
       this.ProductId = id; 
       this.ProductName = name; 
       this.ProductCategory = category; 
       this.Quantity = quantity;
       this.UnitPrice = unitprice; 
       this.UnitProfit = unitprofit;
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
   public int getQuantity() {
        return this.Quantity; 
        }
   public double getUnitPrice() {
        return this.UnitPrice; 
        }
   public double getUnitProfit() {
        return this.UnitProfit; 
        }
   
   
   // setters
   public void setProductId( int id) {
        this.ProductId = id ; 
        }
   public void setProductName(String name) {
        this.ProductName= name; 
        }
   public void setProductCategory(String category) {
        this.ProductCategory= category; 
        }
   public void setQuantity(int quantity) {
        this.Quantity = quantity; 
        }
   public void setUnitPrice(float unitprice) {
        this.UnitPrice= unitprice; 
        }
   public void setUnitProfit(float unitprofit) {
        this.UnitProfit = unitprofit; 
        }
 
// decrease the quantity after each transaction   
public void updateQuantity(int updateNumber) {
        this.Quantity -= updateNumber; 
        }

// increase the quantity   
public void addQuantity(int updateNumber) {
        this.Quantity += updateNumber; 
        }
}
