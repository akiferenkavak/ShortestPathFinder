// This class is a custom implementation of a dynamic array
import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {
	// Array to store the list elements
    private Object[] elements;
    // The number of elements in the list
    private int size;
    
    // Constructor to initialize the array with a default size 10
    // Time Complexity -> O(1)
    public MyArrayList() {
        elements = new Object[10];
        // Initially the list is empty
        size = 0;
    }
    
    // Add an element to the array
    // Time Complexity -> O(n) when resizing occurs
    public void add(E element) {
    	// If the array is full, double the size using resize function
        if (size == elements.length) {
            resize();
        }
        // Add the element to the array
        // Increment the size
        elements[size++] = element;
    }
    
    // Return the element at the specified index
    @SuppressWarnings("unchecked")
    // Suppressing unchecked warning
    // Java does not allow direct creation of generic arrays
    // Time Complexity -> O(1)
    public E get(int index) {
    	// Check if the index is in the bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        // Return the element at the given index
        // Cast it to the generic type E
        return (E) elements[index];
    }
    
    // Remove the element at the specified index
    // Shift the elements
    // Time Complexity -> O(n)
    // n is the number of elements after the index
    public void remove(int index) {
    	// Check if the index is in the bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        // Shift all elements to the left
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        // Decrement the size
        // Null out the last element to free memory
        elements[--size] = null;
    }
    
    // Return the current size of the array
    // Time Complexity -> O(1)
    public int size() {
        return size;
    }
    
    // Check if the list is empty
    // Time Complexity -> O(1)
    public boolean isEmpty() {
       return size == 0;
       
    }

    // Double the size if the array is full
    // Time Complexity -> O(n)
    // n is the current size of the list
    private void resize() {
    	// Create a new array
    	// Double the current capacity
        Object[] newElements = new Object[elements.length * 2];
        // Copy all elements to the new array
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }
    
    // Iterator to iterate over the elements of the list
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
        	// The current position in the iteration
            private int currentIndex = 0;
            
            // Check if there are more elements to iterate
            // Time Complexity -> O(1)
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }
            // Return the next element in the list
            // Time Complexity -> O(1)
            @Override
            // Suppresses warnings about type-casting Object to generic type E
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) elements[currentIndex++];
            }
        };
    }
    
    // Return a string representation of the list
    // Time Complexity -> O(n)
    // n is the size of the list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i].toString());
            // Add a comma and space between elements except the last one
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
