import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class HouseReservation extends Reservation {

    /** Number of floors */
    private int numberOfFloors;

    /**
     * Constructor to setup the HouseReservation Object. 
     * 
     * @param reservationNumber
     * @param accountNumber
     * @param physicalAddress
     * @param mailingAddress
     * @param checkInDate
     * @param numberOfNights
     * @param numberOfBeds
     * @param numberOfBathrooms
     * @param lodgingSize
     * @param numberOfFloors 
     */
    public HouseReservation(String reservationNumber, String accountNumber, Address physicalAddress,
                            Address mailingAddress, GregorianCalendar checkInDate, int numberOfNights,
                            int numberOfBeds, int numberOfBathrooms, int lodgingSize, int numberOfFloors) {

        /**
         * Call super(reservationNumber, accountNumber, physicalAddress, mailingAddress, checkInDate,
         *      numberOfNights, numberOfBeds, numberOfBathrooms, lodgingSize, status);
         * Initialize class fields to associated parameter values.
         */
        super(reservationNumber, accountNumber, physicalAddress, mailingAddress, checkInDate, numberOfNights, 
                numberOfBeds, numberOfBathrooms, lodgingSize);
    }

    /**
     * The function to get the number of floors in this lodging facility. 
     * 
     * @return number of floors 
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Function to set or update the number of floors of this lodging facility. 
     * 
     * @param numberOfFloors in this house 
     */
    public void setNumberOfFloors(int numberOfFloors) {
        
        if(numberOfFloors <= 0) {
            throw new IllegalArgumentException("The number of floors must be a positive number");
        }
        
        this.numberOfFloors = numberOfFloors;
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
            this.numberOfFloors = ((HouseReservation) reservation).numberOfFloors;
        } else {
            throw new IllegalOperationException("IllegalOperationException: Try to update an Invalid Reservation object"); 
        }
        
        return true;
    }

    /**
     * Function to get the Cost of per night reservation of House 
     * 
     * @return per night cost 
     */
    public double calculatePrice() {
        return super.calculatePrice(); 
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
         *                             numberOfFloors)
         */
        return new HouseReservation(reservationNumber, accountNumber, (Address) physicalAddress.clone(),
                            (Address) mailingAddress.clone(), checkInDate, numberOfNights,
                            numberOfBeds, numberOfBathrooms, lodgingSize, numberOfFloors);
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
         *     "numberOfFloors": numberOfFloors
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
        output += String.format("\"numberOfFloors\": %d", numberOfFloors);
        
        output += "}";
        return output;
    }

}
