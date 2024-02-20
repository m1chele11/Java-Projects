import org.junit.jupiter.api.Test;

import java.util.*;

public class PerformanceTest {

    @Test
    public void testInsertingSortedNumbersInBST() {
        /* TODO: */
        /* 1. Create a list of 10000 sorted numbers. */
        List<Integer> sortedNumbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            sortedNumbers.add(i);
        }
        /* 2. Create an empty BST. */
        BinaryTree<Integer> bst = new EmptyBT<>();
        /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 1000; i++) {
            bst = bst.insertBST(i);
        }
        /* 4. Measure the time it takes to insert the numbers. */
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            bst = bst.insertBST(i);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BST: " + duration);
    }

    @Test
    public void testInsertingSortedNumbersInBBT() {
        //TODO: same as above for balanced binary trees
        List<Integer> insertingNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            insertingNumbers.add(i);
        }
        BinaryTree<Integer> bbt = new EmptyBT<>();
        for(int i = 0; i < 10; i++) {
            bbt = bbt.insertBalanced(i);
        }
        long startTime = System.nanoTime();
        /* 3. Insert the numbers in the BST. */
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BBT: " + duration);
        TreePrinter.print(bbt);
    }

    @Test
    public void testRandomNumbersInBST() {
        //TODO: same as above for random numbers

        List<Integer> randomNumbers = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            randomNumbers.add(rand.nextInt(15));
        }
        Collections.shuffle(randomNumbers);
        BinaryTree<Integer> bst = new EmptyBT<>();
        
        long startTime = System.nanoTime();
        for(int i = 0; i < 5; i++) {
            bst = bst.insertBST(randomNumbers.get(i));
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BST: " + duration);
        TreePrinter.print(bst);
    }


    @Test
    public void testRandomNumbersInBBT() {
        //Todo: same as above for balanced binary trees
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomNumbers.add(i);
        }
        Collections.shuffle(randomNumbers);
        BinaryTree<Integer> bbt = new EmptyBT<>();
        for(int i = 0; i < 10; i++) {
            bbt = bbt.insertBalanced(i);
        }
        long startTime = System.nanoTime();
        /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 10; i++) {
            bbt = bbt.insertBST(i);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BBT: " + duration);
        TreePrinter.print(bbt);
    }

    @Test
    public void testFindingNumbersInBST() {
        //TODO: same as above for finding numbers in BST
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            randomNumbers.add(i);
        }
        Collections.shuffle(randomNumbers);
        BinaryTree<Integer> bst = new EmptyBT<>();
        for(int i = 0; i < 1000; i++) {
            bst = bst.insertBST(i);
        }
        long startTime = System.nanoTime();
        /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 1000; i++) {
            bst.findBST(i);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BST: " + duration);

    }
}