/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 * 
 *          Contact class used for representing info in a table for the
 *          ContactList Program.
 */

public class Contact implements ContactInterface, Cloneable {
    private PersonalInfo person; // Personal information
    private Address address; // Current address
    private String phone; // Phone number
    private String email; // Email

    /** The valid attributes of a contact */
    private final static String[] attrs = new String[] { "phone", "status", "first", "last", "email", "street", "city",
            "state", "zip" };

    /**
     * Class that reperesents a simple contact
     * 
     * @param first  contact's First name
     * @param last   contact's last name
     * @param status contact's marrital status
     * @param street contact's street address
     * @param city   contact's resident city
     * @param state  contact's resident state
     * @param zip    contact's zip code. *Must be 5 digits long*
     * @param phone  contact's phone number *Must be exactly MAX_PHONE_NUM digits
     *               long*
     * @param email  contact's email string. No checks are required.
     * @throws IllegalStateException If zipcode or phone are not formatted correctly
     */
    public Contact(String first, String last, String status, String street, String city, String state, String zip,
            String phone, String email) throws IllegalStateException {
        this.person = new PersonalInfo(first, last, status);
        this.address = new Address(zip, street, city, state);
        this.checkPhone(phone);
        this.phone = phone;
        this.email = email;
    }

    /**
     * Creates a deep clone of the current Contact.
     * 
     * @return a deep copy of the Contact.
     */
    public Contact clone() {
        try {
            Contact copy = (Contact) super.clone();
            copy.person = person.clone();
            copy.address = address.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            /* Shouldn't happen */
            return null;
        }
    }

    /**
     * String representation of a Contact
     * 
     * @return String representation
     */
    public String toString() {
        return String.format("\t%s:\tPhone: %s\n%s", person.toString(), phone, address.toString());
    }

    /**
     * @param o other object to compare to
     * @return boolean whether or not this contact equals another contact.
     */
    @Override
    public boolean equals(Object o) {
        boolean res = (this == o);
        if (!res && o instanceof Contact) {
            Contact other = (Contact) o;
            res = (other.person.equals(this.person) &&
                    other.address.equals(this.address) &&
                    other.phone.equals(this.phone) &&
                    other.email.equals(this.email));
        }
        return res;
    }

    /**
     * @param attribute the string name of a field on this Contact class.
     * @return boolean whether or not the field exists.
     */
    @Override
    public boolean exists(String attribute) {
        attribute = attribute.toLowerCase();
        for (String attr : attrs) {
            if (attribute.equals(attr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param attribute the string name of a field on this Contact class.
     * @param value     a specific value for that attribute you want to check.
     * @return boolean whether or not the field exists with the current value.
     * @throws IllegalArgumentException
     */
    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        value = value.toLowerCase();
        attribute = attribute.toLowerCase();
        boolean valueExists = false;
        switch (attribute) {
            case "phone":
                valueExists = phone.equals(value);
                break;
            case "status":
                valueExists = person.getStatus() == Status.fromString(value);
                break;
            case "first":
                valueExists = person.getFirst().toLowerCase().equals(value);
                break;
            case "last":
                valueExists = person.getLast().toLowerCase().equals(value);
                break;
            case "email":
                valueExists = email.toLowerCase().equals(value);
                break;
            case "street":
                valueExists = address.getStreetAddress().toLowerCase().equals(value);
                break;
            case "city":
                valueExists = address.getCity().toLowerCase().equals(value);
                break;
            case "state":
                valueExists = address.getState().toLowerCase().equals(value);
                break;
            case "zip":
                try {
                    valueExists = address.getZipCode() == Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    // do nothing
                }
                break;
            default:
                throw new IllegalArgumentException("attribute not specified");
        }
        return valueExists;
    }

    /**
     * @param attribute the string name of a field on this Contact class.
     * @param value     The value you want to set the attribute to
     * @throws IllegalArgumentException throws if attribute is not a field of the
     *                                  contact.
     */
    @Override
    public void setValue(String attribute, String value) throws IllegalArgumentException {
        attribute = attribute.toLowerCase();
        switch (attribute) {
            case "phone":
                try {
                    this.checkPhone(value);
                    phone = value;
                } catch (IllegalStateException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
                break;
            case "status":
                person.setStatus(Status.valueOf(value));
                break;
            case "first":
                person.setFirst(value);
                break;
            case "last":
                person.setLast(value);
                break;
            case "email":
                email = value;
                break;
            case "street":
                address.setStreetAddress(value);
                break;
            case "city":
                address.setCity(value);
                break;
            case "state":
                address.setState(value);
                break;
            case "zip":
                if (value.length() != 5) {
                    throw new IllegalArgumentException("zip code must be 5 digits long");
                }
                address.setZipCode(Integer.parseInt(value));
                break;
            default:
                throw new IllegalArgumentException("attribute not specified");
        }
    }
}

class Address implements Cloneable {
    private int zipCode;
    private String streetAddress;
    private String city;
    private String state;

    /**
     * Creates an Address.
     * 
     * @param zipCode       Zip code must be 5 digits long.
     * @param streetAddress the street address.
     * @param city          the city.
     * @param state         the state.
     */
    Address(String zipCode, String streetAddress, String city, String state) {
        if (zipCode.length() != 5) {
            throw new IllegalStateException("Zip Code length must be 5");
        }
        try {
            this.zipCode = Integer.parseInt(zipCode);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(e.getMessage());
        }
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
    }

    /**
     * String representatino of an Address
     * 
     * @return the string representation of an address
     */
    @Override
    public String toString() {
        return String.format("\t%s\n\t%s, %s %05d", streetAddress, city, state, zipCode);
    }

    /**
     * getter for zipCode
     * 
     * @return zipdocde
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * setter for zipCode.
     * 
     * @param zipCode zipCode must be less than 5 digits long
     */
    public void setZipCode(int zipCode) {
        if (zipCode > 99999) {
            throw new IllegalArgumentException("Zip code must have at most 5 non-zero digits");
        }
        this.zipCode = zipCode;
    }

    /**
     * getter for streetAddress
     * 
     * @return the streed address.
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * setter for the street address
     * 
     * @param streetAddress the street address.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * getter for the city
     * 
     * @return the city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * setter for the city
     * 
     * @param city the city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * getter for the state
     * 
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * setter for the state
     * 
     * @param state the new state name.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * deep clones the current address
     * 
     * @return a deep copy of the current Address
     */
    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            /* This shouldn't happen */
            return null;
        }
    }

    /**
     * compares two addresses
     * 
     * @param o the other object to be compared to.
     * 
     * @return boolean whether the two objects are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        boolean res = (this == o);
        if (!res && o instanceof Address) {
            Address other = (Address) o;
            res = (this.zipCode == other.zipCode &&
                    this.streetAddress.equals(other.streetAddress) &&
                    this.city.equals(other.city) &&
                    this.state.equals(other.state));
        }
        return res;
    }
}
