import java.util.*;

public class Final_Project {
    
    public static void main(String[] args) {
        List<Employee> employees = EmployeeFileHandler.readEmployeesFromFile("EmployeesList.txt");
        Employee authenticatedUser = authenticateUser(employees);
    
        if (authenticatedUser != null) {
            switch (authenticatedUser.getRole()) {
                case "admin":
                    adminMenu();
                    break;

                case "salesperson":
                    salespersonMenu();
                    break;
                default:
                    System.out.println("Invalid role. Exiting...");
                    break;
            }
        }
    }

    private static void adminMenu() {
        // initialize Inventory list
        List<Product> myproducts = ProductFileHandler.readProductsFromFile("ProductsList.txt");
        InventoryManager MystoreInventory = new InventoryManager(myproducts);

        List<Transaction> myTransactions = TransactionsFileHandler.readTransactionsFromFile(myproducts,
                "TransactionsList.txt");

        ManageTransactions MystoreTransactions = new ManageTransactions(myproducts, myTransactions);

        SalesReportGenerator mystoreReport = new SalesReportGenerator(MystoreTransactions.getTransactionsList());

        for (String element : MystoreInventory.SpecificCategoryProductList("Hoodie")) {
            System.out.print(element + "  ");
        }

        try (Scanner scanner = new Scanner(System.in)) {
            int repeat = 1;
            while (repeat == 1) {
            // display:
                System.out.println(" \n \n Admin Menu:");
                System.out.println("1. Inventory Products Information");
                System.out.println("2. Specific Products Information based on its ID");
                System.out.println("3. Update data about one of your Products based on its ID");
                System.out.println("4. Inventory Manipulation");
                System.out.println("5. New Transaction");
                System.out.println("6. List of all Transactions");
                System.out.println("7. Sales Reports");
                System.out.print("Enter your choice: ");

            
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        // Products Functions
                        System.out.println("Admin Menu:");
                        System.out.println(
                                "1. Get Product ID List \n2. Get Product Names List \n3. Get Product Category List \n4. Get Specific Category Product List");
                        int cc = scanner.nextInt();
                        switch (cc) {
                            case 1:
                                for (int element : MystoreInventory.ProductIDsList()) {
                                    System.out.print(element + "  ");
                                }
                                System.out.println();
                                break;
                            case 2:
                                for (String el : MystoreInventory.ProductNamesList()) {
                                    System.out.print(el + "  ");
                                }
                                System.out.println();
                                break;
                            case 3:
                                for (String elem : MystoreInventory.CategoriesList()) {
                                    System.out.print(elem + "  ");
                                }
                                System.out.println();
                                break;
                            case 4:
                                System.out.println("Enter the Category you are looking for: ");
                                String category = scanner.next();
                                System.out.println(category);
                                for (String element : MystoreInventory.SpecificCategoryProductList(category)) {
                                    System.out.print(element + "  ");
                                }
                                System.out.println();
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                        break;

                    case 2:
                        // Get specific product data
                        System.out.println("Enter the id of the product:");
                        int id = scanner.nextInt();
                        System.out.println("Admin Menu:");
                        System.out.println("1. Get Product Name \n2. Get Product Category \n3. Get product Quantity");
                        int c = scanner.nextInt();
                        switch (c) {
                            case 1:
                                System.out.print("Product name is: " + MystoreInventory.GetProductName(id));
                                System.out.println();
                                break;
                            case 2:
                                System.out.print("Product name is: " + MystoreInventory.GetProductCategory(id));
                                System.out.println();
                                break;
                            case 3:
                                System.out.print("Product name is: " + MystoreInventory.GetProductQuantity(id));
                                System.out.println();
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                        break;

                    case 3:
                        // update product data
                        System.out.println("Enter the id of the product:");
                        int ind = scanner.nextInt();
                        System.out.println("Admin Menu:");
                        System.out.println(
                                "1. set Product Name  \n2. Set Product Category  \n3. Set product Quantity \n4. add product Quantity");
                        int ch = scanner.nextInt();
                        switch (ch) {
                            case 1:
                                System.out.println("Enter the new name for your product:");
                                String N = scanner.next();
                                MystoreInventory.SetProductName(ind, N);
                                ProductFileHandler.writeProductsToFile(MystoreInventory.getList(), "ProductsList.txt");
                                break;
                            case 2:
                                System.out.println("Enter the new category for your product:");
                                String categ = scanner.next();
                                MystoreInventory.SetProductCategory(ind, categ);
                                ProductFileHandler.writeProductsToFile(MystoreInventory.getList(), "ProductsList.txt");
                                break;
                            case 3:
                                System.out.println("Update the quantity for your product:");
                                int quant = scanner.nextInt();
                                MystoreInventory.SetProductQuantity(ind, quant);
                                ProductFileHandler.writeProductsToFile(MystoreInventory.getList(), "ProductsList.txt");
                                break;
                            case 4:
                                System.out.println("Add quantity for your product:");
                                int quantity = scanner.nextInt();
                                MystoreInventory.addQuantity(ind, quantity);
                                ProductFileHandler.writeProductsToFile(MystoreInventory.getList(), "ProductsList.txt");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                        break;

                    case 4:
                        // Manipulate inventory
                        System.out.println("Admin Menu:");
                        System.out.println("1. Add new Product \n 2.Delete a product");
                        int chx = scanner.nextInt();
                        switch (chx) {
                            case 1:
                                System.out.println("Enter the id for your product:");
                                int prodid = scanner.nextInt();
                                System.out.println("Enter the name for your product:");
                                String prodname = scanner.next();
                                System.out.println("Enter the category for your product:");
                                String prodcateg = scanner.next();
                                System.out.println("Enter the quantity for your product:");
                                int prodquant = scanner.nextInt();
                                System.out.println("Enter the unit price for your product:");
                                double produnitpr = scanner.nextDouble();
                                System.out.println("Enter the unit profit for your product:");
                                double produnitprof = scanner.nextDouble();
                                Product NewProduct = new Product(prodid, prodname, prodcateg, prodquant, produnitpr,
                                        produnitprof);
                                MystoreInventory.AddProduct(NewProduct);
                                ProductFileHandler.writeProductsToFile(MystoreInventory.getList(), "ProductsList.txt");
                                break;

                            case 2:
                                System.out.println("Enter the id of the product:");
                                int index = scanner.nextInt();
                                MystoreInventory.DeleteProduct(index);
                                ProductFileHandler.writeProductsToFile(MystoreInventory.getList(), "ProductsList.txt");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                        }
                        break;
                    case 5:
                        // add new transaction
                        System.out.println("Enter the id for your product:");
                        int prodind = scanner.nextInt();
                        System.out.println("Enter the quantity sold:");
                        int quantSold = scanner.nextInt();
                        MystoreTransactions.NewTransaction(prodind, quantSold);
                        TransactionsFileHandler.writeProductsToFile(MystoreTransactions.getTransactionsList(),
                                "TransactionsList.txt");
                        ProductFileHandler.writeProductsToFile(MystoreInventory.getList(), "ProductsList.txt");
                        break;

                    case 6:
                        // list of all the transactions
                        for (Transaction trans : MystoreTransactions.getTransactionsList()) {
                            System.out.println(
                                    "Id: " + trans.getTransactionId() + " Date: " + trans.getDate() + " Product ID: "
                                            + trans.getProductId() + " Quantity: " + trans.getTransactionQuantity());
                        }
                        break;

                    case 7:
                        mystoreReport.generateSalesReport();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } catch (NoSuchElementException e) {
            System.out.println("Session Expired. Please log in again!");
        }
    }
        


    private static void salespersonMenu() {
        // initialize Inventory list
        List<Product> myproducts = ProductFileHandler.readProductsFromFile("ProductsList.txt");
        InventoryManager MystoreInventory = new InventoryManager(myproducts);

        List<Transaction> myTransactions = TransactionsFileHandler.readTransactionsFromFile(myproducts,
                "TransactionsList.txt");

        ManageTransactions MystoreTransactions = new ManageTransactions(myproducts, myTransactions);

        SalesReportGenerator mystoreReport = new SalesReportGenerator(MystoreTransactions.getTransactionsList());

        for (String element : MystoreInventory.SpecificCategoryProductList("Hoodie")) {
            System.out.print(element + "  ");
        }
        try (Scanner scanner = new Scanner(System.in)) {
            int repeat = 1;
            while (repeat == 1) {
                // display:
                System.out.println(" \n \n Salesperson Menu:");
            
                System.out.println("1. List of all Transactions");
                System.out.println("2. Sales Reports");
                
                
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                switch (choice) {

                    case 1:
                        // list of all the transactions
                        for (Transaction trans : MystoreTransactions.getTransactionsList()) {
                            System.out.println(
                                    "Id: " + trans.getTransactionId() + " Date: " + trans.getDate() + " Product ID: "
                                            + trans.getProductId() + " Quantity: " + trans.getTransactionQuantity());
                        }
                        break;

                    case 2:
                        mystoreReport.generateSalesReport();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } catch (NoSuchElementException e) {
            System.out.println("Session Expired. Please log in again!");
    }
    }
    
    

    private static Employee authenticateUser(List<Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String enteredName = scanner.next();
        System.out.print("Enter your password: ");
        String enteredPassword = scanner.next();
    
        for (Employee employee : employees) {
            if (employee.authenticate(enteredName, enteredPassword)) {
                return employee;
            }
        }
        System.out.println("Authentication failed. Exiting...");
        return null;
    }
    
}
