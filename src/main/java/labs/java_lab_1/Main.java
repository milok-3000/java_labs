package labs.java_lab_1;

/**
 * Main class for testing CustomLinkedList functionality.
 */
public class Main {
    
    /**
     * Main method to demonstrate CustomLinkedList operations.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        
        // Create a new list
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        
        // Test add method
        System.out.println("Adding elements: 10, 20, 30");
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list);
        System.out.println();
        
        // Test add at index
        System.out.println("Adding 15 at index 2");
        list.add(2, 15);
        System.out.println(list);
        System.out.println();
        
        // Test get
        System.out.println("Element at index 3: " + list.get(3));
        System.out.println();
        
        // Test contains
        System.out.println("List contains 20? " + list.contains(20));
        System.out.println("List contains 100? " + list.contains(100));
        System.out.println();
        
        // Test getIndex
        System.out.println("Index of 30: " + list.getIndex(30));
        System.out.println();
        
        // Test delete by index
        System.out.println("Deleting element at index 2");
        list.delete(2);
        System.out.println(list);
        System.out.println();
        
        // Test delete by value
        System.out.println("Deleting element 30");
        list.delete(Integer.valueOf(30));
        System.out.println(list);
        System.out.println();
        
        // Test copy constructor
        CustomLinkedList<Integer> list2 = new CustomLinkedList<>(list);
        System.out.println("Copied list:");
        System.out.println(list2);
        System.out.println();
        
        // Test equals
        System.out.println("Original list equals copied list? " + list.equals(list2));
    }
}