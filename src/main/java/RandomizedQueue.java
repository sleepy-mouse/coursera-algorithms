import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Deque<Item> deque;

    // construct an empty randomized queue
    public RandomizedQueue() {
        deque = new Deque<>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size() {
        return deque.size();
    }

    // add the item
    public void enqueue(Item item) {
        deque.addLast(item);
    }

    // remove and return a random item
    public Item dequeue() {
        checkEmptyQueue();
        // TODO
        throw new UnsupportedOperationException();
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkEmptyQueue();
        // TODO
        return null;
    }

    private void checkEmptyQueue() {
        if (deque.isEmpty())
            throw new NoSuchElementException();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return deque.iterator();
    }

    public static void main(String... args) {
    }
}
