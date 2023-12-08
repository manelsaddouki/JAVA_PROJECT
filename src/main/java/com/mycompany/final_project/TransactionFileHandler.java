package main.java.com.mycompany.final_project;

import java.util.List;
import java.util.Date;

import com.mycompany.final_project.Product;
import com.mycompany.final_project.Transaction;
import java.io.*;
import java.util.ArrayList;

public class TransactionFileHandler {

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
                // Write each product's data to the file
                writer.write(String.format(
                        "%date,%d,%d",
                        transaction.getDate(),
                        transaction.getProductId(),
                        transaction.getTransactionQuantity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
