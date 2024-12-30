// This interface defines the contract for a custom queue implementation
// Why we used an interface?
// Ensures flexibility -> Multiple queue implementations
public interface MyQueueInterface<E> {
	// Time Complexity -> O(1)
    void enqueue(E element);
    // Time Complexity -> O(1)
    E dequeue();
	// Time Complexity -> O(1)
    E peek();
	// Time Complexity -> O(1)
    boolean isEmpty();
	// Time Complexity -> O(1)
    int getSize();
}
