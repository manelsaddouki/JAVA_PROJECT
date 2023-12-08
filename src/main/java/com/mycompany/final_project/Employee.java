import java.util.Scanner;

public class Employee extends Person {
    private String role;
    private String password;

    public Employee(int empId, String empName, String password, String role) {
        super(empId, empName);
        this.password = password;
        this.role = role;
    }

    public boolean authenticate(String enteredName, String enteredPassword) {
        return getPersonName().equals(enteredName) && this.password.equals(enteredPassword);
    }

    public void performAuthorizedAction(SystemManager systemManager, ManageTransactions transactionManager,
        InventoryManager inventoryManager, SalesReportGenerator salesReportGenerator, SalesChart salesChartViewer) {
        if (hasAuthorization("Admin")) {
            displayAdminMenu(systemManager, transactionManager, inventoryManager, salesReportGenerator, salesChartViewer);
        } else if (hasAuthorization("Sales")) {
            displaySalesMenu(systemManager, salesReportGenerator, salesChartViewer);
        } else {
            System.out.println("Unauthorized: This employee does not have the required authorization.");
        }
    }

    private void displayAdminMenu(SystemManager systemManager, ManageTransactions transactionManager,
        InventoryManager inventoryManager, SalesReportGenerator salesReportGenerator, SalesChart salesChartViewer) {
        System.out.println("Admin Menu:");
        System.out.println("1. Action 1");
        System.out.println("2. Action 2");
        System.out.println("3. Action 3");
        System.out.println("4. Action 4");
        System.out.println("5. Action 5");
        System.out.println("6. Action 6");
        System.out.print("Enter your choice: ");

        try (Scanner scanner = new Scanner(System.in)) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Performing high-level action.");
                    // Call method
                    break;
                case 2:
                    System.out.println("Performing another Admin action.");
                    // Call method
                    break;
                case 3:
                    // Call method
                   
                    break;
                case 4:
                    // Call method
                   
                    break;
                case 5:
                    // Call method
                    
                    break;
                case 6:
                    // Call method
                    
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private void displaySalesMenu(SystemManager systemManager, SalesReportGenerator salesReportGenerator,
        SalesChart salesChartViewer) {
        System.out.println("Sales Menu:");
        System.out.println("1. Action 1");
        System.out.println("2. Action 2");
        System.out.println("3. Action 3");
        System.out.println("4. Action 4");
        System.out.print("Enter your choice: ");

        try (Scanner scanner = new Scanner(System.in)) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Performing sales-related action.");
                    // Call method
                    break;
                case 2:
                    System.out.println("Performing another Sales action.");
                    // Call method
                    break;
                case 3:
                    // Call method
                    
                    break;
                case 4:
                    // Call method
                    
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public boolean hasAuthorization(String requiredRole) {
        return this.role.equals(requiredRole);
    }
}
