/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Manel Saddouki
 */
public class TransactionsFileHandler {
    // Define a method to read product data from a file
    public static List<Transaction> readTransactionsFromFile(final List<Product> ProductList, String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create Product objects
                // Assuming each line has the format:
                // productId,productName,productCategory,quantity,unitPrice,unitProfit
                String[] parts = line.split(",");
                Date transactionDate = java.sql.Date.valueOf(parts[0]);
                int productId = Integer.parseInt(parts[1]);
                int quantity = Integer.parseInt(parts[2]);

                // Create a new Product object and add it to the list
                Transaction trans = new Transaction(ProductList, transactionDate, productId, quantity);
                transactions.add(trans);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    // Define a method to write product data to a file
    public static void writeProductsToFile(List<Transaction> transactions, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction transaction : transactions) {
                // Write each transaction's data to the file
                writer.write(String.format(
                        "%s,%d,%d%n",
                        String.valueOf(transaction.getDate()),
                        transaction.getProductId(),
                        transaction.getTransactionQuantity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
