public class PersonalContact extends Contact {
    private Label label; // Label for grouping personal contacts

    // private values = new String[] {"phone", "status", "first", "last", "email",
    // "street", "city", "state", "zip", "title", "department", "company", "label"};
    private static final String[] attrs = { "label" };

    public PersonalContact(String first, String last, String city, String state, String zip, String phone, String email, String label) throws IllegalStateException
    {
        super(first, last, "NA", city, state, zip, phone, email);
        this.label = Label.fromString(label);
    }

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

    public PersonalContact clone()
    {
        return (PersonalContact)super.clone();
    }

    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        boolean containsValue = false;
        try {
            containsValue = super.hasValue(attribute, value);
        } catch (IllegalArgumentException e) {
            value = value.toLowerCase();
            switch (attribute) {
                case "label":
                    containsValue = label == label.fromString(value);
                    break;
                default:
                    throw e;
            }
        }
        return containsValue;
    }

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
    public String toString()
    {
        return "Category: " + label + '\n' + super.toString();
    }

    /* Use super.equals b/c we ignore the label & it's the only field *
     * that's additional to the superclass.                           */

    private enum Label {
        SISTER,
        BROTHER,
        DAD,
        MOM,
        FRIEND,
        OTHER;
        
        /* Just use enum's default toString */

        public static Label fromString(String str)
        {
            Label res;
            try {
                res = Label.valueOf(str.toUpperCase());
            }
            catch (IllegalArgumentException e) {
                res = Label.OTHER;
            }
            return res;
        }
    }
}
