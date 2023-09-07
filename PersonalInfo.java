class PersonalInfo implements Cloneable {
    private String first;  // First name
    private String last;   // Last name
    private Status status; // Marital Status

    public PersonalInfo(String first, String last, String status)
    {
        this.first = first;
        this.last = last;
        this.status = Status.fromString(status);
    }

    public String toString()
    {
        return String.format("%s, %s: (%s)",first,last,status.toString());
    }

    /* Object's .clone() should work for this */

    private enum Status {
        MARRIED,
        SINGLE,
        DIVORCED,
        WIDOWED,
        NA;
        /**
         * The toString method for Status
         * @return the string representation of the marital status
         */
        public String toString()
        {
            if (this == Status.NA) {
                return "N/A";
            }
            else {
                /* Leave the 1st character capital & make the rest lower *
                 * case i.e. change MARRIED into Married.                */
            return name().substring(0, 1) +
                   name().substring(1).toLowerCase();
            }
        }
        public static Status fromString(String str)
        {
            for (int i = 0; i < Status.values().length; i++) {
                if (str.toUpperCase().equals(Status.values()[i].name())) {
                    return Status.values()[i];
                }
            }
            return Status.NA;
        }
    }
}
