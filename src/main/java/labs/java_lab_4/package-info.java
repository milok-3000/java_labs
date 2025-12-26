/**
 * Provides classes for loading and processing employee data from CSV files.
 * 
 * <p>This package contains a flexible CSV reading framework that can parse
 * employee records including personal information, department assignments,
 * and salary data.</p>
 * 
 * <p>Main components:</p>
 * <ul>
 *   <li>{@link labs.java_lab_4.CSVDataReader} - Abstract base class for CSV reading</li>
 *   <li>{@link labs.java_lab_4.EmployeeCSVReader} - Specialized reader for employee records</li>
 *   <li>{@link labs.java_lab_4.Employee} - Employee data model</li>
 *   <li>{@link labs.java_lab_4.Department} - Department data model with auto-generated IDs</li>
 *   <li>{@link labs.java_lab_4.Application} - Main application with statistical analysis</li>
 * </ul>
 * 
 * <p>The CSV reader supports:</p>
 * <ul>
 *   <li>Custom delimiters (comma, semicolon, etc.)</li>
 *   <li>Optional header row skipping</li>
 *   <li>Date parsing with configurable formats</li>
 *   <li>Error handling with detailed error messages</li>
 *   <li>Resource loading from classpath</li>
 * </ul>
 * 
 * <p>Example usage:</p>
 * <pre>{@code
 * List<Employee> employees = new ArrayList<>();
 * EmployeeCSVReader<List<Employee>> reader = 
 *     new EmployeeCSVReader<>("/foreign_names.csv", ';');
 * reader.loadInto(employees);
 * }</pre>
 * 
 * <p>Expected CSV format:</p>
 * <pre>
 * id;name;gender;BirthDate;Division;Salary
 * 1;John Smith;Male;01.01.1980;A;5000
 * </pre>
 * 
 * @since 1.0
 * @version 1.0
 */
package labs.java_lab_4;