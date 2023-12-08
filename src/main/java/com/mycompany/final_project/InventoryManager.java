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
  List<Product> ProductList = new ArrayList<>();

  // constructor
  public InventoryManager(List<Product> products) {
    for (Product prod : products) {
      this.ProductList.add(prod);
    }
  }

  public List<Product> getList() {
    return this.ProductList;
  }

  // Inventory Manipulation
  // add a product to the list
  public void AddProduct(Product prod) {
    this.ProductList.add(prod);
  }

  // delete a product from the list
  public void DeleteProduct(int id) {
    Iterator<Product> itr = this.ProductList.iterator();
    while (itr.hasNext()) {
      Product prod = itr.next();
      if (prod.getProductId() == id) {
        itr.remove();
      }
    }
  }

  // information about the products we have
  // get list of products (product ids) we have
  public List<Integer> ProductIDsList() {
    List<Integer> ProductIDList = new ArrayList<>();
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (ProductIDList.contains(prodid) == false) {
        ProductIDList.add(prodid);
      }
    }
    return ProductIDList;
  }

  // get list of products (product names) we have
  public List<String> ProductNamesList() {
    List<String> ProductNameList = new ArrayList<>();
    for (Product product : this.ProductList) {
      String name = product.getProductName();
      if (ProductNameList.contains(name) == false) {
        ProductNameList.add(name);
      }
    }
    return ProductNameList;
  }

  // get list of categories we have
  public List<String> CategoriesList() {
    List<String> CategoriesList = new ArrayList<>();
    for (Product product : this.ProductList) {
      String cat = product.getProductCategory();
      if (CategoriesList.contains(cat) == false) {
        CategoriesList.add(cat);
      }
    }
    return CategoriesList;
  }

  // get list of specific category
  public List<String> SpecificCategoryProductList(String category) {
    List<String> SpecificCategoryProductList = new ArrayList<>();
    for (Product product : this.ProductList) {
      String cat = product.getProductCategory();
      // System.out.println(cat);
      String name = product.getProductName();
      if ((cat.equals("Hoodie")) && (SpecificCategoryProductList.contains(name) == false)) {
        SpecificCategoryProductList.add(name);
      }
    }
    return SpecificCategoryProductList;
  }

  // get total expected revenues from inventory
  public double ExpectedRevenues() {
    double ExpectedRevenues = 0;
    for (Product product : this.ProductList) {
      ExpectedRevenues += (product.getQuantity() * product.getUnitPrice());
    }
    return ExpectedRevenues;
  }

  // get total expected profit from inventory
  public double ExpectedProfit() {
    double ExpectedProfit = 0;
    for (Product product : this.ProductList) {
      ExpectedProfit += (product.getQuantity() * product.getUnitProfit());
    }
    return ExpectedProfit;
  }

  // get total expected profit from a specific category
  public double SpecificCategoryExpectedProfit(String category) {
    double ExpectedProfit = 0;
    List<Product> SpecificCategoryExpectedProfit = new ArrayList<>();
    for (Product product : this.ProductList) {
      String cat = product.getProductCategory();
      if (cat.equals(category)) {
        SpecificCategoryExpectedProfit.add(product);
      }
    }
    for (Product product : SpecificCategoryExpectedProfit) {
      ExpectedProfit += (product.getQuantity() * product.getUnitProfit());
    }
    return ExpectedProfit;
  }

  // information about specific product based on its id
  // get product name based on id:
  public String GetProductName(int id) {
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (id == prodid) {
        return product.getProductName();
      }
    }
    return "No product with such id";
  }

  // get product category based on id:
  public String GetProductCategory(int id) {
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (id == prodid) {
        return product.getProductCategory();
      }
    }
    return "No product with such id";
  }

  // get product category based on id:
  public double GetProductQuantity(int id) {
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (id == prodid) {
        return product.getQuantity();
      }
    }
    return 0;
  }

  // update data about specific product based on its id
  // set product name based on id:
  public void SetProductName(int id, String n) {
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (id == prodid) {
        product.setProductName(n);
      }
    }
  }

  // set product category based on id:
  public void SetProductCategory(int id, String c) {
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (id == prodid) {
        product.setProductCategory(c);
      }
    }
  }

  // set product category based on id:
  public void SetProductQuantity(int id, int q) {
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (id == prodid) {
        product.setQuantity(q);
      }
    }
  }

  // set product category based on id:
  public void addQuantity(int id, int q) {
    for (Product product : this.ProductList) {
      int prodid = product.getProductId();
      if (id == prodid) {
        product.addQuantity(q);
      }
    }
  }

}
