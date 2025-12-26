package labs.java_lab_5;

/**
 * Application entry point demonstrating dependency injection framework.
 */
public class Application {
    
    /**
     * Main method.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Dependency Injection Demo \n");
        
        try {
            runInjectionDemo();
        } catch (InjectionException e) {
            System.err.println("Injection failed: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Caused by: " + e.getCause().getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * Demonstrates dependency injection in action.
     */
    private static void runInjectionDemo() throws InjectionException {
        // Create dependency injector
        DependencyInjector<ServiceContainer> injector = new DependencyInjector<>();
        
        // Create target object
        ServiceContainer container = new ServiceContainer();
        
        System.out.println("Before injection:");
        System.out.println("  Processor: " + container.getProcessor());
        System.out.println("  Handler: " + container.getHandler());
        System.out.println();
        
        // Inject dependencies
        injector.inject(container);
        
        System.out.println("After injection:");
        System.out.println("  Processor: " + container.getProcessor().getClass().getSimpleName());
        System.out.println("  Handler: " + container.getHandler().getClass().getSimpleName());
        System.out.println();
        
        // Execute operations
        container.execute();
    }
}