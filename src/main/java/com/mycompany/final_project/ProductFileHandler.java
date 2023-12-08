/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project;

/**
 *
 * @author Manel Saddouki
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFileHandler {
    // Define a method to read product data from a file
    public static List<Product> readProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create Product objects
                // Assuming each line has the format:
                // productId,productName,productCategory,quantity,unitPrice,unitProfit
                String[] parts = line.split(",");
                int productId = Integer.parseInt(parts[0]);
                String productName = parts[1];
                String productCategory = parts[2];
                int quantity = Integer.parseInt(parts[3]);
                float unitPrice = Float.parseFloat(parts[4]);
                float unitProfit = Float.parseFloat(parts[5]);

                // Create a new Product object and add it to the list
                Product product = new Product(productId, productName, productCategory, quantity, unitPrice, unitProfit);
                product.setProductCategory(productCategory);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Define a method to write product data to a file
    public static void writeProductsToFile(List<Product> products, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                // Write each product's data to the file
                writer.write(String.format(
                        "%d,%s,%s,%d,%.2f,%.2f%n",
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductCategory(),
                        product.getQuantity(),
                        product.getUnitPrice(),
                        product.getUnitProfit()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
