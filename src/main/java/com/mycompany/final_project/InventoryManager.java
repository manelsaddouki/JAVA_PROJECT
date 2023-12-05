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

public class InventoryManager {
    List <Product> ProductList= new ArrayList <> ();  
    
    // constructor 
    public  InventoryManager (List <Product> products){
      for (Product prod:products)
      {this.ProductList.add(prod);}
    }
    
    
    // add a product to the list
    public void AddProduct (Product prod){
      this.ProductList.add(prod);
    }
    
    // add a product to the list
    public void DeleteProduct (Product prod){
      this.ProductList.remove(prod);
    }
    
    // get list of products (product names) we have
    public List<String> ProductList (){
      List <String> ProductNameList= new ArrayList <> (); 
      for (Product product:this.ProductList)
      {
          String name =product.getProductName(); 
          if (ProductNameList.contains(name)== false) {ProductNameList.add(name);}
      }
      return ProductNameList;
    }
    
    // get list of categories we have
    public List<String> CategoriesList (){
      List <String> CategoriesList= new ArrayList <> (); 
      for (Product product:this.ProductList)
      {
          String cat =product.getProductCategory(); 
          if (CategoriesList.contains(cat)== false) {CategoriesList.add(cat);}
      }
      return CategoriesList;
    }
    
    
    // get list of specific category
    public List<String> SpecificCategoryProductList (String category){
      List <String> SpecificCategoryProductList= new ArrayList <> (); 
      for (Product product:this.ProductList)
      {
          String cat =product.getProductCategory();
          String name =product.getProductName();
          if ((cat.equals(category)) && (SpecificCategoryProductList.contains(name)== false)) {SpecificCategoryProductList.add(name);}
      }
      return SpecificCategoryProductList;
    }

    // get total expected revenues from inventory 
    public double ExpectedRevenues (){ 
      double ExpectedRevenues=0;
      for (Product product:this.ProductList)
      {
          ExpectedRevenues += (product.getQuantity()*product.getUnitPrice());
      }
      return ExpectedRevenues;
    }
    
    // get total expected profit from inventory 
    public double ExpectedProfit (){ 
      double ExpectedProfit=0;
      for (Product product:this.ProductList)
      {
          ExpectedProfit += (product.getQuantity()* product.getUnitProfit());
      }
      return ExpectedProfit;
    }
    
    // get total expected profit from a specific category
    public double SpecificCategoryExpectedProfit (String category){
      double ExpectedProfit=0;
      List <Product> SpecificCategoryExpectedProfit= new ArrayList <> (); 
      for (Product product:this.ProductList)
      {
          String cat =product.getProductCategory(); 
          if (cat.equals(category)) { SpecificCategoryExpectedProfit.add(product);}
      }
      for (Product product:SpecificCategoryExpectedProfit)
      {
          ExpectedProfit += (product.getQuantity()* product.getUnitProfit());
      }
      return ExpectedProfit;
    }
}
