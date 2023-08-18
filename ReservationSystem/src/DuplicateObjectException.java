/**
 * Implementation of the DuplicateObjectException. This exception will be 
 * raised during adding an account or a reservation. If account or reservation
 * with the their id already exist, this exception will be raised. 
 * 
 * @author Programmer
 */
public class DuplicateObjectException extends RuntimeException {
    
    /**
     * Constructor with a default message for his exception. 
     */ 
    public DuplicateObjectException() {
        super("DuplicateObjectException: Illegal operation encountered!");
    }
    
    /**
     * Constructor with a Custom built message for a particular Exception
     * reasoning passed to the constructor. 
     * 
     * @param message exception message
     */
    public DuplicateObjectException(String message) {
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
