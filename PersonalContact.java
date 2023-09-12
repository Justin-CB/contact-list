public class PersonalContact extends Contact {
    private Label label; // Label for grouping personal contacts

    // private values = new String[] {"phone", "status", "first", "last", "email",
    // "street", "city", "state", "zip", "title", "department", "company", "label"};
    private String[] attrs = new String[] { "label" };

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

    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        boolean containsValue = false;
        try {
            containsValue = super.hasValue(attribute, value);
        } catch (IllegalArgumentException e) {
            value = value.toLowerCase();
            switch (attribute) {
                case "label":
                    // TODO This does not make sense because I don't know what a
                    // label is
                    containsValue = label.equals(value);
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
                    // TODO This also does not make sense because I don't know
                    // what a label is
                    label = new Label(value);
                default:
                    throw e;
            }
        }
    }
}
