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

    String checkEmptyString(String hypernyms) {
        if (hypernyms == null) {
            throw new IllegalArgumentException("null string");
        }
        final String s = hypernyms.trim();
        if (s.isEmpty())
            throw new IllegalArgumentException("empty string");
        return s;
    }

    String nullToEmpty(String s) {
        return s == null ? "" : s.trim();
    }
}
