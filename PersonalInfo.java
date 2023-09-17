public class PersonalInfo implements Cloneable {
    private String first; // First name
    private String last; // Last name
    private Status status; // Marital Status

    public PersonalInfo(String first, String last, String status) {
        this.first = first;
        this.last = last;
        this.status = Status.fromString(status);
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = Status.fromString(status);
    }

    public String toString() {
        return String.format("%s, %s: (%s)", first, last, status.toString());
    }

    public PersonalInfo clone() {
        try {
            return (PersonalInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            /* Shouldn't happen */
            return null;
        }
    }

    public boolean equals(Object o) {
        boolean res = (this == o);
        if (!res && o instanceof PersonalInfo) {
            PersonalInfo other = (PersonalInfo) o;
            // status is ignored because it is not included in the personal contact
            // list.
            res = (this.first.equals(other.first) &&
                    this.last.equals(other.last) &&
                    (this.status == Status.NA ||
                            other.status == Status.NA ||
                            this.status == other.status));
        }
        return res;
    }

}
