public class Test {

    private static final int TEST_SIZE = 1000000;

    public static void main(String[] args) {

        // Testing doubling strategy
        testStrategy(2.0, "Doubling");

        //TODO: Test other strategies
        //My test:
        testStrategy(1.5, "1.5");
        testStrategy(1.7, "1.7");
        testStrategy(2.5, "2.5");
        testStrategy(10, "10.0");
    }
    private static void testStrategy(double growthFactor, String strategyName) {
        System.out.println("Testing " + strategyName + " Strategy...");

        DynamicArray dynArr = new DynamicArray(growthFactor);

        long startTime = System.nanoTime();

        for (int i = 0; i < TEST_SIZE; i++) {
            dynArr.append(i);

            if (i % 1000 == 999) {
                for (int j = 0; j < 10; j++) {
                    dynArr.pop();
                }
            }
        }

        long endTime = System.nanoTime();
        long operationTime = endTime - startTime;

        System.out.println(strategyName + " Strategy Operation Time: " + operationTime + " ns");
        System.out.println("----------------------------------------------------");
    }
}
