import java.util.*;
import java.util.function.Function;

public class TreeTraversals {

    static <E extends Comparable<E>> List<E> preOrderList(BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        if (tree.isEmpty()) return result;
        try {
            result.add(tree.getData());
            result.addAll(preOrderList(tree.getLeftBT()));
            result.addAll(preOrderList(tree.getRightBT()));
            return result;
        }catch (EmptyTreeE e){
            return result;
        }
    }

    static <E extends Comparable<E>> List<E> inOrderList(BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        if (tree.isEmpty()) return result;
        try{
            result.addAll(inOrderList(tree.getLeftBT()));
            result.add(tree.getData());
            result.addAll(inOrderList(tree.getRightBT()));
            return result;
        }catch (EmptyTreeE e){
            return result;
        }

    }

    static <E extends Comparable<E>> List<E> postOrderList(BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        if (tree.isEmpty()) return result;
        try {
            result.addAll(postOrderList(tree.getLeftBT()));
            result.addAll(postOrderList(tree.getRightBT()));
            result.add(tree.getData());
            return result;
        }catch (EmptyTreeE e){
            return result;
        }
    }

    static <E extends Comparable<E>> List<E> breadthFirstList (BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()){
            BinaryTree<E> temp = queue.poll();
            try {
                result.add(temp.getData());
                queue.add(temp.getLeftBT());
                queue.add(temp.getRightBT());
            }catch (EmptyTreeE e){
                continue;
            }
        }
        return result;
    }

    static <E extends Comparable<E>, F extends Comparable<F>> BinaryTree<F>
    map (Function<E,F> f, BinaryTree<E> tree) {
      if (tree.isEmpty()) {
          return new EmptyBT<>();
      }
      try {
            return new NodeBT<>(f.apply(tree.getData()),
                    map(f, tree.getLeftBT()), map(f, tree.getRightBT()));
        }catch (EmptyTreeE e){
            return new EmptyBT<>();
        }
    }

    static int sum (BinaryTree<Integer> tree) {
        int leftSum = 0;
        int rightSum = 0;
        if (tree.isEmpty()) {
            return 0;
        }
        try{
            leftSum = sum(tree.getLeftBT());
            rightSum = sum(tree.getRightBT());
            return tree.getData() + leftSum + rightSum;
        }catch (EmptyTreeE e) {
            return 0;
        }
    }

    static int mul (BinaryTree<Integer> tree) {
        int leftMull = 1;
        int rightMull = 1;

        if (tree.isEmpty()) {
            return 1;
        }
        try {
            leftMull = mul(tree.getLeftBT());
            rightMull = mul(tree.getRightBT());
            return tree.getData() * leftMull * rightMull;
        } catch (EmptyTreeE e) {
            return 1;
        }
    }
    static <E extends Comparable<E>> BinaryTree<E> mirror (BinaryTree<E> tree) {
        if (tree.isEmpty()) {
            return new EmptyBT<>();
        }
        try {
            BinaryTree<E> left = mirror(tree.getLeftBT());
            BinaryTree<E> right = mirror(tree.getRightBT());
            return new NodeBT<>(tree.getData(), right, left);
        } catch (EmptyTreeE e) {
            return new EmptyBT<>();
        }
    }
}
