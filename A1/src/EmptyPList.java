public class EmptyPList<E> extends PList<E> {
    static class TODO extends RuntimeException {} // TODO: remove this line when you're done
    public int size() {
        throw new TODO();
    }
    public boolean isEmpty() {
        throw new TODO();
    }
    public boolean contains (E element) {
        throw new TODO();
    }
    public int indexOf (E element) throws NotFoundException {
        throw new TODO();
    }
    public int lastIndexOf (E element) throws NotFoundException {
        throw new TODO();
    }
    public E get (int index) throws IndexOutOfBoundsException {
        throw new TODO();
    }
    public PList<E> remove (int index) throws IndexOutOfBoundsException {
        throw new TODO();
    }
    public PList<E> subList (int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        throw new TODO();
    }

    public boolean equals (Object other) {
        return other instanceof EmptyPList;
    }

    public int hashCode() {
        return 0;
    }

}
