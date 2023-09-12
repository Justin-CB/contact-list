public class PersonalInfo implements Cloneable {
    private String first; // First name
    private String last; // Last name
    private Status status; // Marital Status

    public PersonalInfo(String first, String last, String status) {
        this.first = first;
        this.last = last;
        this.status = Status.fromString(status);
    }

    public String toString() {
        return String.format("%s, %s: (%s)", first, last, status.toString());
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
}
