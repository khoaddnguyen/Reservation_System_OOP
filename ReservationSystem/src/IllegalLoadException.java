/**
 * Implementation of Illegal Exception. This exception will be raised while
 * a file not found a for given Reservation or an Account 
 * 
 * @author Programmer
 */
public class IllegalLoadException extends RuntimeException {
    
    /**
     * Constructor with a default message for his exception. 
     */ 
    public IllegalLoadException() {
        super("IllegalLoadException: Illegal operation encountered!");
    }
    
    /**
     * Constructor with a Custom built message for a particular Exception
     * reasoning passed to the constructor. 
     * 
     * @param message exception message
     */
    public IllegalLoadException(String message) {
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
