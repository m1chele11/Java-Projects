public class EmptyBST<E extends Comparable<E>> extends BST<E> {

    // Access fields

    E data() throws EmptyTreeExc { throw new EmptyTreeExc(); }
    BST<E> left() throws EmptyTreeExc { throw new EmptyTreeExc(); }
    BST<E> right() throws EmptyTreeExc { throw new EmptyTreeExc(); }

    // Basic properties

    boolean isEmpty () { return true; }
    int height () { return 0; }
    int size() { return 0; }
    boolean isBalanced() { return true; }

    // Insertions, lookups, and deletions

    BST<E> insert(E elem) {
        return new NodeBST<>(elem, new EmptyBST<>(), new EmptyBST<>());
    } // TODO
    BST<E> insertAndBalance(E elem) {
        return new NodeBST<>(elem, new EmptyBST<>(), new EmptyBST<>());
    } // TODO
    boolean find(E key) {
        return false;
    } // TODO
    String path(E key) throws EmptyTreeExc {
        throw new EmptyTreeExc();
    } // TODO
    BST<E> delete(E elem) throws EmptyTreeExc {
        throw new EmptyTreeExc();
    } // TODO
    BST<E> deleteAndBalance(E elem) throws EmptyTreeExc {
        throw new EmptyTreeExc();
    } // TODO
    E findMin() throws EmptyTreeExc {
        throw new EmptyTreeExc();
    } // TODO
    E findMax() throws EmptyTreeExc {
        throw new EmptyTreeExc();
    } // TODO
    BST<E> deleteMin() throws EmptyTreeExc {
        throw new EmptyTreeExc();
    } // TODO
    BST<E> deleteMax() throws EmptyTreeExc {
        throw new EmptyTreeExc();
    } // TODO

    // Object methods

    public boolean equals(Object other) { return other instanceof EmptyBST; }

    public String toString() { return ""; }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return null; }
    public TreePrinter.PrintableNode getRight() { return null; }
    public String getText() { return ""; }

}
