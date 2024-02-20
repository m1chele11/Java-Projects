import java.util.Optional;

public class NodeBT<E extends Comparable<E>> extends BinaryTree<E> {
    private E data; // is updated when moveUp / moveDown are called
    private final BinaryTree<E> left, right;
    private final int height, size;

    NodeBT (E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.size = left.size() + right.size() + 1;
        this.height = Math.max(left.height(), right.height()) + 1;
        left.parent = Optional.of(this);
        right.parent = Optional.of(this);
    }

    // Access fields

    E getData () { return data; }
    BinaryTree<E> getLeftBT () { return left; }
    BinaryTree<E> getRightBT () { return right; }

    // Basic properties

    boolean isEmpty () { return false; }
    int height() { return height; }
    int size() { return size; }
    boolean isBalanced () { return Math.abs(left.height() - right.height()) <= 1; }

    BinaryTree<E> insertBalanced(NodeBT<E> node) {

        NodeBT<E> newBalance = new NodeBT<>(data, right, left.insertBalanced(node));
        return newBalance.moveUp();
    }

    E getRightMost () {
        if (right.isEmpty()) {
            return data;
        } else {
            try {
                return right.getRightMost();
            } catch (EmptyTreeE emptyTreeE) {
                emptyTreeE.printStackTrace();
            }
        }
        return NodeBT.this.data;
    }

    BinaryTree<E> removeRightMost () {
        if (right.isEmpty()) {
            return left;
        } else {
            try{
                return new NodeBT<>(data, left, right.removeRightMost()).moveUp();
            } catch (EmptyTreeE emptyTreeE) {
                emptyTreeE.printStackTrace();
            }
        }
        return left;
    }

    BinaryTree<E> deleteRoot () {
        if (right.isEmpty()) {
            return left;
        }
        try{
            E rightMost = right.getRightMost();
            return removeRightMost().deleteRoot().insertBalanced(BinaryTree.mkLeaf(rightMost));
        }catch (EmptyTreeE emptyTreeE){
            return left;
        }
    }

    /**
     * If the node is the root, do nothing.
     * Otherwise, if the node is smaller than its parent, then swap the data and
     * ask the parent to move up.
     *
     * @return
     */
    BinaryTree<E> moveUp () {
        if (parent.isEmpty()) {
            return this;
        } else if (data.compareTo(parent.get().getData()) < 0) {
            E temp = data;
            data = parent.get().getData();
            parent.get().data = temp;
            return parent.get().moveUp();
        } else {
            return this;
        }
    }

    /**
     *  If the node is a leaf, do nothing.
     *  If the node has only one child, check if the child is smaller than the node, and
     *  in that case swap the data and ask the child to move down.
     *  If the node has two children, repeat the process above with the smaller of the
     *  two children.
     */
    void moveDown() throws EmptyTreeE {
        if(!left.isEmpty() || !right.isEmpty()) {
            BinaryTree<E> child = (left.isEmpty()) ? right : (right.isEmpty()) ? left : (left.getData().compareTo(right.getData()) < 0) ? left : right;

            if (data.compareTo(child.getData()) > 0) {
                E temp = data;
                data = child.getData();
                ((NodeBT<E>) child).data = temp;
                ((NodeBT<E>) child).moveDown();

            }
        }
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return left.isEmpty() ? null : left; }
    public TreePrinter.PrintableNode getRight() { return right.isEmpty() ? null : right; }
    public String getText() { return String.valueOf(data); }

}

