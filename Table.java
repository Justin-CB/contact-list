public class Table<T extends Contact> {
    private Node<T> head; // First record in the table
    private Node tail; // Last record in the table
    private String title; // Label for the table

    // Creates a new table comprised of nodes in this table, but not in table.
    Table<T> difference(Table<T> table) {
    }

    // Adds a new record to the end of the this table.
    void insert(T data) {
    }

    // Creates a new table comprised of nodes having a value for a specific
    // attribute, created from both tables.
    Table<T> intersect(String attribute, String value, Table<T> table) {
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
