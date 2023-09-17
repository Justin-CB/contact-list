/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 * 
 *          Marital status of a contact
 */
public enum Status {
    MARRIED,
    SINGLE,
    DIVORCED,
    WIDOWED,
    NA;

    /**
     * The toString method for Status
     * 
     * @return the string representation of the marital status
     */
    public String toString() {
        if (this == Status.NA) {
            return "N/A";
        } else {
            /*
             * Leave the 1st character capital & make the rest lower *
             * case i.e. change MARRIED into Married.
             */
            return name().substring(0, 1) +
                    name().substring(1).toLowerCase();
        }
    }

    /**
     * returns a status from a string
     * 
     * @param str non-case sensitive string
     * @return a Status enum corresponding to str or Status.NA
     */
    public static Status fromString(String str) {
        Status res;
        try {
            res = Status.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            res = Status.NA;
        }
        return res;
    }
}
