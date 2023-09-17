/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 * 
 *          Node for creating a linked list
 */
public class Node<T> {
    /** The next node for a linked list */
    public Node<T> next;
    /** the data being stored on a Node */
    public T data;

    /**
     * Creates a new node.
     * 
     * @param data the data of this node.
     * @param next the next node.
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
