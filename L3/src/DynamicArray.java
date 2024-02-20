public class DynamicArray {
    private int[] arr;
    private int size;
    private int capacity;

    public DynamicArray() {
        this.capacity = 1;
        this.size = 0;
        this.arr = new int[capacity];
    }

    public void append(int val) {
        if (size == capacity) {
            resize(2 * capacity); // Double the size
        }
        arr[size++] = val;
    }

    public int pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Array is empty!");
        }
        int removedValue = arr[--size];
        if (size < capacity / 4) {
            resize(capacity / 2); // Halve the size
        }
        return removedValue;
    }
    public int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range!");
        }
        return arr[index];
    }

    private void resize(int newCapacity) {
        // TODO: Implement the resize method
        int[] newArray = new int[newCapacity];
        // Copy elements from the old array to the new array
        for (int i = 0; i < size; i++) {
            newArray[i] = arr[i];

        }
        // Update the capacity and arr reference
        capacity = newCapacity;
        arr = newArray;
    }


    public static void main(String[] args) {
        DynamicArray dynArr = new DynamicArray();
        // Sample test cases for students to fill and check their implementations
        dynArr.append(1);
        dynArr.append(2);
        System.out.println(dynArr.get(1)); // Should print 2
        dynArr.pop();
        System.out.println(dynArr.get(0)); // Should print 1
    }
}