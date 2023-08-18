import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Programmer
 */
public class ReservationSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Call Test Methods
        testloadData();
        testAddAccount();
        testAddReservation();
        testCalculatePricePerNight();
        testCalcualteTotalPrice();
        testGetAccounts();
        testGetReservations();
        testDeserializeAccounts();
        testDeserializeReservations();
        testUpdateAccount();
        testUpdateReservation();
        testCancelReservation();
    }
 
    /**
     * Testing the loading of data from the files. 
     */
    private static void testloadData() {
        Manager manager = new Manager();
        List<Account> accountList = manager.getAccounts(); 
        List<Reservation> reservationList = manager.getReservations();
        printTestName("Test Load Data from JSON Files");
        System.out.printf("Number of Accounts loaded from the JSON Files: %d%n", accountList.size());
        System.out.printf("Number of Reservations loaded from the JSON Files: %d%n", reservationList.size());
    }
    
    /**
     * Function to test the add account function of Manager class. 
     */
    public static void testAddAccount() {
        Manager manager = new Manager();
        List<Account> accountList = manager.getAccounts(); 
        printTestName("Test Add new Account");
        System.out.printf("Before add account: %n");
        System.out.printf("Number of Accounts loaded from the JSON Files: %d%n", accountList.size());
        System.out.printf("After add account: %n");
        
        Address address = new Address("91 7th Ave", "New York", "NY", "12234", "USA");
        Random random = new Random();
        Account account = new Account("A" + (random.nextInt(99999999) + 100000000), address, "jazz@gmail.com", "(321) 123-9992");
        manager.addNewAccount(account);
        
        // Check account is added
        accountList = manager.getAccounts();
        System.out.printf("Number of Accounts loaded from the JSON Files: %d%n", accountList.size());
        
        // Check loaded from file is same
        manager.deSerializeAccounts();
        System.out.printf("After loading from file: %n");
        accountList = manager.getAccounts();
        System.out.printf("Number of Accounts loaded from the JSON Files: %d%n", accountList.size());
    }
    
    /**
     * Function to add new Reservation into the system. 
     */
    public static void testAddReservation() {
        Manager manager = new Manager();
        List<Account> accountList = manager.getAccounts(); 
        List<Reservation> reservationList = manager.getReservations();
        printTestName("Test Add Reservation");
        System.out.printf("Number of Accounts loaded from the JSON Files: %d%n", accountList.size());
        System.out.printf("Number of Reservations loaded from the JSON Files: %d%n", accountList.size());
        
        // Create Reservation
        Random random = new Random();
        GregorianCalendar today = new GregorianCalendar();
        today.setTimeInMillis(System.currentTimeMillis());
        Address address = new Address("191 17th Ave", "New York", "NY", "12299", "USA");
        Reservation res = new CabinReservation("AB" + (random.nextInt(9999999) + 10000000),
                   accountList.get(0).getAccountNumber(), address, null, today, 
                   2, 1, 1, 900, true, true);
        
        manager.addReservation(res);
        
        reservationList = manager.getReservations();
        printTestName("After adding new Reservation");
        System.out.printf("Number of Reservations loaded from the JSON Files: %d%n", reservationList.size());
    }
    
    /**
     * Function to test Price Per Night. 
     */
    private static void testCalculatePricePerNight() {
        Manager manager = new Manager();
        List<Reservation> reservationList = manager.getReservations();
        printTestName("Test Calculate Price Per Night");
        for(Reservation res: reservationList) {
            System.out.printf("Total price of Reservation %s is %.2f%n", 
                    res.getReservationNumber(), manager.calculatePricePerNight(res.getReservationNumber()));
        }
    }
    
    /**
     * Function to test Total price of reservation. 
     */
    private static void testCalcualteTotalPrice() {
        Manager manager = new Manager();
        List<Reservation> reservationList = manager.getReservations();
        printTestName("Test Calculate Total Price");
        for(Reservation res: reservationList) {
            System.out.printf("Total price of Reservation %s is %.2f%n", 
                    res.getReservationNumber(), manager.calculateTotalPrice(res.getReservationNumber()));
        }
    }
    
    /**
     * Test Get Accounts
     */
    public static void testGetAccounts() {
        Manager manager = new Manager();
        List<Account> accountList = manager.getAccounts(); 
        printTestName("Test Get Accounts");
        System.out.printf("Number of Accounts loaded from the JSON Files: %d%n", accountList.size());
        
        // print accounts
        for(Account acc: accountList) {
            System.out.println(acc.toString());
        }
    }
    
    /**
     * Test Get Reservations
     */
    public static void testGetReservations() {
       Manager manager = new Manager();
        List<Reservation> reservationList = manager.getReservations();
        printTestName("Test Get Reservations");
        System.out.printf("Number of Reservations loaded from the JSON Files: %d%n", reservationList.size());
        for(Reservation res: reservationList) {
            System.out.println(res.toString());
        }
    }
    
    /**
     * Test Get Accounts
     */
    public static void testDeserializeAccounts() {
        Manager manager = new Manager();
        
        // explicit deserialize
        manager.deSerializeAccounts();
        
        List<Account> accountList = manager.getAccounts(); 
        printTestName("Test Get Accounts");
        System.out.printf("Number of Accounts loaded from the JSON Files: %d%n", accountList.size());
        
        // print accounts
        for(Account acc: accountList) {
            System.out.println(acc.toString());
        }
    }
    
    /**
     * Test Get Reservations
     */
    public static void testDeserializeReservations() {
       Manager manager = new Manager();
       
       // Explicit Deserialize
       manager.deSerializeReservations();
       
       List<Reservation> reservationList = manager.getReservations();
        printTestName("Test Get Reservations");
        System.out.printf("Number of Reservations loaded from the JSON Files: %d%n", reservationList.size());
        for(Reservation res: reservationList) {
            System.out.println(res.toString());
        }
    }
    
    /**
     * Function to Test the Account Update. 
     */
    public static void testUpdateAccount() {
        Manager manager = new Manager();
        List<Account> accountList = manager.getAccounts(); 
        printTestName("Test Account Update");
        Account account = accountList.get(0);
        System.out.printf("Before Update: %s%n", account);
        account.setEmailAddress("GOAT.gmail.com");
        System.out.printf("After Update: %s%n", account);
        
    }
    
    /**
     * Mark the Reservation Completed
     */
    public static void testUpdateReservation() {
        Manager manager = new Manager();
       
       // find a reseration in DRAFT
       List<Reservation> reservationList = manager.getReservations();
       Reservation res = null; 
       
       for(Reservation r: reservationList) {
           if(r.getStatus().equals(Status.DRAFT)) {
               res = r;
               break;
           }
       }
       
       printTestName("Test Complete Reservation");
       
       if(res != null) {
           System.out.printf("Resevation to be updated: %s%n", res);
           res.setStatus(Status.COMPLETED);
           manager.updateReservation(res);
            System.out.println("After Update: \n" + manager.getReservation(res.getAccountNumber(), 
                   res.getReservationNumber()));
       } else {
           System.out.println("There is no Reservation in DRAFT STatus");
       }
    }
    
    /**
     * Test Cancel a Reservation and serialize
     */
    public static void testCancelReservation() {
       Manager manager = new Manager();
       
       // find a reseration in DRAFT
       List<Reservation> reservationList = manager.getReservations();
       Reservation res = null; 
       
       for(Reservation r: reservationList) {
           if(r.getStatus().equals(Status.DRAFT)) {
               res = r;
               break;
           }
       }
       
       printTestName("Test Cancel Reservation");
       
       if(res != null) {
           System.out.printf("Resevation to be updated: %s%n", res);
           manager.cancelReservation(res.getReservationNumber());
           System.out.println("After Cancel: \n" + manager.getReservation(res.getAccountNumber(), 
                   res.getReservationNumber()));
       } else {
           System.out.println("There is no Reservation in DRAFT STatus");
       }
    }
    
    /**
     * Function to print the Test Name. 
     * 
     * @param testName 
     */
    private static void printTestName(String testName) {
        System.out.printf("*****************************************************%n");
        System.out.printf("\t%s%n", testName);
        System.out.printf("*****************************************************%n");
        
    }
}
