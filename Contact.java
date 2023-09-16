public class Contact implements ContactInterface, Cloneable {
    private PersonalInfo person; // Personal information
    private Address address; // Current address
    private String phone; // Phone number
    private String email; // Email

    // private values = new String[] {"phone", "status", "first", "last", "email",
    // "street", "city", "state", "zip", "title", "department", "company", "label"};
    private String[] attrs = new String[] { "phone", "status", "first", "last", "email", "street", "city", "state",
            "zip" };

    public Contact clone()
    {
        try {
            Contact copy = (Contact)super.clone();
            copy.person = person.clone();
            copy.address = address.clone();
            return copy;
        }
        catch (CloneNotSupportedException e) {
            /* Shouldn't happen */
            return null;
        }
    }

    public String toString()
    {
        return String.format("\t%s:\tPhone: %s\n%s", person.toString(), phone, address.toString());
    }

    @Override
    public boolean equals(Object o)
    {
        boolean res = (this == o);
        if (!res && o instanceof Contact) {
            Contact other = (Contact)o;
            res = (
                other.person.equals(this.person) &&
                other.address.equals(this.address) &&
                other.phone.equals(this.phone) &&
                other.email.equals(this.email)
            );
        }
        return res;
    }

    @Override
    public boolean exists(String attribute) {
        for (String attr : attrs) {
            if (attribute.equals(attr)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        value = value.toLowerCase();
        boolean valueExists = false;
        switch (attribute) {
            case "phone":
                valueExists = phone.equals(value);
                break;
            case "status":
                valueExists = person.getStatus() == Status.fromString(value);
                break;
            case "first":
                valueExists = person.getFirst().equals(value);
                break;
            case "last":
                valueExists = person.getLast().equals(value);
                break;
            case "email":
                valueExists = email.equals(value);
                break;
            case "street":
                valueExists = address.getStreetAddress().equals(value);
                break;
            case "city":
                valueExists = address.getCity().equals(value);
                break;
            case "state":
                valueExists = address.getState().equals(value);
                break;
            case "zip":
                try {
                    valueExists = address.getZipCode() == Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    // do nothing
                }
                break;
            // case "title":
            // break;
            // case "department":
            // break;
            // case "company":
            // break;
            // case "label":
            // break;
            default:
                throw new IllegalArgumentException("attribute not specified");
        }
        return valueExists;
    }

    @Override
    public void setValue(String attribute, String value) throws IllegalArgumentException {
        switch (attribute) {
            case "phone":
                phone = value;
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
            // case "title":
            // break;
            // case "department":
            // break;
            // case "company":
            // break;
            // case "label":
            // break;
            default:
                throw new IllegalArgumentException("attribute not specified");
        }
    }
}

class Address implements Cloneable{
    private int zipCode;
    private String streetAddress;
    private String city;
    private String state;

    public String toString()
    {
        return String.format("\t%s\n\t%s, %s %d", streetAddress, city, state, zipCode);
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Address clone()
    {
        try {
            return (Address)super.clone();
        }
        catch (CloneNotSupportedException e) {
            /* This shouldn't happen */
            return null;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        boolean res = (this == o);
        if (!res && o instanceof Address) {
            Address other = (Address)o;
            res = (
                this.zipCode == other.zipCode &&
                this.streetAddress.equals(other.streetAddress) &&
                this.city.equals(other.city) &&
                this.state.equals(other.state)
            );
        }
        return res;
    }


    Address(int zipCode, String streetAddress, String city, String state) {
        this.zipCode = zipCode;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
    }
}
