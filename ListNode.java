package labs.java_lab_1;

import java.util.Objects;

/**
 * Represents a node in a linked list structure.
 *
 * @param <T> the type of data stored in the node
 */
public class ListNode<T> {
    /**
     * The data stored in this node.
     */
    T data;
    
    /**
     * Reference to the next node in the list.
     */
    ListNode<T> next;
    
    /**
     * Constructs a new ListNode with the specified data.
     *
     * @param data the data to be stored in this node
     */
    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Returns a string representation of the node's data.
     *
     * @return a string representation of the node's data
     */
    @Override
    public String toString() {
        return data.toString();
    }
    
    /**
     * Indicates whether some other object is "equal to" this one comparing by data contained in node.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListNode<?>)) return false;
        ListNode<?> node = (ListNode<?>) o;
        return Objects.equals(data, node.data);
    }
}