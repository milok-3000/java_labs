package labs.java_lab_5;

/**
 * Primary implementation of MessageHandler.
 */
public class MessageHandlerImpl implements MessageHandler {
    
    @Override
    public void handle() {
        System.out.println("Handling message with primary handler");
    }
}