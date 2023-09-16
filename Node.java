public class Node<T> {
    public Node<T> next;
    public T data;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
