package labs.java_lab_4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents an organizational department.
 */
public class Department {
    private final int departmentId;
    private final String departmentName;
    
    private static final Map<String, Integer> departmentRegistry = new ConcurrentHashMap<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1000);

    /**
     * Creates or retrieves a department with the given name.
     * @param name department name
     */
    public Department(String name) {
        this.departmentName = name;
        this.departmentId = departmentRegistry.computeIfAbsent(
            name, 
            k -> idGenerator.getAndIncrement()
        );
    }

    /**
     * Alternative constructor accepting a character as name.
     * @param nameChar single character department identifier
     */
    public Department(Character nameChar) {
        this(String.valueOf(nameChar));
    }

    /**
     * Gets department ID.
     * @return unique department identifier
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Gets department name.
     * @return department name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return String.format("Department{ID=%d, name='%s'}", departmentId, departmentName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Department)) return false;
        Department other = (Department) obj;
        return departmentId == other.departmentId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(departmentId);
    }
}