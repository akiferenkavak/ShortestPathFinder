// A generic class to implement a resizable array-based stack
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
    public MyStack(int capacity) {
        if (capacity < DEFAULT_CAPACITY) capacity = DEFAULT_CAPACITY;
        data = (E[]) new Object[capacity];
    }

    // Check if the stack is empty
    // Time Complexity -> O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of elements in the stack
    // Time Complexity -> O(1)
    public int getSize() {
        return size;
    }

    // Return the underlying array (for debugging purposes)
    public E[] getData() {
        return data;
    }

    // Return the top element without removing it
    // Time Complexity -> O(1)
    public E peek() {
        if (isEmpty()) 
            return null;
        return data[size - 1];
    }

    // Remove and return the top element
    // Time Complexity -> O(1)
    public E pop() {
        if (isEmpty()) 
            return null;
        E top = data[--size];
        data[size] = null;
        if (size < data.length / 4.0 && !isEmpty())
            resize(data.length / 2);
        return top;
    }

    // Add an element to the top of the stack
    // Time Complexity -> O(1)
    public void push(E e) {
        if (size == data.length)
            resize(2 * data.length);
        data[size++] = e;
    }
    
    // Resize the internal array to a given capacity
    // Time Complexity -> O(n)
    private void resize(int capacity) {
        if (capacity < DEFAULT_CAPACITY) 
            return;
        System.out.println("Stack capacity: " + data.length + " -> " + capacity);
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(data, 0, newArray, 0, size);
        data = newArray;
    }
}
