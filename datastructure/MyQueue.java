// A generic queue that uses an array circularly to store the elements.
public class MyQueue<E> {
   // data fields
   private E[] data; // a generic array for storing the elements in the queue
   private int size = 0; // the number of the stored elements (initially 0)
   private int first = 0; // the index of the first queue element (initially 0)

   // constructor that creates an empty queue with the specified initial capacity
   public MyQueue(int capacity) {
      // the array is created with the type Object instead of the generic type E
      // then casted to E[] as generic array creation is not possible
      data = (E[]) new Object[capacity];
   }

   // returns true if the queue is empty and false otherwise
   public boolean isEmpty() {
      return size == 0;
   }

   // returns the element at the start (head) of the queue without removing it
   public E peek() {
      // return null if the queue is empty
      if (isEmpty())
         return null;
      // return the element at index first (the element at the head of the queue)
      return data[first];
   }

   // removes and returns the element at the start (head) of the queue
   public E dequeue() {
      // return null if the queue is empty
      if (isEmpty())
         return null;
      // store the first element in a variable to return after it is removed
      E head = data[first];
      // remove the first element
      data[first] = null; // dereference to help garbage collection
      // update the variables first and size
      first = (first + 1) % data.length; // circular update
      size--; // decrease size by 1
      // return the removed first element that is stored in the variable head
      return head;
   }

   // adds the given element e to the end (tail) of the queue (the ensureCapacity
   // method is used to double the internal array size when it is full)
   public void enqueue(E e) {
      // invoke the ensureCapacity method
      ensureCapacity();
      // compute the rear index (the index after the last stored element)
      int rear = (first + size) % data.length; // circular indexing
      // add the element e to the computed rear index of the array
      data[rear] = e;
      // increase size by 1
      size++;
   }

   // create a new array with twice the old capacity if the internal array is full
   private void ensureCapacity() {
      // end the method if the queue is not full
      if (size < data.length)
         return;
      // print a message that the queue capacity is updated
      System.out.println("Queue capacity: " + data.length + " -> " + 2 * data.length);
      // create a new array with the twice the old capacity
      E[] newArray = (E[]) new Object[2 * data.length];
      // copy the stored elements to the new array circularly
      for (int i = 0; i < data.length; i++)
         newArray[i] = data[(first + i) % data.length];
      data = newArray; // set the data array as the new array
      // update the value of the variable first
      first = 0;
   }

}