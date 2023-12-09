import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileHandler {
    // Define a method to read employee data from a file
    public static List<Employee> readEmployeesFromFile(String filePath) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create Employee objects
                // Assuming each line has the format:
                // empId,empName,password,role
                String[] parts = line.split(",");
                int empId = Integer.parseInt(parts[0]);
                String empName = parts[1];
                String password = parts[2];
                String role = parts[3];

                // Create a new Employee object and add it to the list
                Employee employee = new Employee(empId, empName, password, role);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // Define a method to write employee data to a file
    public static void writeEmployeesToFile(List<Employee> employees, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                // Write each employee's data to the file
                writer.write(String.format(
                        "%d,%s,%s,%s%n",
                        employee.getPersonId(),
                        employee.getPersonName(),
                        employee.getPassword(),
                        employee.getRole()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
