// This interface defines the contract for a custom stack implementation
// Why we used an interface?
// Ensures flexibility -> Multiple stack implementations
public interface MyStackInterface<E> {
	// Time Complexity -> O(1)
    void push(E element);
    // Time Complexity -> O(1)
    E pop();
    // Time Complexity -> O(1)
    E peek();
    // Time Complexity -> O(1)
    boolean isEmpty();
    // Time Complexity -> O(1)
    int getSize();
}
