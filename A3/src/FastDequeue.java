import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import exceptions.EmptyPCollectionException;
import interfaces.DequeueI;

public class FastDequeue<E> implements DequeueI<E> {
    /**
     * This in an implementation of a dynamically resizable dequeue.
     * All operations run in amortized constant time.
     *
     * The general idea is to use an array to store the dequeue elements.
     * The array provides O(1) access to any position but is of fixed capacity.
     * So we resize the array by doubling its size when it's full.
     *
     * We will maintain two pointers into the array: a 'right' pointer where
     * enqueue/dequeueBack operations are performed and a 'left' pointer where
     * enqueueBack/dequeue operations are performed.
     *
     * The right and left pointers move around the array as elements are inserted
     * and deleted. In the general situation, the values are stored in the array as follows:
     *
     * |-------------------------|
     * | 4 5 6 _ _ _ _ _ _ 1 2 3 |
     * |-------------------------|
     *        /\        /\      /\
     *       left      right  capacity
     *
     * left and right typically point to the next available slot
     * all arithmetic modulo capacity
     * data stored at right+1, right+2, ... left-2, left-1
     *
     */
    private int capacity;
    private Optional<E>[] elements;
    private int left, right, size;
    private final Function<Integer,Integer> growthStrategy;

    @SuppressWarnings("unchecked")
    FastDequeue () {
        this.capacity = 8;
        this.elements = (Optional<E>[]) Array.newInstance(Optional.class, capacity);
        Arrays.fill(elements, Optional.empty());
        left = 0;
        right = this.capacity - 1;
        size = 0;
        this.growthStrategy = n -> n * 2;
    }

    public int size() { // O(1)
        return size;
    }

    public boolean isEmpty() { // O(1)
        return size == 0;
    }

    public void enqueue(E item) { // uses right pointer; O(1) amortized
        if(size == capacity){
            resize();
        }
        elements[right] = Optional.of(item);
        right = Math.floorMod(right - 1, capacity);
        size++;
    }

    public E dequeue() throws EmptyPCollectionException { // uses left pointer; O(1) amortized
            int tempIndex = Math.floorMod(left - 1, capacity);
            E elem = elements[tempIndex].orElseThrow(EmptyPCollectionException::new);
            elements[tempIndex] = Optional.empty();
            left = tempIndex;
            size--;
            return elem;
    }


    public void enqueueBack(E item) { // uses left pointer; O(1) amortized
        if(size == capacity){
            resize();
        }
        elements[left] = Optional.of(item);
        left = Math.floorMod(left + 1, capacity);
        size++;
    }

    public E dequeueBack() throws EmptyPCollectionException { // uses right pointer; O(1) amortized
        int tempIndex = Math.floorMod(right + 1, capacity);
        E elem = elements[tempIndex].orElseThrow(EmptyPCollectionException::new);
        elements[tempIndex] = Optional.empty();
        right = tempIndex;
        size--;
        return elem;
    }

    /**
     * Here is an example of how resize should work:
     *
     * Current queue:
     *
     *   0 1 2 3 4 5
     * |-------------|
     * | 4 5 6 1 2 3 |
     * |-------------|
     * right = 2; left = 3; capacity = 6
     *
     * After resize:
     *   0 1 2 3 4 5 6 7 8 9 10 11
     * |--------------------------|
     * | 1 2 3 4 5 6 _ _ _ _ _ _  |
     * |--------------------------|
     * right = 11; left = 6; capacity = 12
     *
     */
    private void resize () {
        int newCapacity = growthStrategy.apply(capacity);
        Optional<E>[] newElements = (Optional<E>[]) new Optional[newCapacity];

        int i = 0;
        while (i < capacity) {
            newElements[i] = elements[left];
            left = (left + 1) % capacity;
            i++;
        }
        left = size;
        right = newCapacity - 1;
        elements = newElements;
        capacity = newCapacity;
    }
}
