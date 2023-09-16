public class Table<T extends Contact> {
    private Node<T> head; // First record in the table
    private Node<T> tail; // Last record in the table
    private String title; // Label for the table

    public Table<T> clone()
    {
        Table<T> newTable = new Table();
        for (Node<T> i = head; i != null; i = i.next) {
            newTable.insert(i.data.clone());
        }
    }

    // Creates a new table comprised of nodes in this table, but not in table.
    Table<T> difference(Table<T> table) {
        Table<T> newTable = this.clone();
        Node<T> prev = null;
        for (Node<T> i = newTable.head; i != null; i = i.next) {
            for (Node<T> j = table.head; j != null; j = j.next) {
                if (i.data.equals(j.data)) {
                    if (i == newTable.head) {
                        newTable.head = i.next;
                    }
                    else {
                        prev.next = i.next;
                        i = prev;
                    }
                }
            }
            prev = i;
    }


    // Adds a new record to the end of the this table.
    void insert(T data) {
        if (head == null) {
            head = new Node(data, null);
            tail = head;
        }
        else {
            tail.next = new Node(data, null);
            tail = tail.next;
        }
    }

    // Creates a new table comprised of nodes having a value for a specific
    // attribute, created from both tables.
    Table<T> intersect(String attribute, String value, Table<T> table) {
        Table<T> newTable = new Table();
        boolean keep = false;
        for (Node<T> i = this.head; i != null; i = i.next) {
            if (i.data.hasValue(attribute, value)) {
                newTable.insert(i.data.clone());
            }
        }
        for (Node<T> i = table.head; i != null; i = i.next) {
            if (i.data.hasValue(attribute, value)) {
                keep = true;
                break;
            }
        }
        if (!keep) {
            newTable = new Table();
        }
        return newTable;

    }

    // Removes the first node matching whose attribute matches value.
    void remove(String attribute, String value) {
    }

    // Creates a new table comprised of nodes having a value for a specific
    // attribute.
    Table<T> select(String attribute, String value) {
    }

    // Creates a new table comprised of nodes that occur in either table(s). No
    // duplicates allowed.
    Table<T> union(Table<T> table) {

    }
}
