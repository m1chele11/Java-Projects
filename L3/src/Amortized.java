import java.util.ArrayList;

public class Amortized {
    private ArrayList<Boolean> bits;
    private int len;
    private int bitsFlipped;

    Amortized(int len) {
        this.bits = new ArrayList<>();
        this.len = len;
        for (int i = 0; i < len; i++) bits.add(false);
        this.bitsFlipped = 0;
    }

    int bitsFlipped() { return bitsFlipped; }

    // TODO: Complete this method.
    void increment(int i) {
        // Implement the logic for incrementing the binary counter.
        Amortized counter = new Amortized(8);
        for (i = 0; i < 16; i++) {
            counter.increment(0);
            System.out.println(counter.bitsFlipped());
        }
    }

    public static void main(String[] args) {
        Amortized counter = new Amortized(8);
        for (int i = 0; i < 16; i++) {
            counter.increment(0);
            System.out.println(counter.bitsFlipped());
        }
    }
}