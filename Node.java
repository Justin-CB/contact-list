public class Node<T> {
    Node<T> next;
    T data;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}