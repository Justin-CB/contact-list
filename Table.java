/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 *          Represents a list of Contact covariants and has operations to
 *          combine tables based loosely on set operations.
 */
public class Table<T extends Contact> {
    private Node<T> head; // First record in the table
    private Node<T> tail; // Last record in the table
    private String title; // Label for the table

    /**
     * Creates a Table
     * 
     * @param title title for the table for printing
     */
    public Table(String title) {
        this.title = title;
        this.head = null;
        this.tail = null;
    }

    /**
     * getter for title
     * 
     * @return the Table's title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Creates a deep copy of the current Table.
     * Must suppress unchecked exceptions or else java won't let us clone the
     * data inside of the linked Lists due to generics.
     */
    @SuppressWarnings("unchecked")
    public Table<T> clone() {
        Table<T> newTable = new Table<T>(this.title);
        for (Node<T> i = head; i != null; i = i.next) {
            newTable.insert((T) i.data.clone());
        }
        return newTable;
    }

    /**
     * Creates a new table comprised of nodes in this table, but not in table.
     * 
     * @param table other table which elements are removed.
     * @return a new Table that has the subtraction performed
     */
    public Table<T> difference(Table<T> table) {
        Table<T> newTable = this.clone();
        Node<T> prev = null;
        for (Node<T> i = newTable.head; i != null; i = i.next) {
            for (Node<T> j = table.head; j != null; j = j.next) {
                if (i.data.equals(j.data)) {
                    if (i == newTable.head) {
                        newTable.head = i.next;
                    } else {
                        prev.next = i.next;
                        i = prev;
                    }
                }
            }
            prev = i;
        }
        newTable.title = this.title + ", " + table.title;
        return newTable;
    }

    /**
     * Adds a new record to the end of the this table.
     * 
     * @param data the data to be inserted into the Table.
     */
    public void insert(T data) {
        if (head == null) {
            head = new Node<T>(data, null);
            tail = head;
        } else {
            tail.next = new Node<T>(data, null);
            tail = tail.next;
        }
    }

    /**
     * return a string representation of the Table, alongside the banner
     * 
     * @return string represntation of the Table.
     */
    public String toString() {
        String res = "===========================" + this.title + "===========================\n";
        for (Node<T> i = head; i != null; i = i.next) {
            res += i.data;
            res += "\n--------------------------------------------------------------\n";
        }
        res += "===========================" + this.title + "===========================\n";
        return res;
    }

    /**
     * Creates a new table comprised of nodes having a value for a specific
     * attribute, created from both tables.
     * 
     * @param attribute the attribute to match in both tables
     * @param value     the value to match in both tables
     * @param table     the other Table to perform the intersection
     * @return a new Table with the intersection performed
     */
    @SuppressWarnings("unchecked")
    public Table<T> intersect(String attribute, String value, Table<T> table) throws IllegalArgumentException {
        Table<T> newTable = new Table<T>(this.title + ", " + table.title);
        boolean keep = false;
        for (Node<T> i = this.head; i != null; i = i.next) {
            if (i.data.hasValue(attribute, value)) {
                newTable.insert((T) i.data.clone());
            }
        }
        for (Node<T> i = table.head; i != null; i = i.next) {
            if (i.data.hasValue(attribute, value)) {
                keep = true;
                break;
            }
        }
        if (!keep) {
            newTable = new Table<T>(this.title + ", " + table.title);
        }
        return newTable;

    }

    /**
     * Removes the first node matching whose attribute matches value.
     * 
     * @param attribute the attribute to match in both tables
     * @param value     the value to match in both tables
     */
    public void remove(String attribute, String value) throws IllegalArgumentException {
        Node<T> prev = null;
        for (Node<T> i = this.head; i != null; i = i.next) {
            if (i.data.hasValue(attribute, value)) {
                if (i == this.head) {
                    this.head = i.next;
                    break;
                } else {
                    prev.next = i.next;
                    i = prev;
                    break;
                }
            }
            prev = i;
        }
    }

    /**
     * Creates a new table comprised of nodes having a value for a specific
     * attribute.
     * 
     * @param attribute the attribute to match in both tables
     * @param value     the value to match in both tables
     */
    @SuppressWarnings("unchecked")
    public Table<T> select(String attribute, String value) throws IllegalArgumentException {
        Table<T> newTable = new Table<T>(this.title);
        for (Node<T> i = head; i != null; i = i.next) {
            if (i.data.hasValue(attribute, value)) {
                newTable.insert((T) i.data.clone());
            }
        }
        return newTable;
    }

    /**
     * Creates a new table comprised of nodes that occur in either table(s). No
     * duplicates allowed.
     * 
     * @param table the other Table to perform the union
     * @return a new Table with the union performed
     */
    @SuppressWarnings("unchecked")
    public Table<T> union(Table<T> table) {
        Table<T> newTable = this.clone();
        for (Node<T> j = table.head; j != null; j = j.next) {
            boolean insert = true;
            for (Node<T> i = newTable.head; i != null; i = i.next) {
                if (j.data.equals(i.data)) {
                    insert = false;
                }
            }
            if (insert) {
                newTable.insert((T) j.data.clone());
            }
        }
        newTable.title = this.title + ", " + table.title;
        return newTable;
    }
}
