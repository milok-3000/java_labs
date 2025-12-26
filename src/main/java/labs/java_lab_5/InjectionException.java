package labs.java_lab_5;

/**
 * Exception thrown when dependency injection fails.
 */
public class InjectionException extends Exception {
    
    public InjectionException(String message) {
        super(message);
    }

    public InjectionException(String message, Throwable cause) {
        super(message, cause);
    }
}