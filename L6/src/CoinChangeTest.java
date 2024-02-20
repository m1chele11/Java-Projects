import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {

    @Test
    void minChange() {
        int change = 76;
        int expected = 4;
        int actual = CoinChange.minChange(change);
        assertEquals(expected, actual);

        int change2 = 23;
        int expected2 = 5;
        int actual2 = CoinChange.minChange(change2);
        assertEquals(expected2, actual2);

    }
}