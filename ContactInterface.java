//Interface for checking and changing attributes of records
public interface ContactInterface {
    public final static int MAX_PHONE_NUM = 10;
      
    /**
    * Check to see if a record has an attribute. Should not be case sensitive.
    * @param attribute possible attribute within a record.
    * @return true if the value is contained in the object, false otherwise.
    */
    public boolean exists(String attribute);
       
    /**
    * Check to see if a record has an attribute containing a specific value. Should not be case sensitive.
    * @param attribute possible attribute within a record.
    * @param value desired value of the attribute.
    * @return true if the value is contained in the object, false otherwise.
    * @throws IllegalArgumentException if the attribute is invalid.
    */
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException;
        
    /**
    * Change the value of a specific attribute.
    * @param attribute possible attribute within a record.
    * @param value new value of the attribute.
    * @throws IllegalArgumentException if the attribute is invalid.
    */
    public void setValue(String attribute, String value) throws IllegalArgumentException;

    default public void checkPhone(String number) {
        if (number.length() != MAX_PHONE_NUM || !(number.chars().allMatch(ch -> Character.isDigit(ch))))
        throw new IllegalStateException("Invalid Phone number: " + number);
    }
}


