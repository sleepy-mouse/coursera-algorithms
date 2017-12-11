import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private final LinkedList<Item> list;

    // construct an empty randomized queue
    public RandomizedQueue() {
        list = new LinkedList<>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size() {
        return list.size();
    }

    // add the item
    public void enqueue(Item item) {
        list.add(item);
    }

    // remove and return a random item
    public Item dequeue() {
        checkEmptyQueue();
        int randomIndex = randomIndex();
        return list.remove(randomIndex);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkEmptyQueue();
        int randomIndex = randomIndex();
        return list.get(randomIndex);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return list.iterator();
    }

    private void checkEmptyQueue() {
        if (list.isEmpty())
            throw new NoSuchElementException();
    }

    private int randomIndex() {
        return StdRandom.uniform(size());
    }

    public static void main(String... args) {
    }
}
