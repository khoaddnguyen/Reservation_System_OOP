import java.util.List;

public class Manager {

    /** Constant for the Directory */
    public static final String DIRECTORY = "c:/646/accounts/";

    /** list of account */
    private List<Account> listOfAccounts;
    
    /** List of Reservations */
    private List<Reservation> listOfReservations; 
    

    /** File IO object */
    private FileIO fileIO;
    
    /**
     * Constructor of the class. 
     */
    public Manager() {
        
        /**
         * Initialize objects.
         *
         * Load the accounts at instantiation of this Manager object.
         */
        fileIO = new JsonDAO();
        
        // Load accounts and Reservations
        this.deSerializeAccounts();
        this.deSerializeReservations();
    }
    
    /**
     * List of Reservations
     * 
     * @return 
     */
    public List<Reservation> getReservations() {
        return this.listOfReservations;
    }
    
    /**
     * 
     * @param account
     * @throws DuplicateObjectException 
     */
    public void addNewAccount(Account account) throws DuplicateObjectException {
        
        // check if account exist
        Account acc = this.getAccount(account.getAccountNumber());
        
        if(acc == null) {
            this.listOfAccounts.add(account);
            this.fileIO.serializeAccount(account, DIRECTORY);
        } else {
            throw new DuplicateObjectException("DuplicateObjectException: An account already exist with this Account Number");
        }
    }
    
    /**
     * 
     * @param reservation
     * @throws DuplicateObjectException
     * @throws IllegalStateException 
     */
    public void addReservation(Reservation reservation) throws DuplicateObjectException, 
            IllegalStateException {
        
        // Check reservation already exist
        Reservation res = this.getReservation(reservation.getAccountNumber(), reservation.getReservationNumber());
        
        if(res == null) {
            this.listOfReservations.add(res);
            this.fileIO.serializeReservation(reservation, DIRECTORY);
        } else {
            throw new IllegalStateException("IllegalStateException: Erorr saving Reservation to the file");
        }
    }
    
    /**
     * Serialize all the accounts. 
     */
    public void serializeAccounts() {
        this.fileIO.serializeAccounts(listOfAccounts, DIRECTORY);
    }
    
    /**
     * Function to serialize all the reservations
     */
    public void serializeReservations() {
        this.fileIO.serializeReservations(listOfReservations, DIRECTORY);
    }
    
    /**
     * Function to de-serialize all the reservations
     */
    public void deSerializeReservations() {
        this.listOfReservations = this.fileIO.deserializeReservations(listOfAccounts, DIRECTORY);
    }
    
    /**
     * Function to de-serialize all the accounts. 
     * 
     * @throws IllegalLoadException 
     */
    public void deSerializeAccounts() throws IllegalLoadException {
        this.listOfAccounts = this.fileIO.deserializeAccounts(DIRECTORY);
    }
    
    /**
     * 
     * @param reservation
     * @return
     * @throws IllegalOperationException 
     */
    public boolean updateReservation(Reservation reservation) throws IllegalOperationException {
        
        // Find the Reservation
        Reservation res = this.getReservation(reservation.getAccountNumber(), reservation.getReservationNumber());
        this.fileIO.serializeReservation(res, DIRECTORY);
        return res.updateReservation(reservation);
    }
    
    /**
     * 
     * @param reservationNumber
     * @return
     * @throws IllegalOperationException 
     */
    public boolean cancelReservation(String reservationNumber) throws IllegalOperationException {
        
        // Find reservation
        Reservation res = null; 
        
        for(Reservation r: this.listOfReservations) {
            if(r.getReservationNumber().equals(reservationNumber)) {
                res = r; 
                this.fileIO.serializeReservation(res, DIRECTORY);
                break;
            }
        }
        
        if(res == null) {
            throw new IllegalOperationException("IllegalOperationException: The requested Reservation is not found");
        }
        
        if(res.getStatus().equals(Status.COMPLETED) || res.getStatus().equals(Status.CANCELLED) ) {
            throw new IllegalOperationException("IllegalOperationException: The requested Reservation Cannote be cancelled");
        }
    
        res.setStatus(Status.CANCELLED);
        return true;  
    }
    
    /**
     * Function to get the list of all the accounts. 
     * 
     * @return account list 
     */
    public List<Account> getAccounts() {
        return this.listOfAccounts;
    }
    
    /**
     * Function to get an Account from the list of accounts identified by the 
     * account number. 
     * 
     * @param accountNumber to identify account
     * @return account if found, null otherwise
     */
    public Account getAccount(String accountNumber) {
        for(Account account: listOfAccounts) {
            if(account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        
        return null;
    }
    
    /**
     * 
     * @param account
     * @return true if updated, false otherwise
     * @throws IllegalOperationException
     * @throws IllegalStateException 
     */
    public boolean updateAccount(Account account) throws IllegalOperationException, IllegalStateException {
        
        Account acc = this.getAccount(account.getAccountNumber());

        if(acc == null) {
            throw new IllegalOperationException("IllegalOperationException: The requested account doesnot exist");
        }
        
        acc = (Account) account.clone();
        this.fileIO.serializeAccount(account, DIRECTORY);
        
        return true; 
    }
    
    /**
     * Function to calculate the cost of per night reservation of a reservation. 
     * 
     * @param reservationNumber
     * @return cost 
     * @throws IllegalOperationException
     */
    public double calculatePricePerNight(String reservationNumber) 
        throws IllegalOperationException {
        
        Reservation res = null; 
        
        for(Reservation r: this.listOfReservations) {
            if(r.getReservationNumber().equalsIgnoreCase(reservationNumber)) {
                res = r; 
            }
        }
        
        if(res == null) {
            throw new IllegalOperationException("IllegalOperationException: Reserverion is not found with Reservation Number: " + reservationNumber);
        }
        
        return res.calculatePrice();
    }
    
    /**
     * Function to compute the total cost of reservation. 
     * 
     * @param reservationNumber
     * @return total cost
     * @throws IllegalOperationException
     */
    public double calculateTotalPrice(String reservationNumber) 
        throws IllegalOperationException{
        
         Reservation res = null; 
        
        for(Reservation r: this.listOfReservations) {
            if(r.getReservationNumber().equalsIgnoreCase(reservationNumber)) {
                res = r; 
            }
        }
        
        if(res == null) {
            throw new IllegalOperationException("IllegalOperationException: Reserverion is not found with Reservation Number: " + reservationNumber);
        }
        
        return res.calculatePrice() * res.getNumberOfNights();
    }
    
    /**
     * Function to get a Reservation Object identified by the Account Number and a Reservation 
     * Number. 
     * 
     * @param accountNumber account number
     * @param reservationNumber reservation number
     * @return reservation object or null
     */
    public Reservation getReservation(String accountNumber, String reservationNumber) {
        for(Reservation res: this.listOfReservations) {
            if(res.getAccountNumber().equalsIgnoreCase(accountNumber) && 
                    res.getReservationNumber().equalsIgnoreCase(reservationNumber)) {
                return res;
            }
        }
        return null; 
    }
    
    
}
