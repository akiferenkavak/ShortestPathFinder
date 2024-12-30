public class MyQueue<E> {
	// A generic array for storing the elements in the queue
    private E[] data;
    // Number of stored elements in the queue
    private int size = 0;
    // Index of the first element
    private int first = 0;

    // Constructor to create an empty queue with the specified capacity
    @SuppressWarnings("unchecked")
    public MyQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    // Check if the queue is empty    
    // Time Complexity -> O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the element at the front without removing it
    // Time Complexity -> O(1)
    public E peek() {
        if (isEmpty()) return null;
        // Return the element at index first
        // The element at the head of the queue
        return data[first];
    }

    // Remove and return the element at the front
    // Time Complexity -> O(1)
    public E dequeue() {
        if (isEmpty()) return null;
        E head = data[first];
        data[first] = null;
        // Circular update
        first = (first + 1) % data.length;
        size--;
        return head;
    }

    // Add an element to the end of the queue
    // Time Complexity -> O(1)
    public void enqueue(E e) {
        ensureCapacity();
        // Compute the index after the last stored element
        int rear = (first + size) % data.length;
        data[rear] = e;
        size++;
    }

    // Double the array size when it is full
    // Time Complexity -> O(n)
    private void ensureCapacity() {
        if (size < data.length) return;
        System.out.println("Queue capacity: " + data.length + " -> " + (2 * data.length));
        @SuppressWarnings("unchecked")
        // Suppressing unchecked warning
        // Java does not allow direct creation of generic arrays
        E[] newArray = (E[]) new Object[2 * data.length];
        // Copy every element to the new array
        for (int i = 0; i < size; i++) {
            newArray[i] = data[(first + i) % data.length];
        }
        // Update data to new data
        data = newArray;
        first = 0;
    }

    // Return the number of elements in the queue
    // Time Complexity -> O(1)
    public int getSize() {
        return size;
    }
}
