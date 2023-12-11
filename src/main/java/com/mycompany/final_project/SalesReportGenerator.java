import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

        // Calculate total revenue and profit for all products
        Map<Integer, Integer> productQuantities = new HashMap<>();
        float totalRevenue = 0;
        float totalProfit = 0;

        for (Transaction transaction : this.salesTransactions) {
            int productId = transaction.getProductId();
            int quantity = transaction.getTransactionQuantity();

            // Update the total quantity for the product
            productQuantities.put(productId, productQuantities.getOrDefault(productId, 0) + quantity);

            totalRevenue += transaction.getTotalRevenue();
            totalProfit += transaction.getTotalProfit();
        }

        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("Total Profit: $" + totalProfit);

        
    }

        
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
    
            // Sort transactions by date
            salesTransactions.sort(Comparator.comparing(Transaction::getDate));
    
            // Map to store aggregated information by product ID
            Map<Integer, TransactionAggregate> productAggregates = new HashMap<>();
    
            for (Transaction transaction : salesTransactions) {
                Date transactionDate = transaction.getDate();
            
                // Check if the transaction date is within or equal to the specified range
                if (!transactionDate.before(parsedStartDate) && !transactionDate.after(parsedEndDate)) {
                    int productId = transaction.getProductId();
            
                    // Update the aggregate information for the product ID
                    TransactionAggregate aggregate = productAggregates.getOrDefault(productId, new TransactionAggregate());
                    aggregate.addTransaction(transaction);
                    productAggregates.put(productId, aggregate);
                }
            }
            
    
            // Print the aggregated information
            for (Map.Entry<Integer, TransactionAggregate> entry : productAggregates.entrySet()) {
                int productId = entry.getKey();
                TransactionAggregate aggregate = entry.getValue();
    
                System.out.println("Product ID: " + productId);
                System.out.println("Total Quantity Sold: " + aggregate.getTotalQuantity());
                System.out.println("Total Revenue: $" + aggregate.getTotalRevenue());
                System.out.println("Total Profit: $" + aggregate.getTotalProfit());
                System.out.println("-----------------------------");
            }
    
        } catch (ParseException e) {
            System.out.println("Error parsing dates. Please use the format yyyy-MM-dd.");
        }
    }
    
    // Class to store aggregated information for a product ID
    private static class TransactionAggregate {
        private int totalQuantity;
        private float totalRevenue;
        private float totalProfit;
    
        public TransactionAggregate() {
            this.totalQuantity = 0;
            this.totalRevenue = 0;
            this.totalProfit = 0;
        }
    
        public void addTransaction(Transaction transaction) {
            this.totalQuantity += transaction.getTransactionQuantity();
            this.totalRevenue += transaction.getTotalRevenue();
            this.totalProfit += transaction.getTotalProfit();
        }
    
        public int getTotalQuantity() {
            return totalQuantity;
        }
    
        public float getTotalRevenue() {
            return totalRevenue;
        }
    
        public float getTotalProfit() {
            return totalProfit;
        }
    }
    
    public int getProductIdInputFromUser() {
        Scanner scanner = new Scanner(System.in);
    
        int productId = 0;
        boolean validInput = false;
    
        while (!validInput) {
            System.out.print("Enter the product ID: ");
    
            try {
                productId = Integer.parseInt(scanner.nextLine());
    
                if (isProductExists(productId)) {
                    validInput = true;
                } else {
                    System.out.println("Product ID not found. Please enter a valid ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer ID.");
            }
        }
    
        // Do not close the scanner here to allow further input
        // scanner.close();
    
        return productId;
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
    
        int totalQuantity = 0;
        float totalRevenue = 0;
        float totalProfit = 0;
    
        for (Transaction transaction : salesTransactions) {
            if (transaction.getProductId() == productId) {
                totalQuantity += transaction.getTransactionQuantity();
                totalRevenue += transaction.getTotalRevenue();
                totalProfit += transaction.getTotalProfit();
            }
        }
    
        if (totalQuantity > 0) {
            System.out.println("Generating Report for Product ID: " + productId);
    
            // Assuming that the product name is the same for all transactions of the same product
            String productName = salesTransactions.stream()
                    .filter(t -> t.getProductId() == productId)
                    .findFirst()
                    .map(Transaction::getProductName)
                    .orElse("Unknown Product");
    
            System.out.println("Product ID: " + productId);
            System.out.println("Product Name: " + productName);
            System.out.println("Total Quantity Sold: " + totalQuantity);
            System.out.println("Total Revenue: $" + totalRevenue);
            System.out.println("Total Profit: $" + totalProfit);
            System.out.println("-----------------------------");
        } else {
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

        if (fundraisingRevenue > 0 || fundraisingProfit > 0) {
            System.out.println("Total Fundraising Revenue: $" + fundraisingRevenue);
            System.out.println("Total Fundraising Profit: $" + fundraisingProfit + " (All this profit will be donated to Palestine)");
            System.out.println("-----------------------------");
        } else {
            System.out.println("No fundraising transactions found.");
        }
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

        Date[] dateRange = findDateRange();

        if (dateRange != null) {
            System.out.println("Peak Sales Periods: ");
            System.out.println("Start Date: " + dateRange[0]);
            System.out.println("End Date: " + dateRange[1]);
            System.out.println("-----------------------------");
        } else {
            System.out.println("No transactions found within the specified date range.");
        }
    }

    private Date[] findDateRange() {
        if (salesTransactions.isEmpty()) {
            return null;
        }

        // Sort transactions by date
        salesTransactions.sort(Comparator.comparing(Transaction::getDate));

        Date startDate = salesTransactions.get(0).getDate();
        Date endDate = salesTransactions.get(salesTransactions.size() - 1).getDate();

        return new Date[]{startDate, endDate};
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
        int quantitySold = transaction.getTransactionQuantity(); // Use the transaction quantity
        productSoldCounts.put(productId, productSoldCounts.getOrDefault(productId, 0) + quantitySold);
    }

    // Sort products by the total quantity sold in descending order
    List<Map.Entry<Integer, Integer>> sortedProducts = new ArrayList<>(productSoldCounts.entrySet());
    sortedProducts.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());

    System.out.println("Most Popular Products: ");
    for (int i = 0; i < Math.min(5, sortedProducts.size()); i++) {
        Map.Entry<Integer, Integer> entry = sortedProducts.get(i);
        System.out.println("Product ID: " + entry.getKey() + ", Total Quantity Sold: " + entry.getValue());
    }
    System.out.println("-----------------------------");
}

    // Display sales trends over time
