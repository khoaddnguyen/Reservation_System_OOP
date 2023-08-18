public class Address {

    /** Street Address */
    private String street;

    /** City of this address */
    private String city;

    /** State */
    private String state;

    /** zip code */
    private String zip;

    /** Country of this address */
    private String country;

    /**
     * Function to create an Address object from the given parameter values. 
     * 
     * @param street of Address Object
     * @param city of Address Object
     * @param state of Address Object
     * @param zip of Address Object
     * @param country of Address Object 
     */
    public Address(String street, String city, String state, String zip, String country) {
        /**
         * Validate values are present not empty or null
         * Initialize the class instance fields with corresponding parameter values.
         */
        
        this.setStreet(street);
        this.setCity(city);
        this.setState(state);
        this.setZip(zip);
        this.setCountry(country);
    }

    /**
     * Function to get the Street Address of this Address Object.
     * 
     * @return street address of this Address Object. 
     */
    public String getStreet() {
        return street;
    }

    /**
     * Function to set or update the Street Address of this Address Object.
     * 
     * @param street address of this Address Object. 
     */
    public void setStreet(String street) {
        
        if(street == null) {
            throw new IllegalArgumentException("The Street Address must not be empty or null");
        }
        
        this.street = street;
    }

    /**
     * Function to get the Name of the city of this Address Object.
     * 
     * @return city of this Address Object.
     */
    public String getCity() {
        return city;
    }

    /**
     * Function to set or update the City name of this Address Object.
     * 
     * @param city of this Address Object. 
     */
    public void setCity(String city) {
        
        if(city == null) {
            throw new IllegalArgumentException("The city Name must not be empty or null");
        }
        
        this.city = city;
    }

    /**
     * Function to get the State of this Address Object.
     * 
     * @return state of this Address Object. 
     */
    public String getState() {
        return state;
    }

    /**
     * Function to set or update the state of this Address Object.
     * 
     * @param state of this Address Object. 
     */
    public void setState(String state) {
        
        if(state == null) {
            throw new IllegalArgumentException("The State must not be empty or null");
        }
        
        this.state = state;
    }

    /**
     * Function to get the Zip code of this Address Object.
     * 
     * @return zip code of this Address Object. 
     */
    public String getZip() {
        return zip;
    }

    /**
     * Function to set or update the Zip code of this Address Object.
     * 
     * @param zip of this Address Object. 
     */
    public void setZip(String zip) {
        
        if(zip == null) {
            throw new IllegalArgumentException("The Zip Code must not be empty or null");
        }
        
        this.zip = zip;
    }

    /**
     * Function to get the country of this Address Object.
     * 
     * @return country of this Address Object.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Function to set or update the Country of this Address Object.
     * 
     * @param country of this Address Object. 
     */
    public void setCountry(String country) {
        
        if(country == null) {
            throw new IllegalArgumentException("The country Name must not be empty or null");
        }
        
        this.country = country;
    }
    
    
    /**
     * Create clone of this object. 
     * 
     * @return new deep copy object 
     */
    public Object clone() {
        /**
         * return new Address(street, city, state, zip, country)
         */
        return new Address(street, city, state, zip, country);
    }

    public String toString() {
        /**
         * return {
         *     "street": street,
         *     "city": city,
         *     "state", state,
         *     "zip": zip,
         *     "country": country
         * }
         */
        String output = "{\n"
                + "\"mailingAddress\": {\n" +
                   "\"street\": \"street\",\n" +
                   "\"state\": \"state\",\n" +
                   "\"zip\", \"zip\",\n" +
                   "\"country\": \"country\"\n" +
               "}";
        return output;
    }
}
