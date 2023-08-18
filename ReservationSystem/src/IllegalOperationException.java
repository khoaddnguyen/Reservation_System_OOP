/**
 * Implementation of the IllegalOperationException Exception class. This class
 * will be raised in a situation when Cancelling or Completing a Reservation
 * but it cannot be done. 
 * 
 * @author Programmer
 */
public class IllegalOperationException extends RuntimeException {
    
    /**
     * Constructor with a default message for his exception. 
     */ 
    public IllegalOperationException() {
        super("IllegalOperationException: Illegal operation encountered!");
    }
    
    /**
     * Constructor with a Custom built message for a particular Exception
     * reasoning passed to the constructor. 
     * 
     * @param message exception message
     */
    public IllegalOperationException(String message) {
        super(message);
    }
    
    @Override
    /**
     * Method to get the String representation of this exception.  
     * 
     * @return string representation
     */
    public String toString() {
       return super.toString(); 
    }
}
