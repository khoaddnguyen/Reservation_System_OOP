import com.google.gson.Gson;
import java.util.List;

/**
 * Interface to provide a contract for the File IO. Adaption of this interface
 * will achieve the separation of concern using a concrete class that can handle
 * any file type. 
 * 
 * @author Programmer
 */
public interface FileIO {
    
    /**
     * Function to serialize all the account to a file. The actual logic or file type
     * will be the decision of the concrete class implementing this interface. 
     * 
     * @param accounts accounts to be serialized
     * @param path file path
     */
    public void serializeAccounts(List<Account> accounts, String path);
    
    /**
     * Function to load an de-serialize accounts in a list of accounts from a 
     * given path pointing to a directory of accounts. The implementing class
     * will abide by the contract of this interface. 
     * 
     * @param path directory path to read accounts
     * @return list of accounts read
     * @throws IllegalLoadException
     */
    public List<Account> deserializeAccounts(String path) throws IllegalLoadException;
    
    /**
     * Function to serialize an Individual Account. 
     * 
     * @param account to be serialized as JSOn
     * @param path to the parent directory
     */
    public void serializeAccount(Account account, String path);
    
    /**
     * Function to de-serialize Account
     * 
     * @param path to read the account object
     * @return account object
     * @throws IllegalLoadException if not found 
     */
    public Account deserializeAccount (String path) throws IllegalLoadException;
    
    /**
     * Function to list all the reservations of all the accounts from the file system. 
     * 
     * @param accounts for reservations
     * 
     * @return list of reservations 
     * @throws IllegalLoadException
     */
    public List<Reservation> deserializeReservations(List<Account> accounts, String path)
        throws IllegalLoadException;
    
    /**
     * Function to de-serialize a Reservation
     * @param fileName to read
     * @return reservation object 
     */
    public Reservation deserializeReservation(String fileName) 
        throws IllegalLoadException;
    
    /**
     * JSONSerializer for the Reservation object including sub classes. 
     * 
     * @return JSONSerializer object  
     */
    public Gson getReservationSerializer();
    
    /**
     * Function to serialize a Reservation in the system. 
     * 
     * @param reservation reservation to serialize
     * @param path of parent directory
     */
    public void serializeReservation(Reservation reservation, String path);
    
    /**
     * Function to serialize all the Reservations in the system. 
     * 
     * @param reservations list of reservations
     * @param path of parent directory
     */
    public void serializeReservations(List<Reservation> reservations, String path);
}
