package labs.java_lab_1;

/**
 * CustomLinkedList represents a generic container organized by nodes, capable of holding elements of the same type.
 *
 * @param <T> the type of elements contained in the CustomLinkedList
 */
public class CustomLinkedList<T> {
    
    /**
     * Represents the head node of the CustomLinkedList.
     */
    private ListNode<T> head;
    
    /**
     * Represents the tail node of the CustomLinkedList.
     */
    private ListNode<T> tail;
    
    /**
     * Represents the size of the CustomLinkedList.
     */
    private int size;
    
    /**
     * Constructs an empty CustomLinkedList with default values (head and tail set to null, size set to 0).
     */
    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Constructs a CustomLinkedList by copying the elements from another CustomLinkedList.
     *
     * @param list the source CustomLinkedList for initialization
     */
    public CustomLinkedList(CustomLinkedList<T> list) {
        head = null;
        tail = null;
        size = 0;
        this.add(list);
    }
    
    /**
     * Checks if the CustomLinkedList is empty.
     *
     * @return true if the CustomLinkedList is empty, false otherwise
     */
    public boolean empty() {
        return size == 0;
    }
    
    /**
     * Adds an element to the tail of the CustomLinkedList.
     *
     * @param data the element to be added
     */
    public void add(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        
        if (empty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    /**
     * Adds an element to the CustomLinkedList at the specified index.
     *
     * @param index the position at which the element should be added
     * @param data the element to be added
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void add(int index, T data) throws IndexOutOfBoundsException {
        if (index > size + 1)
            throw new IndexOutOfBoundsException("Index out of bounds");
        
        ListNode<T> newNode = new ListNode<>(data);
        
        if (index == 1) {
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else {
            ListNode<T> current = head;
            for (int i = 1; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            
            if (newNode.next == null) {
                tail = newNode;
            }
        }
        size++;
    }
    
    /**
     * Adds all elements from a given CustomLinkedList to the tail of this CustomLinkedList.
     *
     * @param list the CustomLinkedList whose elements are to be added
     */
    public void add(CustomLinkedList<T> list) {
        ListNode<T> current = list.head;
        while (current != null) {
            this.add(current.data);
            current = current.next;
        }
    }
    
    /**
     * Adds all elements from a given CustomLinkedList to this CustomLinkedList at the specified index.
     *
     * @param index the position at which the elements should be added
     * @param list the CustomLinkedList whose elements are to be added
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void add(int index, CustomLinkedList<T> list) {
        if (index > size + 1)
            throw new IndexOutOfBoundsException("Index out of bounds");
        
        int currentIndex = index;
        ListNode<T> current = list.head;
        
        while (current != null) {
            this.add(currentIndex, current.data);
            currentIndex++;
            current = current.next;
        }
    }
    
    /**
     * Deletes the element at the specified index from the CustomLinkedList.
     *
     * @param index the index of the element to be deleted
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        
        if (index == 1) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            ListNode<T> current = head;
            for (int i = 1; i < index - 1; i++) {
                current = current.next;
            }
            
            current.next = current.next.next;
            
            if (current.next == null) {
                tail = current;
            }
        }
        size--;
    }
    
    /**
     * Deletes the first occurrence of the specified element from the CustomLinkedList.
     *
     * @param deletingData the element to be deleted
     */
    public void delete(T deletingData) {
        if (empty()) return;
        
        if (head.data.equals(deletingData)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return;
        }
        
        ListNode<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(deletingData)) {
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }
    
    /**
     * Retrieves the element at the specified index from the CustomLinkedList.
     *
     * @param index the index of the element to be retrieved
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        
        ListNode<T> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * Checks if the CustomLinkedList contains the specified element.
     *
     * @param data the element to be checked for existence
     * @return true if the element is found, false otherwise
     */
    public boolean contains(T data) {
        ListNode<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Retrieves the index of the first occurrence of the specified element in the CustomLinkedList.
     *
     * @param data the element to be searched for
     * @return the index of the first occurrence of the element, or -1 if not found
     */
    public int getIndex(T data) {
        ListNode<T> current = head;
        int index = 1;
        
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }
    
    @Override
    public String toString() {
        ListNode<T> current = head;
        StringBuilder result = new StringBuilder("Linked list(size: " + this.size + "):\n");
        while (current != null) {
            result.append(current.data);
            if (current.next != null)
                result.append(", ");
            current = current.next;
        }
        result.append(".");
        return result.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomLinkedList<?>)) return false;
        CustomLinkedList<?> that = (CustomLinkedList<?>) o;
        if (this.size != that.size) return false;
        
        ListNode<T> current = this.head;
        ListNode<?> current_o = that.head;
        
        while (current != null) {
            if (!current.data.equals(current_o.data)) {
                return false;
            }
            current = current.next;
            current_o = current_o.next;
        }
        return true;
    }
    
    /**
     * Returns current size of list.
     *
     * @return size of list
     */
    public int getSize() {
        return size;
    }
}