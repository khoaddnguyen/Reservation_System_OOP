import java.util.GregorianCalendar;
import java.text.*;

public class CabinReservation extends Reservation {

    /** this cabin has full kitchen */
    private boolean fullKitchen;

    /** this cabin has loft */
    private boolean loft;

    /**
     * Function to setup the Cabin Reservation Object with given parameter values. 
     * Constructor will pass parameters to the parent class Reservation. 
     * 
     * @param reservationNumber of this CabinResevation Object
     * @param accountNumber of this CabinResevation Object
     * @param physicalAddress of this CabinResevation Object
     * @param mailingAddress of this CabinResevation Object
     * @param checkInDate of this CabinResevation Object
     * @param numberOfNights of this CabinResevation Object
     * @param numberOfBeds of this CabinResevation Object
     * @param numberOfBathrooms of this CabinResevation Object
     * @param lodgingSize of this CabinResevation Object
     * @param fullKitchen of this CabinResevation Object
     * @param loft of this CabinResevation Object
     */
    public CabinReservation(String reservationNumber, String accountNumber, Address physicalAddress,
                            Address mailingAddress, GregorianCalendar checkInDate, int numberOfNights,
                            int numberOfBeds, int numberOfBathrooms, int lodgingSize, boolean fullKitchen, boolean loft) {

        /**
         * Call super(reservationNumber, accountNumber, physicalAddress, mailingAddress, checkInDate,
         *      numberOfNights, numberOfBeds, numberOfBathrooms, lodgingSize, status);
         * Initialize class fields to associated parameter values.
         */
        
        super(reservationNumber, accountNumber, physicalAddress, mailingAddress, checkInDate, numberOfNights, 
                numberOfBeds, numberOfBathrooms, lodgingSize);
        
        this.fullKitchen = fullKitchen;
        this.loft = loft; 
    }

    /**
     * Function to check that the lodging has a full kitchen. 
     * 
     * @return true if has full kitchen, false otherwise 
     */
    public boolean isFullKitchen() {
        return fullKitchen;
    }

    /**
     * Function to update or set whether this lodging has a full kitchen or not. 
     * 
     * @param fullKitchen or not 
     */
    public void setFullKitchen(boolean fullKitchen) {
        this.fullKitchen = fullKitchen;
    }

    /**
     * Function to check that the lodging has a Loft. 
     * 
     * @return true if has loft, false otherwise 
     */
    public boolean isLoft() {
        return loft;
    }

    /**
     * Function to update or set whether this lodging has a Loft or not. 
     * 
     * @param loft or not 
     */
    public void setLoft(boolean loft) {
        this.loft = loft;
    }
    
    /**
     * 
     * @param reservation
     * @return true if successful 
     * @throws IllegalOperationException 
     */
    public boolean updateReservation(Reservation reservation) 
        throws IllegalOperationException {
        
        if(reservation instanceof CabinReservation) {
            this.reservationNumber = reservation.accountNumber;
            this.accountNumber = reservation.accountNumber;
            this.physicalAddress = (Address) reservation.physicalAddress.clone();

            if(mailingAddress != null) {
                this.mailingAddress = (Address) reservation.mailingAddress.clone();
            }

            this.checkInDate = reservation.checkInDate;
            this.numberOfNights = reservation.numberOfNights;
            this.numberOfBeds = reservation.numberOfBeds;
            this.numberOfBathrooms = reservation.numberOfBathrooms;

            this.lodgingSize = reservation.lodgingSize;
            this.fullKitchen = ((CabinReservation) reservation).fullKitchen;
            this.loft = ((CabinReservation) reservation).loft;
        } else {
            throw new IllegalOperationException("IllegalOperationException: Try to update an Invalid Reservation object"); 
        }
        
        return true;
    }
    
    /**
     * Get the per night cost of this Cabin Reservation
     * 
     * Add $20 if full kitchen
     * $5 addition for number bathrooms more than 1
     * 
     * @return per night cost 
     */
    public double calculatePrice() {
        double base = super.calculatePrice();
        
        if(this.fullKitchen) {
            base += 20; 
        }
        
        if(this.getNumberOfBathrooms() > 1) {
            base += (5 * (this.getNumberOfBathrooms() - 1));
        }
        
        return base; 
    }
    
    /**
     * Create Clone of this object. 
     * @return 
     */
    public Object clone() {
        /**
         * return new CabinReservation(reservationNumber, accountNumber, physicalAddress,
         *                             mailingAddress, checkInDate, numberOfNights,
         *                             numberOfBeds, numberOfBathrooms, lodgingSize, status,
         *                             fullKitchen, loft)
         */
        
        return new CabinReservation(reservationNumber, accountNumber, (Address) physicalAddress.clone(),
                            (Address) mailingAddress.clone(), checkInDate, numberOfNights,
                            numberOfBeds, numberOfBathrooms, lodgingSize, fullKitchen, loft);
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
         *     "status": status,
         *     "fullKitchen": fullKitchen,
         *     "loft": loft
         * }
         */
        
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        
        String output = "{\n"; 
        output += String.format("\"reservationNumber\": \"%s\",\n", this.getReservationNumber());
        output += String.format("\"accountNumber\": \"%s\",\n", this.getAccountNumber());
        output += String.format("\"physicalAddress\": %s,\n", this.physicalAddress);
        output += String.format("\"mailingAddress\": %s,\n", this.mailingAddress);
        output += String.format("\"checkInDate\": \"%s\",\n", format.format(checkInDate.getTime()));
        output += String.format("\"numberOfNights\": %d,\n", this.getNumberOfNights());
        output += String.format("\"numberOfBeds\": %d,\n", this.getNumberOfBeds());
        output += String.format("\"numberOfBathrooms\": %d,\n", this.getNumberOfBathrooms());
        output += String.format("\"lodgingSize\": %d,\n", this.getLodgingSize());
        output += String.format("\"status\": %s,\n", this.getStatus());
        output += String.format("\"fullKitchen\": %s,\n", fullKitchen);
        output += String.format("\"loft\": %s,\n", loft);
        
        output += "}";
        
        return output;
    }
}
