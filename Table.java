public class Table<T extends Contact> {
    private Node<T> head; // First record in the table
    private Node<T> tail; // Last record in the table
    private String title; // Label for the table

    @SuppressWarnings("unchecked")
    public Table<T> clone() {
        Table<T> newTable = new Table<T>();
        for (Node<T> i = head; i != null; i = i.next) {
            newTable.insert((T) i.data.clone());
        }
        return newTable;
    }

    // Creates a new table comprised of nodes in this table, but not in table.
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
        return newTable;
    }

    // Adds a new record to the end of the this table.
    public void insert(T data) {
        if (head == null) {
            head = new Node<T>(data, null);
            tail = head;
        } else {
            tail.next = new Node<T>(data, null);
            tail = tail.next;
        }
    }

    public String toString() {
        String res = "";
        for (Node<T> i = head; i != null; i = i.next) {
            res += i.data;
            res += "\n--------------------------------------------------------------\n";
        }
        return res;
    }

    // Creates a new table comprised of nodes having a value for a specific
    // attribute, created from both tables.
    @SuppressWarnings("unchecked")
    public Table<T> intersect(String attribute, String value, Table<T> table) {
        Table<T> newTable = new Table<T>();
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
            newTable = new Table<T>();
        }
        return newTable;

    }

    // Removes the first node matching whose attribute matches value.
    public void remove(String attribute, String value) {
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

    // Creates a new table comprised of nodes having a value for a specific
    // attribute.
    @SuppressWarnings("unchecked")
    public Table<T> select(String attribute, String value) {
        Table<T> newTable = new Table<T>();
        for (Node<T> i = head; i != null; i = i.next) {
            if (i.data.hasValue(attribute, value)) {
                newTable.insert((T) i.data.clone());
            }
        }
        return newTable;
    }

    // Creates a new table comprised of nodes that occur in either table(s). No
    // duplicates allowed.
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
        return newTable;
    }
}
