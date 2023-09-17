/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 * 
 *          Class that represents the personal information for a contact.
 */
public class PersonalInfo implements Cloneable {
    private String first; // First name
    private String last; // Last name
    private Status status; // Marital Status

    /**
     * Creates a PersonalInfo object.
     * 
     * @param first  contact's first name
     * @param last   contact's last name
     * @param status contact's marrital status
     */
    public PersonalInfo(String first, String last, String status) {
        this.first = first;
        this.last = last;
        this.status = Status.fromString(status);
    }

    /**
     * getter for first name
     * 
     * @return contact's first name
     */
    public String getFirst() {
        return first;
    }

    /**
     * setter for first name
     * 
     * @param first new first name value
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * getter for last name
     * 
     * @return contact's last name
     */
    public String getLast() {
        return last;
    }

    /**
     * setter for last name
     * 
     * @param last new last name
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * getter for status
     * 
     * @return contact's marrital status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * setter for status
     * 
     * @param status the new marrital status (enum)
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * setter for status
     * 
     * @param status the new marrital status (string)
     */
    public void setStatus(String status) {
        this.status = Status.fromString(status);
    }

    /**
     * returns the string representation of a PersonalInfo object. Contains
     * first name, last name, and marrital status
     * 
     * @return string containing Containing first name, last name, and marrital
     *         status
     */
    public String toString() {
        return String.format("%s, %s: (%s)", first, last, status.toString());
    }

    /**
     * Deep Clones the PersonalInfo object
     * 
     * @return a deep copy of the current PersonalInfo object
     */
    public PersonalInfo clone() {
        try {
            return (PersonalInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            /* Shouldn't happen */
            return null;
        }
    }

    /**
     * Determines if a PersonalInfo object is equal to another object
     * 
     * @param o other object to be compared to
     * @return whether or not the two objects have the same fields with the same
     *         values
     */
    public boolean equals(Object o) {
        boolean res = (this == o);
        if (!res && o instanceof PersonalInfo) {
            PersonalInfo other = (PersonalInfo) o;
            // Ignore status whenever it is NA because that is what the output
            // does
            res = (this.first.equals(other.first) &&
                    this.last.equals(other.last) &&
                    (this.status == Status.NA ||
                            other.status == Status.NA ||
                            this.status == other.status));
        }
        return res;
    }

}
