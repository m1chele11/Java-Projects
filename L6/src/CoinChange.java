import java.util.Arrays;

public class CoinChange {
    public static int[] denominations = new int[]{1, 5, 10, 25};

    public static int minChange(int amount) {
        //TODO - This function should return the minimum number of coins needed to make change for the given amount
        int totalCoins = 0;
        if(amount == 0){//base case for 0
            return 0;
        }

        for(int i = denominations.length - 1; i >= 0; i--){//iterate through the denominations array
            if(amount >= denominations[i]){//if the amount is greater than the denomination
                totalCoins += amount / denominations[i];//add the amount divided by the denomination to the total coins
                amount = amount % denominations[i];//set the amount to the remainder of the amount divided by the denomination
            }
        }
        return totalCoins;
    }

    /*
    This is a helper function that you may find useful for
    adding numbers while avoiding the MAX_VALUE overflow problem
     */
    public static int add(int x, int y) {
        if (x == Integer.MAX_VALUE || y == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return x + y;
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 30; i++) {
            System.out.println("The minimum number of coins to make change for " + i + " is: " + minChange(i));
        }
    }
}