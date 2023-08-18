import java.util.List;
import java.util.ArrayList;

public class Account {

    /** Account number of the account */
    private String accountNumber;

    /** Mailing Address of this account */
    private Address mailingAddress;

    /** Email Address of this account */
    private String emailAddress;

    /** Phone Number of this account */
    private String phoneNumber;

    /** List of Reservation number of this account */
    private List<String> listOfReservations;


    /**
     * Function to create an Account Object with given parameter values passed
     * to the constructor. All the values are validated, must have valid account 
     * number, must have mailing address, email address and phone number. 
     * 
     * @param accountNumber of the account
     * @param mailingAddress of the account
     * @param emailAddress of the account
     * @param phoneNumber of the account
     */
    public Account(String accountNumber, Address mailingAddress, String emailAddress, String phoneNumber) {
        /**
         * Validate Account Number
         * Assign parameter values to the associated class instance fields.
         * Initialize the List of Reservation numbers.
         */
        
        // Validate all fields must not be null
        if(accountNumber == null) {
            throw new IllegalArgumentException("Account Number cannot be empty or null");
        }
        
        // Vadlidate Account Number
        // must have length of 10, starts with "A"
        if(accountNumber.length() != 10 || !accountNumber.startsWith("A")) {
            throw new IllegalArgumentException("Account Number must start with an A, and have 10 characters");
        }
        
        this.accountNumber = accountNumber;
        
        // set and validate values
        this.setMailingAddress(mailingAddress);
        this.setEmailAddress(emailAddress);
        this.setPhoneNumber(phoneNumber);
        
        // initiallyze Empty List of reservations
        this.listOfReservations = new ArrayList<>();
    }
    
    /**
     * Function to get the Account number of this Account object.
     * 
     * @return account number of this Account object. 
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    
    /**
     * Function to get the Mailing Address of this Account. 
     * 
     * @return mailing address of this Account object.
     */
    public Address getMailingAddress() {
        return mailingAddress;
    }

    /**
     * Function to set or update the mailing address of this Account object.  
     * 
     * @param mailingAddress of this Account object. 
     */
    public void setMailingAddress(Address mailingAddress) {
        
        if(mailingAddress == null) {
            throw new IllegalArgumentException("Account's mailing Address cannot be empty or null");
        }
        
        this.mailingAddress = mailingAddress;
    }

    /**
     * Function to get the Email address of this Account object.
     * 
     * @return email address of this Account object. 
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Function to set or update the email address of this Account object.
     * 
     * @param emailAddress of this Account object. 
     */
    public void setEmailAddress(String emailAddress) {
        
        if(emailAddress == null) {
            throw new IllegalArgumentException("Account's Email Address cannot be empty or null");
        }
        
        this.emailAddress = emailAddress;
    }

    /**
     * Function to get the phone number of this account. 
     * 
     * @return phone number of this Account object.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Function to set or update the phone number of this account. 
     * 
     * @param phoneNumber of this Account object. 
     */
    public void setPhoneNumber(String phoneNumber) {
        
        if(phoneNumber == null) {
            throw new IllegalArgumentException("Account's phone number cannot be empty or null");
        }
        
        this.phoneNumber = phoneNumber;
    }

    /**
     * Function to get the List of Reservations Numbers for this Account. 
     * 
     * @return list of reservation numbers.  
     */
    public List<String> getListOfReservations() {
        return listOfReservations;
    }

    /**
     * Function to add new reservation number into the list of this accounts
     * reservations. 
     * 
     * @param reservationNumber for this account.  
     */
    public void addReservation(String reservationNumber) {
        this.listOfReservations.add(reservationNumber);
    }
    

    @Override
    /**
     * Function to produce a clone of this Account object.
     *
     * @return cloned object
     */
    public Object clone() {
        /**
         * Create new Account(this.accountNumber, this.mailingAddress, this.emailAddress, this.phoneNumber)
         * Deep copy Reservation List
         * return new object.
         */
        return new Account(accountNumber, (Address)mailingAddress.clone(), 
                emailAddress, phoneNumber);
    }

    @Override
    /**
     * Function to get the String representation of the Account Object.
     *
     * @return String representation
     */
    public String toString() {
        /**
         * return
         * {
         *  "accountNumber": accountNumber,
         *  "mailingAddress": {
         *      "street": street,
         *      "state": state,
         *      "zip", zip,
         *      "country": country
         *      },
         *   "emailAddress": emailAddress,
         *   "phoneNumber" : phoneNUmber,
         *   "listOfReservations": [
         *      {reservationNumber1},
         *      ....,
         *      {reservationNumberN}
         *   ]
         * }
         */
        String output = "{\n";
        output += String.format("\"accountNumber\": \"%s,\"%n", accountNumber);
        output += String.format("\"mailingAddress\": %s,%n", mailingAddress);
        output += String.format("\"emailAddress\": \"%s,\"%n", emailAddress);
        output += String.format("\"phoneNumber\": \"%s,\"%n", phoneNumber);
        output += String.format("\"listOfReservations\": [%n");
        int count = 0; 
        for(String res: listOfReservations) {
            if(count == 0) {
                output += res + ",\n";
            } else {
                output += ", " + res + "\n";
            }
            
            count++;
        }
        output += "]\n";
        output += "}";
        return output;
    }
}
