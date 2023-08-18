import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Reservation {
    /** Reservation Number of this reservation */
    protected String reservationNumber;

    /** Account number of account who made this reservation */
    protected String accountNumber;

    /** Physical Address of facility */
    protected Address physicalAddress;

    /** Mailing Address of facility */
    protected Address mailingAddress;

    /** check in Date */
    protected GregorianCalendar checkInDate;

    /** Number of nights */
    protected int numberOfNights;

    /** number of beds */
    protected int numberOfBeds;

    /** Number of bathrooms */
    protected int numberOfBathrooms;

    /** Lodging Size */
    protected int lodgingSize;

    /** Status of the Reservation */
    protected Status status;

    
    /**
     * Function to create instance of a Reservation from the given parameter
     * values passed to the constructor through parameters. 
     * 
     * Validate parameters: Reservation Number, 
     * 
     * @param reservationNumber of this Reservation Object
     * @param accountNumber of this Reservation Object
     * @param physicalAddress of this Reservation Object
     * @param mailingAddress of this Reservation Object
     * @param checkInDate of this Reservation Object
     * @param numberOfNights of this Reservation Object
     * @param numberOfBeds of this Reservation Object
     * @param numberOfBathrooms of this Reservation Object
     * @param lodgingSize of this Reservation Object
     */
    public Reservation(String reservationNumber, String accountNumber, Address physicalAddress,
                       Address mailingAddress, GregorianCalendar checkInDate, int numberOfNights,
                       int numberOfBeds, int numberOfBathrooms, int lodgingSize) {
        /**
         * Validate the parameter values except mailingAddress that can be null
         * assign the parameter values to the corresponding class instance fields.
         */
        
        // length must be 10
        if(reservationNumber == null || reservationNumber.length() != 10) {
            throw new IllegalArgumentException("Reservation Number must have 10 Characters");
        }
        
        this.reservationNumber = reservationNumber;
        
        this.setAccountNumber(accountNumber);
        this.setPhysicalAddress(physicalAddress);
        this.setMailingAddress(mailingAddress);
        this.setCheckInDate(checkInDate);
        this.setNumberOfNights(numberOfNights);
        this.setNumberOfBeds(numberOfBeds);
        this.setNumberOfBathrooms(numberOfBathrooms);
        this.setLodgingSize(lodgingSize);        
        
        this.status = Status.DRAFT;
    }
    
    /**
     * Function to get the Reservation Number of this Reservation Object.
     * 
     * @return reservation number of this Reservation Object 
     */
    public String getReservationNumber() {
        return reservationNumber;
    }
    
    /**
     * Function to get the account number that owns this Reservation. 
     * 
     * @return account number of this Reservation Object 
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * The Function to set or update the account number of this Reservation Object.
     * 
     * @param accountNumber of this Reservation Object 
     */
    public void setAccountNumber(String accountNumber) {
        
        if(accountNumber == null) {
            throw new IllegalArgumentException("Account Number must not be empty or null");
        }
        
        this.accountNumber = accountNumber;
    }
    
    /**
     * The function to get the Physical Address of the lodging. 
     * 
     * @return physical address of this lodging facility. 
     */
    public Address getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * Function to set or update the Physical address of this Lodging Facility. 
     * 
     * @param physicalAddress of this Reservation Object 
     */
    public void setPhysicalAddress(Address physicalAddress) {
        
        if(physicalAddress == null) {
            throw new IllegalArgumentException("The Physical Address cannot be null or empty");
        }
        
        this.physicalAddress = physicalAddress;
    }

    /**
     * Function to get the mailing address of this reservation's lodging.
     * 
     * @return mailing address 
     */
    public Address getMailingAddress() {
        return mailingAddress;
    }

    /**
     * Function to set or update the Mailing address of this Lodging Facility. 
     * 
     * @param mailingAddress 
     */
    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    /**
     * Function to get the Check in Date of this reservation. 
     * 
     * @return check in date 
     */
    public GregorianCalendar getCheckInDate() {
        return checkInDate;
    }

    /**
     * Function to set or update the Check in date of this reservation. 
     * 
     * @param checkInDate of this Reservation Object 
     */
    public void setCheckInDate(GregorianCalendar checkInDate) {
        
        if(checkInDate == null) {
            throw new IllegalArgumentException("The check in Date cannot be null or empty");
        }
        
        this.checkInDate = checkInDate;
    }
    
    /**
     * A function to get the number of nights for this reservation. 
     * 
     * @return number of nights.  
     */
    public int getNumberOfNights() {
        return numberOfNights;
    }

    /**
     * The function to set or update the number of nights of this reservation.
     * 
     * @param numberOfNights of this reservation. 
     */
    public void setNumberOfNights(int numberOfNights) {
        
        if(numberOfNights <= 0) {
            throw new IllegalArgumentException("Number of nights cannot be Non-Positive");
        }
        
        this.numberOfNights = numberOfNights;
    }
    
    /**
     * Function to get the number of beds in the reservation lodging facility. 
     * 
     * @return number of beds 
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Function to set or update the number of beds of the lodging facility. 
     * 
     * @param numberOfBeds of this reservation.  
     */
    public void setNumberOfBeds(int numberOfBeds) {
        
        if(numberOfBeds <= 0) {
            throw new IllegalArgumentException("The number of beds must be a positive number");
        }
        
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * Function to get the number of bath rooms in this lodging facility.  
     * 
     * @return number of bathrooms
     */
    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * Function to set or update the number of bathrooms in this lodging facility. 
     * 
     * @param numberOfBathrooms in this lodging facility 
     */
    public void setNumberOfBathrooms(int numberOfBathrooms) {
        
        if(numberOfBathrooms <= 0) {
            throw new IllegalArgumentException("Number of Barthrooms must be a positive number");
        }
        
        this.numberOfBathrooms = numberOfBathrooms;
    }
    
    /**
     * Function to get the lodging size
     * 
     * @return lodging size 
     */
    public int getLodgingSize() {
        return lodgingSize;
    }

    /**
     * Function to set or update the lodging size of this lodging facility. 
     * 
     * @param lodgingSize 
     */
    public void setLodgingSize(int lodgingSize) {
        
        if(lodgingSize <= 0) {
            throw new IllegalArgumentException("The lodging size must be a positive number");
        }
        
        this.lodgingSize = lodgingSize;
    }
    
    /**
     * Function to get the status of this Reservation. 
     * 
     * @return status 
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Function to set or update the status of this Reservation. 
     * 
     * @param status of this reservation 
     */
    public void setStatus(Status status) {
        
        if(status == null) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }
        
        this.status = status;
    }
    
    /**
     * Function to update Reservation. 
     * @param reservation
     * @return
     * @throws IllegalOperationException 
     */
    public abstract boolean updateReservation(Reservation reservation) 
        throws IllegalOperationException;

    /**
     * Function to get the cost per night of this Reservation. 
     * 
     * @return reservation cost
     */
    public double calculatePrice() {
        // return base fee + lodging cost
        double cost = 120.0; 
        
        if(this.lodgingSize > 900) {
            cost += 15;
        }
        
        return cost; 
    }

    public Object clone() {
        /**
         * return new Reservation(reservationNumber, accountNumber, physicalAddress,
         *                        mailingAddress, checkInDate, numberOfNights,
         *                        numberOfBeds, numberOfBathrooms, lodgingSize, status)
         */
        // FIXME
        return null;
    }

    public String toString() {
        /**
         * return {
         *     "reservationNumber": reservationNumber,
         *     "accountNumber": accountNumber,
         *     "physicalAddress: {physicalAddress},
         *     "mailingAddress: {mailingAddress},
         *     "checkInDate": checkInDate,
         *     "numberOfNights": numberOfNights,
         *     "numberOfBeds": numberOfBeds,
         *     "numberOfBathrooms": numberOfBathRooms,
         *     "lodgingSize": lodgingSize,
         *     "status": status
         * }
         */
        // implemented in child classes
        return "";
    }
}
