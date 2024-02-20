import exceptions.EmptyPCollectionException;
import interfaces.QueueI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private QueueI<Integer> slow, fast;

    @BeforeEach
    void setUp() {
        slow = new SlowQueue<>();
        fast = new FastQueue<>();
    }
    @Test
    void isEmpty() {
        assertTrue(slow.isEmpty());
        assertTrue(fast.isEmpty());
    }

    //my test case:
    @Test
    void myIsEmpty() {
        assertTrue(slow.isEmpty());
        assertTrue(fast.isEmpty());
        slow.enqueue(1);
        assertFalse(slow.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, slow.size());
        assertEquals(0, fast.size());
        slow.enqueue(10);
        assertEquals(1, slow.size());
        fast.enqueue(10);
        assertEquals(1, fast.size());
    }

    //my test case:
    @Test
    void mySize() {
        assertEquals(0, slow.size());
        assertEquals(0, fast.size());
        slow.enqueue(10);
        slow.enqueue(11);
        slow.enqueue(20);
        assertEquals(3, slow.size());
        fast.enqueue(10);
        fast.enqueue(15);
        assertEquals(3, fast.size());
    }

    @Test
    void enqueue() {
        slow.enqueue(10);
        slow.enqueue(20);
        slow.enqueue(30);
        assertEquals(3, slow.size());
        fast.enqueue(10);
        fast.enqueue(20);
        assertEquals(2, fast.size());
    }

    //My test case:
    @Test
    void myEnqueue() {
        slow.enqueue(10);
        slow.enqueue(20);
        slow.enqueue(30);
        slow.enqueue(50);
        assertEquals(4, slow.size());
        fast.enqueue(10);
        assertEquals(1, fast.size());
    }

    @Test
    void dequeue() throws EmptyPCollectionException {
        assertThrows(EmptyPCollectionException.class, () -> slow.dequeue());
        assertThrows(EmptyPCollectionException.class, () -> fast.dequeue());
        slow.enqueue(10);
        slow.enqueue(20);
        slow.enqueue(30);
        assertEquals(10, slow.dequeue());
        assertEquals(20, slow.dequeue());
        assertEquals(30, slow.dequeue());
        fast.enqueue(10);
        fast.enqueue(20);
        assertEquals(10, fast.dequeue());
        assertEquals(20, fast.dequeue());
    }

    //My test case:
    @Test
    void myDequeue() throws EmptyPCollectionException {
        assertThrows(EmptyPCollectionException.class, () -> slow.dequeue());
        assertThrows(EmptyPCollectionException.class, () -> fast.dequeue());
        slow.enqueue(10);
        slow.enqueue(20);
        slow.enqueue(30);
        assertEquals(10, slow.dequeue());
        System.out.println(slow.size());
        assertEquals(20, slow.dequeue());
        slow.enqueue(51);
        assertEquals(30, slow.dequeue());
        assertEquals(51, slow.dequeue());
        fast.enqueue(10);
        fast.enqueue(20);
        assertEquals(10, fast.dequeue());
        fast.enqueue(69);
        assertEquals(20, fast.dequeue());
        System.out.println(fast.size());
        assertEquals(69, fast.dequeue());

    }

}