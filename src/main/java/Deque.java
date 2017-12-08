import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deque<Item> implements Iterable<Item> {
    private final Util util = new Util();
    private final List<Item> deque;

    public Deque() {
        deque = new LinkedList<>();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    // return the number of items on the deque
    public int size() {
        return deque.size();
    }

    // insert the item at the front
    public void addFirst(Item item) {
        final Item item1 = util.checkNotNull(item);
        deque.add(0, item1);
    }

    // insert the item at the end
    public void addLast(Item item) {
        final Item item1 = util.checkNotNull(item);
        deque.add(item1);
    }

    // delete and return the item at the front
    public Item removeFirst() {
        util.checkEmptyDeque(deque);
        return deque.remove(0);
    }

    // delete and return the item at the end
    public Item removeLast() {
        util.checkEmptyDeque(deque);
        return deque.remove(deque.size() - 1);
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return deque.iterator();
    }
}
