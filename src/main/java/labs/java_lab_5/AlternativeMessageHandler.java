package labs.java_lab_5;

/**
 * Alternative implementation of MessageHandler.
 */
public class AlternativeMessageHandler implements MessageHandler {
    
    @Override
    public void handle() {
        System.out.println("Handling message with alternative handler");
    }
}