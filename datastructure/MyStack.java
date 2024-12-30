// A generic class to implement a resizable array based stack
public class MyStack<E> {
	// Array to store the elements in the stack 
    private E[] data;
    // Number of elements in the stack
    private int size = 0;
    // Default initial capacity
    private static final int DEFAULT_CAPACITY = 5; 

    // Constructor to create an empty stack with default capacity
    
    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor to create an empty stack with a specified capacity
    // Time Complexity -> O(1)
    @SuppressWarnings("unchecked")
    // Suppressing unchecked warning
    // Java does not allow direct creation of generic arrays
    public MyStack(int capacity) {
        if (capacity < DEFAULT_CAPACITY) capacity = DEFAULT_CAPACITY;
        // Create the array with the type object
        // Cast it to E[]
        data = (E[]) new Object[capacity];
    }

    // Check if the stack is empty
    // Time Complexity -> O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the top element without removing it
    // Time Complexity -> O(1)
    public E peek() {
        if (isEmpty()) 
        	return null;
        // Return the last element in the array (the top element)
        return data[size - 1];
    }

    // Remove and return the top element
    // Time Complexity -> O(1)
    public E pop() {
    	// Return null if the stack is empty
        if (isEmpty()) 
        	return null;
        // Store the top element
        // Return it after it is removed
        // Dereference to help garbage collection
        E top = data[--size];
        data[size] = null;
        // Efficient use of memory
        if (size < data.length / 4.0 && !isEmpty())
        	// Shrink the capacity of the data array to its half
        	resize(data.length / 2);
        return top;
    }

    // Add an element to the top of the stack
    // Time Complexity -> O(1)
    public void push(E e) {
        if (size == data.length)
        	// Double the capacity of the data array by using the resize method
        	resize(2 * data.length);
        // Increment the size
        data[size++] = e;
    }
    
    // Resize the internal array to a given capacity
    // Time Complexity -> O(n)
    // n is the current size of the stack
    private void resize(int capacity) {
    	// Check that the capacity does not shrink below the default capacity
        if (capacity < DEFAULT_CAPACITY) 
        	return;
        System.out.println("Stack capacity: " + data.length + " -> " + capacity);
        @SuppressWarnings("unchecked")
        // Create a new array with the given capacity
        E[] newArray = (E[]) new Object[capacity];
        // Copy the stored elements to the new array
        System.arraycopy(data, 0, newArray, 0, size);
        data = newArray;
    }
    
    // Return the number of elements in the stack
    // Time Complexity -> O(1)
    public int getSize() {
        return size;
    }

}
