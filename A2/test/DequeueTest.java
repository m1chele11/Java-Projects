import exceptions.EmptyPCollectionException;
import exceptions.IndexOutOfBoundsException;
import interfaces.DequeueI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeueTest {
    DequeueI<Integer> dequeue;

    @BeforeEach
    void setUp() {
        dequeue = new Dequeue<>();
    }

    @Test
    void isEmpty() {
        assertTrue(dequeue.isEmpty());
    }

    //my test case:
    @Test
    void myIsEmpty() {
        assertTrue(dequeue.isEmpty());
        dequeue.enqueue(1);
        assertFalse(dequeue.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, dequeue.size());
    }

    //my test case:
    @Test
    void mySize() {
        assertEquals(0, dequeue.size());
        dequeue.enqueue(10);
        dequeue.enqueue(11);
        assertEquals(2, dequeue.size());
    }

    @Test
    void enqueue() {
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        assertEquals(3, dequeue.size());
    }

    //my test case:
    @Test
    void myEnqueue() {
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        assertEquals(2, dequeue.size());
        assertFalse(dequeue.isEmpty());
    }

    @Test
    void dequeue() throws EmptyPCollectionException{
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        assertEquals(10, dequeue.dequeue());
        assertEquals(2, dequeue.size());
    }

    //my test case:
    @Test
    void myDequeue() throws EmptyPCollectionException{
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        assertEquals(10, dequeue.dequeue());
        dequeue.enqueue(69);
        assertEquals(3, dequeue.size());
    }

    @Test
    void enqueueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(10);
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        assertEquals(3, dequeue.size());
        assertEquals(10, dequeue.dequeueBack());
    }

    //my test case:
    @Test
    void myEnqueueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(15);
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        dequeue.enqueueBack(5);
        assertEquals(4, dequeue.size());
        assertEquals(15, dequeue.dequeueBack());
    }

    @Test
    void dequeueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(10);
        System.out.println(dequeue.size());
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        assertEquals(10, dequeue.dequeueBack());
        assertEquals(2, dequeue.size());
    }

//my test case:
    @Test
    void myDequeueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(12);
        System.out.println(dequeue.size());
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        dequeue.enqueueBack(30);
        assertEquals(12, dequeue.dequeueBack());
        assertEquals(3, dequeue.size());
    }

    @Test
    void frontAndBack () throws EmptyPCollectionException{
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        // [10, 20, 30]
        dequeue.enqueueBack(40);
        dequeue.enqueueBack(50);
        // [50, 40, 10, 20, 30]
        assertEquals(50, dequeue.dequeue());
        assertEquals(30, dequeue.dequeueBack());
    }

    //my test case:
    @Test
    void myFrontAndBack () throws EmptyPCollectionException{
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        dequeue.enqueue(69);
        // [10, 20, 30, 69]
        dequeue.enqueueBack(40);
        dequeue.enqueueBack(50);
        // [50, 40, 10, 20, 30, 69
        assertEquals(50, dequeue.dequeue());
        assertEquals(69, dequeue.dequeueBack());
    }
}