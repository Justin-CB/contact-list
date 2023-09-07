class PersonalInfo {
    private String fname;
    private String lname;
    private MaritalStatus mstat;

    public PersonalInfo(String fname, String lname, String mstat)
    {
        this.fname = fname;
        this.lname = lname;
        this.mstat = MaritalStatus.fromString(mstat);
    }

    public String toString()
    {
        return String.format("%s, %s: (%s)",fname,lname,mstat.toString());
    }

    private enum MaritalStatus {
        MARRIED,
        SINGLE,
        DIVORCED,
        WIDOWED,
        NA;
        /**
         * The toString method for MaritalStatus
         * @return the string representation of the marital status
         */
        public String toString()
        {
            if (this == MaritalStatus.NA) {
                return "N/A";
            }
            else {
                /* Leave the 1st character capital & make the rest lower *
                 * case i.e. change MARRIED into Married.                */
            return name().substring(0, 1) +
                   name().substring(1).toLowerCase();
            }
        }
        public static MaritalStatus fromString(String str)
        {
            for (int i = 0; i < MaritalStatus.values().length; i++) {
                if (str.toUpperCase().equals(MaritalStatus.values()[i].name())) {
                    return MaritalStatus.values()[i];
                }
            }
            return MaritalStatus.NA;
        }
    }
}
