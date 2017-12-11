import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Chris Qiu
 */
class Util {
    <E> void checkEmptyDeque(List<E> deque) {
        if (deque.isEmpty())
            throw new NoSuchElementException();
    }

    <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new IllegalArgumentException();
        }
        return reference;
    }
}
