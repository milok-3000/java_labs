package labs.java_lab_4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Application entry point demonstrating employee data loading from CSV.
 */
public class Application {
    
    /**
     * Main method.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        try {
            List<Employee> employeeList = loadEmployeesFromCSV();
            displayEmployees(employeeList);
            printStatistics(employeeList);
        } catch (IOException e) {
            System.err.println("Failed to load employee data: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads employees from CSV file.
     * @return list of employees
     * @throws IOException if file reading fails
     */
    private static List<Employee> loadEmployeesFromCSV() throws IOException {
        List<Employee> employees = new ArrayList<>();
        EmployeeCSVReader<List<Employee>> reader = 
            new EmployeeCSVReader<>("/foreign_names.csv", ';');
        
        reader.loadInto(employees);
        
        System.out.println("Successfully loaded " + employees.size() + " employees.\n");
        return employees;
    }

    /**
     * Displays all employees.
     * @param employees list of employees to display
     */
    private static void displayEmployees(List<Employee> employees) {
        System.out.println("Employee List\n");
        
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        
        System.out.println();
    }

    /**
     * Prints statistical information about employees.
     * @param employees list of employees
     */
    private static void printStatistics(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to analyze.");
            return;
        }

        System.out.println("Statistics");
        System.out.println("Total employees: " + employees.size());
        
        // Count unique departments
        long uniqueDepartments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .count();
        System.out.println("Unique departments: " + uniqueDepartments);
        
        // Calculate average salary
        double avgSalary = employees.stream()
                .mapToInt(Employee::getWage)
                .average()
                .orElse(0.0);
        System.out.printf("Average salary: %.2f%n", avgSalary);
        
        // Find max salary
        int maxSalary = employees.stream()
                .mapToInt(Employee::getWage)
                .max()
                .orElse(0);
        System.out.println("Maximum salary: " + maxSalary);
        
        // Find min salary
        int minSalary = employees.stream()
                .mapToInt(Employee::getWage)
                .min()
                .orElse(0);
        System.out.println("Minimum salary: " + minSalary);
    }
}