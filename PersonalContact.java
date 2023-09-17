/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 * 
 *          Class that represents a Personal Contact. Personal contacts contain
 *          all the fields of a Contact, plus a label for grouping
 */
public class PersonalContact extends Contact {
    private Label label; // Label for grouping personal contacts

    /** valid attributes for a Personal Contact */
    private static final String[] attrs = { "label" };

    /**
     * Creates a PersonalContact object. Does not contain a status since the
     * csv input does not contain statuses.
     * 
     * @param first  contact's first name
     * @param last   contact's last name
     * @param status contact's marrital status (Valid are: MARRIED, SINGLE,
     *               DIVORCED, WIDOWED)
     * @param street contact's street address
     * @param city   contact's city name
     * @param state  contact's state name.
     * @param zip    contact's zip code. Must be exactly 5 digits long
     * @param phone  contact's phone number *Must be exactly MAX_PHONE_NUM
     *               digits long*
     * @param email  contact's email string. No checks are required.
     * @throws IllegalStateException If zipcode or phone are not formatted correctly
     */
    public PersonalContact(String first, String last, String street, String city, String state, String zip,
            String phone, String email, String label) throws IllegalStateException {
        super(first, last, "NA", street, city, state, zip, phone, email);
        this.label = Label.fromString(label);
    }

    /**
     * @param attribute the string name of a field on this Contact class.
     * @return boolean whether or not the field exists.
     */
    @Override
    public boolean exists(String attribute) {
        if (super.exists(attribute)) {
            return true;
        }

        for (String attr : attrs) {
            if (attribute.equals(attr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * deep clones the current PersonalContact
     * 
     * @return a deep copy of the current PersonalContact
     */
    public PersonalContact clone() {
        return (PersonalContact) super.clone();
    }

    /**
     * @param attribute the string name of a field on this Contact class.
     * @param value     a specific value for that attribute you want to check.
     * @return boolean whether or not the field exists with the current value.
     * @throws IllegalArgumentException
     */
    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        boolean containsValue = false;
        try {
            containsValue = super.hasValue(attribute, value);
        } catch (IllegalArgumentException e) {
            value = value.toLowerCase();
            switch (attribute) {
                case "label":
                    containsValue = label == Label.fromString(value);
                    break;
                default:
                    throw e;
            }
        }
        return containsValue;
    }

    /**
     * @param attribute the string name of a field on this Contact class.
     * @param value     The value you want to set the attribute to
     * @throws IllegalArgumentException throws if attribute is not a field of the
     *                                  contact.
     */
    @Override
    public void setValue(String attribute, String value) throws IllegalArgumentException {
        try {
            super.setValue(attribute, value);
        } catch (IllegalArgumentException e) {
            value = value.toLowerCase();
            switch (attribute) {
                case "label":
                    label = Label.fromString(value);
                    break;
                default:
                    throw e;
            }
        }
    }

    /**
     * String representatino of an PersonalContact
     * 
     * @return the string representation of an PersonalContact
     */
    public String toString() {
        return "Category: " + label + '\n' + super.toString();
    }

    /*
     * Use super.equals b/c we ignore the label & it's the only field *
     * that's additional to the superclass.
     */

    private enum Label {
        SISTER,
        BROTHER,
        DAD,
        MOM,
        FRIEND,
        OTHER;

        /**
         * Converts string into a label
         * 
         * @param str label represenation of a string
         * @return label version of string or OTHER if not valid
         */
        public static Label fromString(String str) {
            /* Just use enum's default toString */
            Label res;
            try {
                res = Label.valueOf(str.toUpperCase());
            } catch (IllegalArgumentException e) {
                res = Label.OTHER;
            }
            return res;
        }
    }
}
