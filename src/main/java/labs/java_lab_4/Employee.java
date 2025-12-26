package labs.java_lab_4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an employee with personal and work-related information.
 */
public class Employee {
    private final int identifier;
    private final String fullName;
    private final String sex;
    private final LocalDate dateOfBirth;
    private final Department department;
    private final int wage;

    /**
     * Creates an employee instance.
     * @param identifier unique employee identifier
     * @param fullName employee's full name
     * @param sex employee's gender
     * @param dateOfBirth employee's birth date
     * @param department employee's department
     * @param wage employee's salary
     */
    public Employee(int identifier, String fullName, String sex, 
                    LocalDate dateOfBirth, Department department, int wage) {
        this.identifier = identifier;
        this.fullName = fullName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.wage = wage;
    }

    /**
     * Gets employee ID.
     * @return employee identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Gets employee name.
     * @return full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets employee gender.
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Gets birth date.
     * @return date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets employee's department.
     * @return department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Gets employee salary.
     * @return wage
     */
    public int getWage() {
        return wage;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return String.format("Employee{ID=%d, name='%s', gender='%s', birthDate=%s, %s, salary=%d}",
                identifier, fullName, sex, dateOfBirth.format(formatter), department, wage);
    }
}