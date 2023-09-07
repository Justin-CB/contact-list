public class Table<T extends Contact> {
    private Node head;     // First record in the table
    private Node tail;     // Last record in the table
    private String title;  // Label for the table

    Table<T> difference(Table<T> table)
    //Creates a new table comprised of nodes in this table, but not in table.
    
    void insert(T data)
    //Adds a new record to the end of the this table.
    
    Table<T> intersect(String attribute, String value, Table<T> table)
    //Creates a new table comprised of nodes having a value for a specific attribute, created from both tables.
    
    void remove(String attribute, String value)
    //Removes the first node matching whose attribute matches value.
    
    Table<T> select(String attribute, String value)
    //Creates a new table comprised of nodes having a value for a specific attribute.
    
    Table<T> union(Table<T> table)
    //Creates a new table comprised of nodes that occur in either table(s). No duplicates allowed.
}
