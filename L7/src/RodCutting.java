public class RodCutting {
  private int[] prices;
  private Integer[] memo;

  public RodCutting(int[] prices) {
      this.prices = prices;
      this.memo = new Integer[prices.length + 1];
  }

  public int maxRevenue(int length) {
        if (length == 0) {
            return 0;
        }
        if (memo[length] != null) {
            return memo[length];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++) {
            max = Math.max(max, prices[i - 1] + maxRevenue(length - i));
        }
        memo[length] = max;
        return max;
  }

  public static void main(String[] args) {
      int[] prices = {1, 5, 8, 9};
      RodCutting cutter = new RodCutting(prices);
      System.out.println(cutter.maxRevenue(4)); // Expected output: 10
  }
}
