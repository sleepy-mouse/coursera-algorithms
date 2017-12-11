import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Chris Qiu
 */
class LinkedList<E> implements List<E>, Deque<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    private static class Node<T> {
        Node<T> prev;
        Node<T> next;
        T object;

        Node(T element) {
            object = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "object=" + object +
                    '}';
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return stream().anyMatch(e -> Objects.equals(e, object));
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(first, 0);
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        int i = 0;
        for (E e : this) {
            objects[i++] = e;
        }
        return objects;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        for (int i = 0; i < a.length; i++) {
            T t = a[i];
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException();
        addLast(e);
        return true;
    }

    @Override
    public boolean remove(Object object) {
        return removeIf(e -> Objects.equals(e, object));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (Iterator<E> it = this.iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
        }
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == size) {
            first = new Node<>(element);
        } else {
            Node<E> n = first;
            while (n.next != null) {
                n = n.next;
            }
            n.next = new Node<>(element);
        }
    }

    @Override
    public E remove(int index) {
        final Node<E> node = findNode(index);
        final E object = node.object;
        removeNode(node);
        return object;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator<>(first, 0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkIndex(index);
        Node<E> e = findNode(index);
        return new LinkedListIterator<>(e, index);
    }

    private Node<E> findNode(int index) {
        Node<E> n = first;
        for (int i = 0; i <= index && n.next != null; i++, n = n.next) {
            if (i == index)
                return n;
        }
        throw new NoSuchElementException(String.valueOf(index));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException(String.valueOf(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addFirst(E e) {
        if (size > 0) {
            final Node<E> newFirst = new Node<>(e);
            Node<E> oldFirst = first;
            oldFirst.prev = newFirst;
            newFirst.next = oldFirst;
            first = newFirst;
            incrementSize();
        } else {
            addToEmptyList(e);
        }
    }

    private void addToEmptyList(E e) {
        final Node<E> newElement = new Node<>(e);
        first = newElement;
        last = newElement;
        size = 1;
    }

    @Override
    public void addLast(E e) {
        if (size > 0) {
            final Node<E> newLast = new Node<>(e);
            Node<E> oldLast = last;
            oldLast.next = newLast;
            newLast.prev = oldLast;
            last = newLast;
            incrementSize();
        } else {
            addToEmptyList(e);
        }
    }

    private void incrementSize() {
        size++;
    }

    private void decrementSize() {
        size--;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return false;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return pollFirst();
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return pollLast();
        }
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        } else {
            final E object = first.object;
            first = removeFirstNode(first);
            if (first == null)
                last = null;
            decrementSize();
            return object;
        }
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        } else {
            final E object = last.object;
            last = removeLastNode(last);
            if (last == null)
                first = null;
            decrementSize();
            return object;
        }
    }

    private static <V> Node<V> removeLastNode(Node<V> oldLast) {
        Objects.requireNonNull(oldLast);
        final Node<V> newLast = oldLast.prev;
        oldLast.next = null;
        oldLast.prev = null;
        oldLast = null;
        if (newLast != null) {
            newLast.next = null;
            return newLast;
        } else return null;
    }

    private static <V> Node<V> removeFirstNode(Node<V> oldFirst) {
        Objects.requireNonNull(oldFirst);
        final Node<V> newFirst = oldFirst.next;
        oldFirst.next = null;
        oldFirst.prev = null;
        oldFirst = null;
        if (newFirst != null) {
            newFirst.prev = null;
            return newFirst;
        } else return null;
    }

    private static <V> void removeNode(Node<V> node) {
        Objects.requireNonNull(node);
        final Node<V> prev = node.prev, next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null)
            next.prev = prev;
        node.prev = null;
        node.next = null;
        node = null;
    }

    @Override
    public E getFirst() {
        final E e = peekFirst();
        if (e != null) {
            return e;
        } else throw new NoSuchElementException();
    }

    @Override
    public E getLast() {
        final E e = peekLast();
        if (e != null) {
            return e;
        } else throw new NoSuchElementException();
    }

    @Override
    public E peekFirst() {
        return isEmpty() ? null : first.object;
    }

    @Override
    public E peekLast() {
        return isEmpty() ? null : last.object;
    }

    @Override
    public boolean removeFirstOccurrence(Object object) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object object) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingListIterator<>(last, size - 1);
    }

    private class LinkedListIterator<T> implements ListIterator<T> {
        Node<T> current;
        Node<T> next;
        Node<T> prev;
        int currentIndex;
        boolean removePermissable;

        LinkedListIterator(Node<T> start, int index) {
            next = start;
            currentIndex = index;
        }

        @Override
        public boolean hasNext() {
            return current != null ? current.next != null : next != null;
        }

        @Override
        public T next() {
            if (current != null) {
                if (current.next == null)
                    throw new NoSuchElementException();
                current = current.next;
                removePermissable = true;
                currentIndex++;
                return current.object;
            } else {
                if (next != null) {
                    current = next;
                    next = null;
                    return current.object;
                } else throw new NoSuchElementException();
            }
        }

        @Override
        public boolean hasPrevious() {
            return current != null ? current.prev != null : prev != null;
        }

        @Override
        public T previous() {
            if (current != null) {
                currentIndex--;
                final T element = current.object;
                current = current.prev;
                removePermissable = true;
                return element;
            } else {
                if (prev != null) {
                    current = prev;
                    prev = null;
                    return current.object;
                } else throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            return current.next != null ? currentIndex + 1 : size;
        }

        @Override
        public int previousIndex() {
            return current.prev != null ? currentIndex - 1 : -1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
        }

        @Override
        public void add(T e) {
        }
    }

    private class DescendingListIterator<R> extends LinkedListIterator<R> {
        DescendingListIterator(Node<R> start, int index) {
            super(start, index);
        }

        @Override
        public boolean hasNext() {
            return current.prev != null;
        }

        @Override
        public R next() {
            if (hasNext())
                return current.prev.object;
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return current.next != null;
        }

        @Override
        public R previous() {
            return current.next.object;
        }

        @Override
        public int nextIndex() {
            return current.prev != null ? currentIndex - 1 : -1;
        }

        @Override
        public int previousIndex() {
            return current.next != null ? currentIndex + 1 : size;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(R r) {
        }

        @Override
        public void add(R r) {
        }
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "first=" + first +
                ", last=" + last +
                ", size=" + size +
                '}';
    }
}
