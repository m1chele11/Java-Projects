public class NEmptyPList<E> extends PList<E> {
    static class TODO extends RuntimeException {} // TODO: remove this line when you're done
    private final E first;
    private final PList<E> rest;

    public NEmptyPList(E first, PList<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    public int size() {
        return 1 + rest.size();
    }

    public boolean isEmpty() {
        return false;
    }
    public boolean contains (E element) {
        if(first.equals(element)){
            return true;
        }else{
            return rest.contains(element);
        }
    }
    public int indexOf (E element) throws NotFoundException {
        if(first.equals(element)){
            return 0;
        } else if (rest instanceof NEmptyPList) {
            int restIndex = rest.indexOf(element);
            return (restIndex == -1) ? -1 : 1 + restIndex;
        }else {
            throw new NotFoundException();
        }
    }

    public int lastIndexOf (E element) throws NotFoundException {
        if (first.equals(element)) {
            int restIndex = rest.lastIndexOf(element);
            return (restIndex == -1) ? 0 : 1 + restIndex;
        } else if (rest instanceof NEmptyPList) {
            throw new NotFoundException();
        } else {
            try {
                int restIndex = rest.lastIndexOf(element);
                return (restIndex == -1) ? -1 : 1 + restIndex;
            } catch (NotFoundException e) {
                throw new NotFoundException();
            }
        }
    }
    public E get (int index) throws IndexOutOfBoundsException {
        if(index == 0){
            return first;
        } else if (rest instanceof NEmptyPList) {
            return rest.get(index - 1);
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public PList<E> remove (int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            return rest;
        } else if (rest instanceof NEmptyPList) {
            PList<E> newRest = rest.remove(index - 1);
            return new NEmptyPList<>(first, newRest);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public PList<E> subList (int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        if (fromIndex == 0) {
            if (toIndex == 0) {
                return new EmptyPList<>();
            } else if (toIndex == 1) {
                return new NEmptyPList<>(first, new EmptyPList<>());
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else if (fromIndex < 0 || fromIndex >= size() || toIndex <= fromIndex) {
            return new NEmptyPList<>(first, rest.subList(fromIndex - 1, toIndex - 1));
        } else if (rest instanceof NEmptyPList) {
            throw new IndexOutOfBoundsException();
        } else {
            throw new IndexOutOfBoundsException();
        }
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
