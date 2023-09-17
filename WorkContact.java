/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 * 
 *          Class that represents a Work Contact. Work contacts contain
 *          all the fields of a Contact and a title, company, and a department
 */

public class WorkContact extends Contact {
    private String title; // Job title
    private String company; // Company contact works for
    private String department; // Contact’s department

    /** valid attributes for a work contact */
    private static final String[] attrs = { "title", "company", "department" };

    /**
     * Creates a WorkContact object.
     * 
     * @param first      contact's first name
     * @param last       contact's last name
     * @param status     contact's marrital status (Valid are: MARRIED, SINGLE,
     *                   DIVORCED, WIDOWED)
     * @param street     contact's street address
     * @param city       contact's city name
     * @param state      contact's state name.
     * @param zip        contact's zip code. Must be exactly 5 digits long
     * @param phone      contact's phone number *Must be exactly MAX_PHONE_NUM
     *                   digits long*
     * @param email      contact's email string. No checks are required.
     * @param title      contact's job title
     * @param company    contact's company name for contact
     * @param department contact's department name
     * @throws IllegalStateException If zipcode or phone are not formatted correctly
     */
    public WorkContact(String first, String last, String status, String street, String city, String state, String zip,
            String phone, String email, String title, String company, String department) throws IllegalStateException {
        super(first, last, status, street, city, state, zip, phone, email);
        this.title = title;
        this.company = company;
        this.department = department;
    }

    /**
     * @param attribute a field you are checking on this contact.
     * @return boolean whether or not it exists on this WorkContact.
     */
    @Override
    public boolean exists(String attribute) {
        attribute = attribute.toLowerCase();
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
     * String representation of a WorkContact
     * 
     * @return String representation
     */
    public String toString() {
        return "Job Title: " + title + "\nCompany: " + company + "\nDepartment: " + department + '\n'
                + super.toString();
    }

    /**
     * Will compare two contacts as Contact, unless other contact is a WorkContact.
     * In that case, the contact will compare the fields of both WorkContact.
     * 
     * @param o other object to compare to
     * @return boolean whether or not this Work contact equals another contact.
     */
    public boolean equals(Object o) {
        boolean res = super.equals(o);
        if (res && o instanceof WorkContact) {
            WorkContact other = (WorkContact) o;
            res = (this.title.equals(other.title) &&
                    this.company.equals(other.company) &&
                    this.department.equals(other.department));
        }
        return res;
    }

    /**
     * deep clones the current address
     * 
     * @return a deep copy of the current Address
     */
    public WorkContact clone() {
        return (WorkContact) super.clone();
    }

    /**
     * @param attribute the string name of a field on this Contact class.
     * @param value     a specific value for that attribute you want to check.
     * @return boolean whether or not the field exists with the current value.
     * @throws IllegalArgumentException
     */
    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        attribute = attribute.toLowerCase();
        value = value.toLowerCase();
        boolean containsValue = false;
        try {
            containsValue = super.hasValue(attribute, value);
        } catch (IllegalArgumentException e) {
            switch (attribute) {
                case "title":
                    containsValue = title.toLowerCase().equals(value);
                    break;
                case "company":
                    containsValue = company.toLowerCase().equals(value);
                    break;
                case "department":
                    containsValue = department.toLowerCase().equals(value);
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
        attribute = attribute.toLowerCase();
        try {
            super.setValue(attribute, value);
        } catch (IllegalArgumentException e) {
            switch (attribute) {
                case "title":
                    title = value;
                    break;
                case "company":
                    company = value;
                    break;
                case "department":
                    department = value;
                    break;
                default:
                    throw e;
            }
        }
    }
}
