package controller;

/**
 *Thrown when a system operation cant be fulfilled by the controller.
 *
 */
public class SystemOperationFailureException extends Exception{
    
    /**
     * Creates a new instance representing the error described in the specified
     * message.
     * @param msg       A message that describes the error
     * @param cause     The exception that caused the system operation to fail.
     */
    public SystemOperationFailureException(String msg, Exception cause){
        super(msg, cause);
    }
    
}