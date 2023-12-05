/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.final_project;

/**
 *
 * @author Manel Saddouki
 */

import java.util.*;

public class Final_Project {

    public static void main(String[] args) {
        // initialize the products we have 
        Product product1 = new Product (201, "Cozy Hoodie", "Hoodie", 40, 43.99,12.99 );
        Product product2 = new Product (202, "Comfy Hoodie", "Hoodie", 35, 43.99,12.99 );
        Product product3 = new Product (301, "Canvas Tote Bag", "Tote Bag", 25, 19.99,7.99 );
        Product product4 = new Product (301, "Tote Bag Stripes", "Tote Bag", 30, 19.99,7.99 );
        Product product5 = new Product (401, "Cat Keychain", "Keychain", 20, 4.99,2.99 );
        Product product6 = new Product (501, "Cozy Socks", "Socks", 30, 19.99,7.99 );
        Product product7 = new Product (601, "Palestine Hoodie", "Palestine", 40, 43.99,12.99 );
        Product product8 = new Product (701, "Gaza Tote Bag", "Palestine", 40, 19.99,7.99 );
        Product product9 = new Product (701, "Keffiyah Tote Bag", "Palestine", 40, 19.99,7.99 );
    
        // initialize Inventory list
        List <Product> myproducts = List.of(product1, product3, product4, product5, product6, product7, product8, product9);
        InventoryManager MystoreInventory = new InventoryManager(myproducts);
        
        // initialize some transactions
        Transaction trans1 = new Transaction (myproducts, 401, 5);
        Transaction trans2 = new Transaction (myproducts, 201, 7);
        
        // using ManageTransactions constructor, we will initialize the list of transactions we have.
        List <Transaction> mytransactions = List.of(trans1, trans2);
        ManageTransactions MyTransactionsManager = new ManageTransactions(myproducts, mytransactions);
        
        /* now my store has: 
        MystoreInventory (list of products I have in as inventory)
        MyTransactionsManager (list of transactions I have) */
        
       // now I can add any transaction:
       MyTransactionsManager.NewTransaction(501,1);
       
       
       // test update of product quantity after the transaction 
       System.out.println(product5.getQuantity()); // product 401, it should be 20-5=15
       System.out.println(product1.getQuantity()); // product 201, it should be 40-7=33
       System.out.println(product6.getQuantity()); // product 501, it should be 30-1=29
       
       // test different methods for inventory manager class: 
       System.out.println(MystoreInventory.SpecificCategoryExpectedProfit("Hoodie"));
       
       for (String element: MystoreInventory.ProductList())
       {System.out.print (element +" / ");}
       System.out.println();
       
       MystoreInventory.AddProduct(product2);
       for (String element: MystoreInventory.ProductList())
       {System.out.print(element +" / ");} // comfy hoodie should be added to the list 
       System.out.println();
       
       for (String element: MystoreInventory.CategoriesList())
       {System.out.print(element +"/ ");}
       System.out.println();
       
       for (String element: MystoreInventory.SpecificCategoryProductList("Palestine"))
       {System.out.print(element +" / ");} // comfy hoodie should be added to the list 
       System.out.println();
       
       // test methods in product class: 
       System.out.println (product1.getProductCategory()+ " " + product1.getProductName() + " " +product1.getUnitPrice()+ " " + product1.getQuantity());
       
       product1.addQuantity(5); 
       System.out.println ( product1.getQuantity());
        // here we will create an excel sheet that has all the inventory we have 
    }
}