private void displaySalesTrends() {
    System.out.println("Sales Trends Over Time: ");
    generateTrendsReport(); // Display overall trends report

    // Group transactions by date
    Map<String, List<Transaction>> transactionsByDate = new HashMap<>();
    for (Transaction transaction : salesTransactions) {
        String transactionDateStr = formatDate(transaction.getDate());
        transactionsByDate.computeIfAbsent(transactionDateStr, k -> new ArrayList<>()).add(transaction);
    }

        // Iterate through each date and display total quantities, revenue, and profit in ascending order
    List<String> sortedDates = transactionsByDate.keySet().stream()
    .sorted()
    .toList();

    for (String date : sortedDates) {
    System.out.println("Date: " + date);

    int totalQuantity = 0;
    float totalRevenue = 0;
    float totalProfit = 0;

    for (Transaction transaction : transactionsByDate.get(date)) {
    totalQuantity += transaction.getTransactionQuantity();
    totalRevenue += transaction.getTotalRevenue();
    totalProfit += transaction.getTotalProfit();
    }

    System.out.println("Total Quantity Sold: " + totalQuantity);
    System.out.println("Total Revenue: $" + totalRevenue);
    System.out.println("Total Profit: $" + totalProfit);
    System.out.println("-----------------------------");
    }


    identifyPeakSalesPeriods();

}

// Helper method to get the total quantity sold for a specific product ID on a given date
private int getTotalQuantitySoldOnDate(int productId, List<Transaction> transactionsOnDate) {
    int totalQuantitySold = 0;
    for (Transaction transaction : transactionsOnDate) {
        if (transaction.getProductId() == productId) {
            totalQuantitySold += transaction.getTransactionQuantity();
        }
    }
    return totalQuantitySold;
}

// Helper method to format the date as "yyyy-MM-dd"
private String formatDate(Date date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
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
