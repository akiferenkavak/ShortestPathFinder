// CustomArrayList.java

import java.util.List;

public class MyArrayList<T> {
    private Object[] elements;
    private int size;

    // Başlangıç kapasitesi
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(List<T> other) {
         this.elements = new Object[other.size() * 2]; // Kapasite, gelen listenin boyutuna göre ayarlanır
        this.size = 0;
        for (int i = 0; i < other.size(); i++) {
             this.add(other.get(i)); // Elemanları tek tek ekle
        }
    }

    // Eleman ekleme
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    // Eleman alma (index bazlı)
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    // Eleman kaldırma (index bazlı)
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        @SuppressWarnings("unchecked")
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    // Mevcut boyut
    public int size() {
        return size;
    }

    // isEmpty: Liste boş mu?
    public boolean isEmpty() {
        return size == 0;
    }

    // Kapasite kontrolü ve genişletme
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }
}
