import java.util.Arrays;
import java.util.HashMap;

public class CoinChange {
    public static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    public static int[] denominations = {1, 5, 10, 25};

    public static int minChangeMemo(int amount) {
        // TODO: Implement this method
        /*
        This is the memoized solution. It is much more efficient than the recursive solution because it uses a hashmap
        to store each recursive result. This method will be very similar to minChangeReg, but will use a hashmap
        to store each recursive result, and then retrieve it if it has already been calculated.
        */
        if (hm.containsKey(amount)) {
            return hm.get(amount);
        }
        int min = Integer.MAX_VALUE;
        for (int denomination : denominations) {
            if (amount == denomination) {
                hm.put(amount, 1);
                return 1;
            }
            if (amount > denomination){
                min = Math.min(min, add(1, minChangeMemo(amount - denomination)));
            }
            else {
                hm.put(amount, Integer.MAX_VALUE);
            }
        }
        return min;

    }
    public static int minChangeReg(int amount) {
        // TODO: Implement this method
        /*
        This is your standard recursive solution. It is very inefficient because it does not use memoization,
        but I would recommend tackling this one first as this is what you are most used to.
        */
        for (int denomination : denominations) {
            if (amount == denomination) {
                return 1;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int denomination : denominations) {
            if (amount > denomination) {
                min = Math.min(min, add(1, minChangeReg(amount - denomination)));
            }
        }
        return min;
    }
    public static int add(int a, int b) {
        return a == Integer.MAX_VALUE || b == Integer.MAX_VALUE ? Integer.MAX_VALUE : a + b;
    }

    public static void main(String[] args) {
        for (int i = 23; i < 70; i += 23) {
            long start = System.currentTimeMillis();
            System.out.println("The minimum number of coins needed to make " + i + " cents is: " + minChangeMemo(i));
            long end = System.currentTimeMillis();
            System.out.println("Time taken WITH memoization: " + (end - start) + "ms");
        }
        System.out.println("--------------------------------------------------");
        for (int i = 23; i < 70; i += 23) {
            long start = System.currentTimeMillis();
            System.out.println("The minimum number of coins needed to make " + i + " cents is: " + minChangeReg(i));
            long end = System.currentTimeMillis();
            System.out.println("Time taken WITHOUT memoization: " + (end - start) + "ms");
        }

        System.out.println("--------------------------------------------------");

        long start = System.currentTimeMillis();
       System.out.println("The minimum number of coins needed to make 10000 cents is: " + minChangeMemo(10000));
        long end = System.currentTimeMillis();
        System.out.println("Time taken WITH memoization: " + (end - start) + "ms");
    }

}
