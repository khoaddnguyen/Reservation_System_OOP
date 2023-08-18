import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class HotelReservation extends Reservation {

    /** Room has Kitchenette */
    private boolean kitchenette;

    /**
     * Constructor for the Hotel Reservation class to initialize the object with
     * given parameter values for all the attributes. 
     * 
     * @param reservationNumber of this HotelReservation Object. 
     * @param accountNumber of this HotelReservation Object.
     * @param physicalAddress of this HotelReservation Object.
     * @param mailingAddress of this HotelReservation Object.
     * @param checkInDate of this HotelReservation Object.
     * @param numberOfNights of this HotelReservation Object.
     * @param numberOfBeds of this HotelReservation Object.
     * @param numberOfBathrooms of this HotelReservation Object.
     * @param lodgingSize of this HotelReservation Object.
     * @param kitchenette of this HotelReservation Object.
     */
    public HotelReservation(String reservationNumber, String accountNumber, Address physicalAddress,
                            Address mailingAddress, GregorianCalendar checkInDate, int numberOfNights,
                            int numberOfBeds, int numberOfBathrooms, int lodgingSize, boolean kitchenette) {

        /**
         * Call super(reservationNumber, accountNumber, physicalAddress, mailingAddress, checkInDate,
         *      numberOfNights, numberOfBeds, numberOfBathrooms, lodgingSize, status);
         * Initialize class fields to associated parameter values.
         */
        
        super(reservationNumber, accountNumber, physicalAddress, mailingAddress, checkInDate, numberOfNights, 
                numberOfBeds, numberOfBathrooms, lodgingSize);
       
        this.kitchenette = kitchenette;
    }

    /**
     * Function to check that this lodging has a kitchenette or not. 
     * 
     * @return true if has kitchenette, false otherwise
     */
    public boolean isKitchenette() {
        return kitchenette;
    }

    /**
     * Function to set or update the Kitchenette of this lodging facility. 
     * 
     * @param kitchenette of this lodging facility.
     */
    public void setKitchenette(boolean kitchenette) {
        this.kitchenette = kitchenette;
    }
    
    /**
     * 
     * @param reservation
     * @return true if successful 
     * @throws IllegalOperationException 
     */
    public boolean updateReservation(Reservation reservation) 
        throws IllegalOperationException {
        
        if(reservation != null && reservation instanceof HotelReservation) {
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
            this.kitchenette = ((HotelReservation) reservation).kitchenette;
        } else {
            throw new IllegalOperationException("IllegalOperationException: Try to update an Invalid Reservation object"); 
        }
        
        return true;
    }

    /**
     * Function to calculate price of this reservation per night. 
     * 
     * Additional $50
     * $10 for kitchenette
     * 
     * @return cost per night 
     */
    public double calculatePrice() {
        double base = super.calculatePrice();
        
        if(this.kitchenette) {
            base += 10;
        }
        
        base += 50; 
        
        return base; 
    }
     
    /**
     * Create clone of this object. 
     * 
     * @return clone 
     */
    public Object clone() {
        /**
         * return new HotelReservation(reservationNumber, accountNumber, physicalAddress,
         *                             mailingAddress, checkInDate, numberOfNights,
         *                             numberOfBeds, numberOfBathrooms, lodgingSize, status,
         *                             kitchenette)
         */
         // FIXME
         return new HotelReservation(reservationNumber, accountNumber, (Address) physicalAddress.clone(),
                            (Address) mailingAddress.clone(), checkInDate, numberOfNights,
                            numberOfBeds, numberOfBathrooms, lodgingSize, kitchenette);
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
         *     "kitchenette": kitchenette
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
        output += String.format("\"kitchenette\": %s,\n", kitchenette);
        
        output += "}";
        return output;
    }

}
