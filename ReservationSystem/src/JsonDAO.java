
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.GregorianCalendar;

/**
 * Implementation of the concrete class for the JSON file IO. 
 * 
 * @author Programmer
 */
public class JsonDAO implements FileIO {
    
    /**
     * Function to serialize all the account to a file. The actual logic or file type
     * will be the decision of the concrete class implementing this interface. 
     * 
     * @param accounts accounts to be serialized
     * @param path file path
     */
    public void serializeAccounts(List<Account> accounts, String path) {
        for(Account account: accounts) {
            serializeAccount(account, path);
        }
    }
    
    /**
     * Function to serialize an Individual Account. 
     * 
     * @param account to be serialized as JSOn
     * @param path to the parent directory
     */
    public void serializeAccount(Account account, String path) {
        try {
            
            //Address address = new Address("151 Church St.", "New York", "NY", "10001", "USA");
            //Account account = new Account("A123456789", address, "john@hotmail.com", "(212) 123-1234");
            //account.addReservation("abc");
            
            // Save all the accounts to their respective files in respective directories
            String dirPath = path + account.getAccountNumber() + "/"; 
            String fileName = dirPath + "acc-" + account.getAccountNumber() + ".json";

            // Manage directories
            File theDir = new File(dirPath);
            if(!theDir.exists()) {
                theDir.mkdirs();
            }

            PrintWriter out = new PrintWriter(fileName);
            Gson gson = new Gson();
            String json = gson.toJson(account);
            out.println(json);
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Function to load an de-serialize accounts in a list of accounts from a 
     * given path pointing to a directory of accounts. The implementing class
     * will abide by the contract of this interface. 
     * 
     * @param path directory path to read accounts
     * @return list of accounts read
     * @throws IllegalLoadException
     */
    public List<Account> deserializeAccounts(String path) throws IllegalLoadException {
        List<Account> accounts = new ArrayList<Account>();
        
        try {
            File dir = new File(path); 
            File list [] = dir.listFiles(); 
            
            for(File file: list) {
                if(file.isDirectory()) {
                    String fileName = file.getAbsolutePath() + "/acc-" + file.getName() + ".json";
                    Account account = this.deserializeAccount(fileName);
                    
                    if(account == null) {
                        throw new Exception(); // error
                    } else {
                        accounts.add(account);
                    }
                }
            }
            
        } catch(Exception e) {
            throw new IllegalLoadException("ERROR: Loading Account objects");
        }
        
        return accounts; 
    }
    
    /**
     * Function to de-serialize Account
     * 
     * @param path to read the account object
     * @return account object
     * @throws IllegalLoadException if not found 
     */
    public Account deserializeAccount (String path) 
        throws IllegalLoadException{
        Account account = null;
            
        try {
            String data = Files.readString(Paths.get(path));
            Gson gson = new Gson();
            account = gson.fromJson(data, Account.class);
        } catch(Exception e) {
            throw new IllegalLoadException("Account file for account object not found");
        }
        
        return account;
    }
    
    /**
     * Function to serialize all the Reservations in the system. 
     * 
     * @param reservations list of reservations
     * @param path of parent directory
     */
    public void serializeReservations(List<Reservation> reservations, String path) {
        for(Reservation res: reservations) {
            this.serializeReservation(res, path);
        }
    }
    
    /**
     * Function to list all the reservations of all the accounts from the file system. 
     * 
     * @param accounts for reservations
     * 
     * @return list of reservations 
     * @throws IllegalLoadException
     */
    public List<Reservation> deserializeReservations(List<Account> accounts, String path)
        throws IllegalLoadException {
        
        List<Reservation> reservations = new ArrayList<Reservation>();
        
        try {
            for(Account account: accounts) {
                for(String res: account.getListOfReservations()) {
                    String dirPath = path + account.getAccountNumber() + "/";
                    File dir = new File(dirPath); 
                    File files [] = dir.listFiles();

                    for(File file: files) {
                        if(file.getName().startsWith("res")) {
                            Reservation reservation = this.deserializeReservation(file.getAbsolutePath()); 
                            if(reservation == null) {
                                System.out.println("HERE");
                                throw new Exception();
                            } else {
                                reservations.add(reservation);
                            }
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new IllegalLoadException("ERROR: Loading reservations from the file system");
            
        }
        
        return reservations;
    }
    
    /**
     * Function to de-serialize a Reservation
     * @param fileName to read
     * @return reservation object 
     */
    public Reservation deserializeReservation(String fileName) 
        throws IllegalLoadException {
        Reservation res = null;
        try {
            
            String json = Files.readString(Paths.get(fileName));
            
            if(fileName.contains("res-C")) {
                res = this.getReservationSerializer().fromJson(json, CabinReservation.class);
            } else if(fileName.contains("res-H")) {
                res = this.getReservationSerializer().fromJson(json, HotelReservation.class);
            } else {
                res = this.getReservationSerializer().fromJson(json, HouseReservation.class);
            } 
            
        } catch(Exception e) {
            e.printStackTrace();
            throw new IllegalLoadException("ERROR: Loading reservation from the file system");
        }
        
        return res;
    }
    
    /**
     * Function to serialize a Reservation in the system. 
     * 
     * @param reservation reservation to serialize
     * @param path of parent directory
     */
    public void serializeReservation(Reservation reservation, String path) {
        try {
            // Save all the accounts to their respective files in respective directories
            String dirPath = path + reservation.getAccountNumber() + "/"; 
            String fileName = dirPath + "res-"; 
            
            if(reservation instanceof HouseReservation) {
                  fileName += "O" + reservation.getReservationNumber() + ".json";
            } else if(reservation instanceof CabinReservation) {
                  fileName += "C" + reservation.getReservationNumber() + ".json";
            } else if(reservation instanceof HotelReservation) {
                  fileName += "H" + reservation.getReservationNumber() + ".json";
            }

            PrintWriter out = new PrintWriter(fileName);
            Gson gson = new Gson();
            String json = gson.toJson(reservation);
            out.println(json);
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * JSONSerializer for the Reservation object including sub classes. 
     * 
     * @return JSONSerializer object  
     */
    public Gson getReservationSerializer() {
	    RuntimeTypeAdapterFactory<Reservation> resAdapter = RuntimeTypeAdapterFactory.of(Reservation.class, Reservation.class.getName())
	            .registerSubtype(HouseReservation.class, HouseReservation.class.getName())
	            .registerSubtype(HotelReservation.class, HotelReservation.class.getName())
	            .registerSubtype(CabinReservation.class, CabinReservation.class.getName());
	    
	    return new GsonBuilder().registerTypeAdapterFactory(resAdapter).create();
    }
}
