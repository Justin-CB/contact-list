public class WorkContact extends Contact {
    private String title;       // Job title
    private String company;     // Company contact works for
    private String department;  // Contact’s department

    private static final String[] attrs = {"title","company","department"};

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

    public String toString()
    {
        return "Job Title: " + title + "\nCompany: " + company + "\nDepartment: " + department + '\n' + super.toString();
    }

    public boolean equals(Object o)
    {
        boolean res = super.equals(o);
        if (res && o instanceof WorkContact) {
            WorkContact other = (WorkContact)o;
            res = (
                this.title.equals(other.title) &&
                this.company.equals(other.company) &&
                this.department.equals(other.department)
            );
        }
        return res;
    }

    public WorkContact clone()
    {
        return (WorkContact)super.clone();
    }

    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        boolean containsValue = false;
        try {
            containsValue = super.hasValue(attribute, value);
        } catch (IllegalArgumentException e) {
            value = value.toLowerCase();
            switch (attribute) {
                case "title":
                    containsValue = title.equals(value);
                    break;
                case "company":
                    containsValue = company.equals(value);
                    break;
                case "department":
                    containsValue = department.equals(value);
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
