public class NEmptyPList<E> extends PList<E> {
    static class TODO extends RuntimeException {} // TODO: remove this line when you're done
    private final E first;
    private final PList<E> rest;

    public NEmptyPList(E first, PList<E> rest) {
        this.first = first;
        this.rest = rest;
    }

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
        if (other instanceof NEmptyPList<?> otherList) {
            return first.equals(otherList.first) && rest.equals(otherList.rest);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return first.hashCode() + rest.hashCode();
    }
}
