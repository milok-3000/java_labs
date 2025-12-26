package labs.java_lab_5;

/**
 * Container class demonstrating dependency injection.
 * Dependencies are automatically injected at runtime.
 */
public class ServiceContainer {
    
    @InjectDependency
    private DataProcessor processor;
    
    @InjectDependency
    private MessageHandler handler;

    /**
     * Executes operations using injected dependencies.
     */
    public void execute() {
        if (processor == null || handler == null) {
            throw new IllegalStateException("Dependencies not injected");
        }
        
        System.out.println("Executing service container operations:");
        processor.process();
        handler.handle();
    }

    /**
     * Gets the data processor dependency.
     * @return processor instance
     */
    public DataProcessor getProcessor() {
        return processor;
    }

    /**
     * Gets the message handler dependency.
     * @return handler instance
     */
    public MessageHandler getHandler() {
        return handler;
    }
}