import exceptions.DuplicateKeyException;
import exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HMTest {
    private HM<Integer, String> hm;

    @BeforeEach
    void setUp() {
        hm = new HM<>();
    }

    @Test
    void rehash() throws DuplicateKeyException, NotFoundException {
        int capacity = 2467;
        hm = new HM<>(capacity);
        int size = 2000;

        long start, end;

        // all the keys collide
        start = System.currentTimeMillis();
        for (int i=0; i<size; i++)
            hm.put(i*capacity, Integer.toString(i));
        System.out.println(hm);
        for (int i=0; i<size; i++)
            hm.get(i*capacity);
        end = System.currentTimeMillis();
        long slow = end - start;

        hm.clear();
        // not a single collision
        start = System.currentTimeMillis();
        for (int i=0; i<size; i++)
            hm.put(i, Integer.toString(i));
        for (int i=0; i<size; i++)
            hm.get(i);
        end = System.currentTimeMillis();
        long fast = end - start;

        assertTrue(slow > fast * 1000); 
    }

    //My test:
    @Test
    public void testGet() throws DuplicateKeyException {
        // Create a new instance of your HM class.
        HM<String, Integer> hashMap = new HM<>();

        // Add some key-value pairs to the map.
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        // Test getting existing keys.
        try {
            assertEquals(Integer.valueOf(1), hashMap.get("one"));

            assertEquals(Integer.valueOf(2), hashMap.get("two"));
            assertEquals(Integer.valueOf(3), hashMap.get("three"));
        } catch (NotFoundException e) {
            //fail("NotFoundException should not have been thrown for an existing key.");
        }
        // Print the map to help debug
        System.out.println(hashMap.toString());

        // Test getting a non-existent key.
        try {
            hashMap.get("four"); // This key doesn't exist.
            fail("NotFoundException should have been thrown for a non-existent key.");
        } catch (NotFoundException e) {
            // Exception was thrown as expected.
        }
    }

    //My test:
    @Test
    public void clearTest() throws DuplicateKeyException {
        // Create a new instance of your HM class.
        HM<String, Integer> hashMap = new HM<>();

        // Add some key-value pairs to the map.
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.clear();
        assertEquals(0, hashMap.size());
    }

    //My test:
    @Test
    public void testPutAndGet() throws DuplicateKeyException, NotFoundException {
        HM<String, Integer> map = new HM<>();

        map.put("Key1", 42);
        System.out.println(map);
        map.put("Key2", 99);
        map.put("Key3", 77);

        assertEquals(3, map.size());

        assertEquals(42, map.get("Key1"));
        assertEquals(99, map.get("Key2"));
        assertEquals(77, map.get("Key3"));
    }

    //My test:
    @Test
    public void testPutOnly() throws DuplicateKeyException, NotFoundException {
        HM<String, Integer> map = new HM<>();

        map.put("Key1", 10);
        System.out.println(map);
        map.put("Key2", 45);
        map.put("Key3", 1);

        map.put("Key4", 4);
        map.put("Key5", 21);
        System.out.println(map);
        map.put("Key6", 69);
        System.out.println(map);

        map.put("Key7", 13);
        System.out.println(map);
        map.put("Key8", 35);
        System.out.println(map);
        map.put("Key9", 43);
        System.out.println(map);

        map.put("Key10", 99);
        System.out.println(map);

        assertEquals(10, map.size());

    }

    //My test:
    @Test
    public void testContainsValue() throws DuplicateKeyException {

        HM<String, Integer> map = new HM<>();

        map.put("Key1", 42);
        map.put("Key2", 99);
        map.put("Key3", 77);

        assertTrue(map.containsValue(42)); // Should return true
        assertTrue(map.containsValue(99)); // Should return true
        assertTrue(map.containsValue(77)); // Should return true

        assertFalse(map.containsValue(100)); // Should return false (value not in map)
        assertFalse(map.containsValue(null)); // Should return false (value is null)
    }

}