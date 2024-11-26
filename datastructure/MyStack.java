// A generic class that implements a resizable array based stack.
public class MyStack<E> { // E is a generic type.
   // data fields
   private E[] data; // a generic array for storing the elements in the stack
   private int size = 0; // the number of the stored elements (initially 0)
   private static final int DEFAULT_CAPACITY = 5; // the default array capacity

   // constructor that creates an empty stack with the default initial capacity
   public MyStack() {
      // invoking the constructor MyStack(int capacity) with DEFAULT_CAPACITY
      this(DEFAULT_CAPACITY);
   }

   // constructor that creates an empty stack with the specified initial capacity
   // ensuring that the initial capacity is at least equal to the default capacity
   @SuppressWarnings("unchecked")
   public MyStack(int capacity) {
      if (capacity < DEFAULT_CAPACITY)
         capacity = DEFAULT_CAPACITY;
      // the array is created with the type Object instead of the generic type E
      // then casted to E[] as generic array creation is not possible
      data = (E[]) new Object[capacity];
   }

   // returns true if the stack is empty and false otherwise
   public boolean isEmpty() {
      return size == 0;
   }

   // returns the top element in the stack without removing it
   public E peek() {
      // return null if the stack is empty
      if (isEmpty())
         return null;
      // return the last element in the array (the top element in the stack)
      return data[size - 1];
   }

   // removes and returns the top element in the stack
   public E pop() {
      // return null if the stack is empty
      if (isEmpty())
         return null;
      // store the top element in a variable to return after it is removed
      E top = data[size - 1];
      // decrease size by 1 and remove the top element
      data[--size] = null; // dereference to help garbage collection
      // when only less than a quarter of the data array is used
      if (size < data.length / 4.0 && !isEmpty()) // and the array is not empty
         // shrink the capacity of the data array to its half
         resize(data.length / 2); // by using the resize method
      // return the removed top element that is stored in the variable top
      return top;
   }

   // adds the specified element e to the top of the stack
   public void push(E e) {
      // when the data array is full
      if (size == data.length)
         // double the capacity of the data array by using the resize method
         resize(2 * data.length);
      // add the element e after the last stored element in the array
      // and increase size by 1
      data[size++] = e;
   }

   // private (inner) method that resizes the data array to a given capacity
   // as long as the given capacity is not smaller than the default capacity
   private void resize(int capacity) {
      // if the given capacity is lower than the default capacity
      if (capacity < DEFAULT_CAPACITY)
         return; // end the method
      // print a message that the stack capacity is updated
      System.out.println("Stack capacity: " + data.length + " -> " + capacity);
      // create a new array with the given capacity
      @SuppressWarnings("unchecked")
      E[] newArray = (E[]) new Object[capacity];
      // copy the stored elements to the new array
      System.arraycopy(data, 0, newArray, 0, size);
      data = newArray; // set the data array as the new array
   }

  
}