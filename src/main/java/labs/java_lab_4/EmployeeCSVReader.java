package labs.java_lab_4;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

/**
 * CSV reader specialized for Employee records.
 * @param <T> collection type containing employees
 */
public class EmployeeCSVReader<T extends Collection<Employee>> extends CSVDataReader<T, Employee> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Constructs employee reader with default settings.
     * @param resourcePath path to employee CSV file
     */
    public EmployeeCSVReader(String resourcePath) {
        super(resourcePath);
    }

    /**
     * Constructs employee reader with custom delimiter.
     * @param resourcePath path to employee CSV file
     * @param delimiter field delimiter
     */
    public EmployeeCSVReader(String resourcePath, char delimiter) {
        super(resourcePath, delimiter);
    }

    @Override
    protected void processRecords(List<String[]> records, T collection) throws IOException {
        for (String[] record : records) {
            try {
                Employee employee = parseRecord(record);
                collection.add(employee);
            } catch (Exception e) {
                System.err.println("Failed to parse record: " + String.join(", ", record));
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    protected Employee parseRecord(String[] fields) {
        if (fields.length < 6) {
            throw new IllegalArgumentException("Insufficient fields in record. Expected 6, got " + fields.length);
        }

        // Format: id;name;gender;BirthDate(dd.MM.yyyy);Division;Salary
        int employeeId = parseInteger(fields[0].trim(), "ID");
        String name = fields[1].trim();
        String gender = fields[2].trim();
        LocalDate birthDate = parseDate(fields[3].trim());
        Department dept = new Department(fields[4].trim().charAt(0));
        int salary = parseInteger(fields[5].trim(), "Salary");

        return new Employee(employeeId, name, gender, birthDate, dept, salary);
    }

    /**
     * Parses integer from string with error handling.
     * @param value string value
     * @param fieldName field name for error messages
     * @return parsed integer
     */
    private int parseInteger(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                String.format("Invalid %s format: '%s'", fieldName, value), e
            );
        }
    }

    /**
     * Parses date from string.
     * @param value date string
     * @return parsed LocalDate
     */
    private LocalDate parseDate(String value) {
        try {
            return LocalDate.parse(value, DATE_FORMAT);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                String.format("Invalid date format: '%s'. Expected: dd.MM.yyyy", value), e
            );
        }
    }
}