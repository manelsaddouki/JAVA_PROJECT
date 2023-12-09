import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesReportGenerator {
    private List<Transaction> salesTransactions;

    // Constructor
    public SalesReportGenerator(List<Transaction> salesTransactions) {
        this.salesTransactions = salesTransactions;
    }

    // Method to generate sales reports based on different criteria
    public void generateSalesReport() {
        System.out.println("Sales Report: ");

        // Generate overall trends report
        generateTrendsReport();

        // Generate reports based on date range
        generateReportByDateRange();

        // Generate reports based on a specific product
        generateReportByProduct();

        // Generate fundraising report
        generateFundraisingReport();

        // Calculate and display average sales
        calculateAverageSales();

        // Identify and display peak sales periods
        identifyPeakSalesPeriods();

        // Determine and display the most popular products
        determineMostPopularProducts();

        // Display sales trends over time
        displaySalesTrends();

        // Export the sales report to a text file
        exportReportToFile("SalesReportOutput.txt");

         // Export text file to PDF
         SalesReportPDFExporter.exportTextFileToPDF("SalesReportOutput.txt");

         // Export text file to PDF
         SalesChart salesChart = new SalesChart(salesTransactions); // Assuming myTransactions is accessible
         salesChart.generateSalesTrendsChartToPDF("SalesTrendsChart.pdf");

        
    }

    // Defining the different Methods we called earlier:

    // Generate overall trends report
    private void generateTrendsReport() {
        System.out.println("Overall Trends Report: ");

        // Calculate total revenue and profit for all transactions
        float totalRevenue = 0;
        float totalProfit = 0;

        for (Transaction transaction : this.salesTransactions) {
            totalRevenue += transaction.getTotalRevenue();
            totalProfit += transaction.getTotalProfit();
        }

        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("Total Profit: $" + totalProfit);
    }

    // Generate sales report for a specific date range
    private void generateReportByDateRange() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Start Date (yyyy-MM-dd): ");
        String startDateInput = scanner.next();

        System.out.println("Enter End Date (yyyy-MM-dd): ");
        String endDateInput = scanner.next();

        System.out.println("Generating Report for Date Range: " + startDateInput + " to " + endDateInput);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date parsedStartDate = dateFormat.parse(startDateInput);
            Date parsedEndDate = dateFormat.parse(endDateInput);

            // For demonstration purposes, let's print transactions within the date range
            for (Transaction transaction : salesTransactions) {
                Date transactionDate = transaction.getDate();

                // Check if the transaction date is within the specified range
                if (transactionDate.after(parsedStartDate) && transactionDate.before(parsedEndDate)) {
                    System.out.println("Transaction ID: " + transaction.getProductId());
                    System.out.println("Product Name: " + transaction.getProductName());
                    System.out.println("Quantity Sold: " + transaction.getTransactionQuantity());
                    System.out.println("Total Revenue: $" + transaction.getTotalRevenue());
                    System.out.println("Total Profit: $" + transaction.getTotalProfit());
                    System.out.println("-----------------------------");
                }
            }
        } catch (ParseException e) {
            System.out.println("Error parsing dates. Please use the format yyyy-MM-dd.");
        }
        // Close the scanner to prevent resource leaks
        // scanner.close();
    }

    // Moving to the next Method: generateReportByProduct
    // Method to take user input for product ID
    public int getProductIdInputFromUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            int productId = 0;

            do {
                System.out.print("Enter the product ID: ");
                if (scanner.hasNextInt()) {
                    productId = scanner.nextInt();
                }

                // Check if the entered product ID exists in the transactions
                if (!isProductExists(productId)) {
                    System.out.println("Product ID not found. Please enter a valid ID.");
                }

            } while (!isProductExists(productId));

            return productId;
        }
    }

    // Method to check if the product ID exists in the transactions
    private boolean isProductExists(int productId) {
        for (Transaction transaction : salesTransactions) {
            if (transaction.getProductId() == productId) {
                return true;
            }
        }
        return false;
    }

    // Finally!!! Generate sales report for a specific product based on the entered
    // product ID
    public void generateReportByProduct() {
        int productId = getProductIdInputFromUser();

        boolean found = false;
        for (Transaction transaction : salesTransactions) {
            if (transaction.getProductId() == productId) {
                System.out.println("Generating Report for Product ID: " + productId);
                System.out.println("Transaction ID: " + transaction.getProductId());
                System.out.println("Product Name: " + transaction.getProductName());
                System.out.println("Quantity Sold: " + transaction.getTransactionQuantity());
                System.out.println("Total Revenue: $" + transaction.getTotalRevenue());
                System.out.println("Total Profit: $" + transaction.getTotalProfit());
                System.out.println("-----------------------------");
                found = true;
                break; // Stop searching once the product is found
            }
        }

        if (!found) {
            System.out.println("No transactions found for Product ID: " + productId);
        }
    }


    // Generate fundraising report
    private void generateFundraisingReport() {
        System.out.println("Generating Fundraising Report");

        // Identify and track products with "Palestine" or "Gaza" in their names
        float fundraisingRevenue = 0;
        float fundraisingProfit = 0;

        for (Transaction transaction : salesTransactions) {
            String productName = transaction.getProductName().toLowerCase();
            if (productName.contains("palestine") || productName.contains("gaza")) {
                fundraisingRevenue += transaction.getTotalRevenue();
                fundraisingProfit += transaction.getTotalProfit();
            }
        }

        System.out.println("Total Fundraising Revenue: $" + fundraisingRevenue);
        System.out.println("Total Fundraising Profit: $" + fundraisingProfit + ". All this profit will be donated to Palestine.");
        System.out.println("-----------------------------");
    }

    // Method to calculate average sales
    private void calculateAverageSales() {
        if (salesTransactions.isEmpty()) {
            System.out.println("No transactions available for calculating average sales.");
            return;
        }

        float totalQuantitySold = 0;
        for (Transaction transaction : salesTransactions) {
            totalQuantitySold += transaction.getTransactionQuantity();
        }

        float averageSales = totalQuantitySold / salesTransactions.size();
        System.out.println("Average Sales: " + averageSales);
    }

    // Method to identify peak sales periods
    private void identifyPeakSalesPeriods() {
        if (salesTransactions.isEmpty()) {
            System.out.println("No transactions available for identifying peak sales periods.");
            return;
        }

        // Assuming transactions are sorted by date
        Transaction firstTransaction = salesTransactions.get(0);
        Transaction lastTransaction = salesTransactions.get(salesTransactions.size() - 1);

        System.out.println("Peak Sales Periods: ");
        System.out.println("Start Date: " + firstTransaction.getDate());
        System.out.println("End Date: " + lastTransaction.getDate());
        System.out.println("-----------------------------");
    }

    // Method to determine the most popular products
    private void determineMostPopularProducts() {
        if (salesTransactions.isEmpty()) {
            System.out.println("No transactions available for determining the most popular products.");
            return;
        }

        Map<Integer, Integer> productSoldCounts = new HashMap<>();

        for (Transaction transaction : salesTransactions) {
            int productId = transaction.getProductId();
            productSoldCounts.put(productId, productSoldCounts.getOrDefault(productId, 0) + 1);
        }

        // Sort products by the number of times sold in descending order
        List<Map.Entry<Integer, Integer>> sortedProducts = new ArrayList<>(productSoldCounts.entrySet());
        sortedProducts.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());

        System.out.println("Most Popular Products: ");
        for (int i = 0; i < Math.min(5, sortedProducts.size()); i++) {
            Map.Entry<Integer, Integer> entry = sortedProducts.get(i);
            System.out.println("Product ID: " + entry.getKey() + ", Number of Transactions: " + entry.getValue());
        }
        System.out.println("-----------------------------");
    }

    // Display sales trends over time
    private void displaySalesTrends() {
        System.out.println("Sales Trends Over Time: ");
        generateTrendsReport(); // Display overall trends report
        identifyPeakSalesPeriods(); // Display peak sales periods
    }


    // Method to export the sales report to a text file
    private void exportReportToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Generate various reports
            generateSalesReportContent(writer);

            System.out.println("Sales report exported to file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate total revenue
    private float calculateTotalRevenue() {
        float totalRevenue = 0;
        for (Transaction transaction : salesTransactions) {
            totalRevenue += transaction.getTotalRevenue();
        }
        return totalRevenue;
    }

    // Method to calculate total profit
    private float calculateTotalProfit() {
        float totalProfit = 0;
        for (Transaction transaction : salesTransactions) {
            totalProfit += transaction.getTotalProfit();
        }
        return totalProfit;
    }

    // Method to generate sales report content and write to file
    private void generateSalesReportContent(PrintWriter writer) {
        writer.println("Total Transactions: " + salesTransactions.size());
        writer.println();

        // Generate overall trends report
        writer.println("Overall Trends Report: ");
        writer.println("Total Revenue: $" + calculateTotalRevenue());
        writer.println("Total Profit: $" + calculateTotalProfit());
        writer.println();
    }
}
