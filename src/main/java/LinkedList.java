import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Chris Qiu
 */
class LinkedList<E> implements List<E>, Deque<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    private class Node<E> {
        Node<E> prev;
        Node<E> next;
        E object;

        Node(E element) {
            object = element;
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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(first);
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
        return null;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
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
        if (index < 0) {
            if (first == null) {
                first = new Node<>(element);
            } else {
                Node<E> n = first;
                while (n.next != null) {
                    n = n.next;
                }
                n.next = new Node<>(element);
            }
        } else {
        }
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator<>(first);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        Node<E> e = find(index);
        return new LinkedListIterator<>(e);
    }

    private Node<E> find(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        if (size == 0)
            add(0, e);
        else
            add(size - 1, e);
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingListIterator<>(last);
    }

    private class LinkedListIterator<T> implements ListIterator<T> {
        Node<T> current;

        LinkedListIterator(Node<T> start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            return current.next.object;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev != null;
        }

        @Override
        public T previous() {
            return current.prev.object;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
        }

        @Override
        public void set(T e) {
        }

        @Override
        public void add(T e) {
        }
    }

    private class DescendingListIterator<R> extends LinkedListIterator<R> {
        DescendingListIterator(Node<R> start) {
            super(start);
        }

        @Override
        public boolean hasNext() {
            return current.prev != null;
        }

        @Override
        public R next() {
            return current.prev.object;
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
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
        }

        @Override
        public void set(R r) {
        }

        @Override
        public void add(R r) {
        }
    }
}
