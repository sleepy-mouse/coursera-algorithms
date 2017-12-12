package part1.week2;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class LinkedListTest {
    private static final List<Integer> LIST = ImmutableList.of(1, 2, 3, 4, 5);
    private LinkedList<Integer> l;

    @Before
    public void setUp() {
        l = new LinkedList<>();
    }

    @Test
    public void addAll() {
        l.addAll(LIST);
        assertThat(l).containsOnly(LIST.toArray(new Integer[0]));
    }

    @Test
    public void addFirst() {
        l.addFirst(1);
        l.addFirst(2);
        l.addFirst(3);
        assertThat(l).containsExactly(3, 2, 1);
    }

    @Test
    public void addLast() {
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        assertThat(l).containsExactly(1, 2, 3);
    }

    @Test
    public void removeFirst() {
        l.add(1);
        l.add(2);
        l.add(3);
        assertThat(l).containsExactly(1, 2, 3);
        l.removeFirst();
        assertThat(l).containsExactly(2, 3);
    }

    @Test
    public void removeLast() {
        l.add(1);
        l.add(2);
        l.add(3);
        assertThat(l).containsExactly(1, 2, 3);
        l.removeLast();
        assertThat(l).containsExactly(1, 2);
    }

    @Test
    public void size() {
        l.add(1);
        l.add(2);
        l.add(3);
        assertThat(l.size()).isEqualTo(3);
        l.removeLast();
        assertThat(l.size()).isEqualTo(2);
    }

    @Test
    public void isEmpty() {
        assertThat(l.isEmpty()).isTrue();
        l.add(1);
        l.add(2);
        l.add(3);
        assertThat(l.size()).isEqualTo(3);
        l.removeFirst();
        l.removeFirst();
        l.removeFirst();
        assertThat(l.isEmpty()).isTrue();
    }

    @Test
    public void iterator() {
        assertThat(l.isEmpty()).isTrue();
        l.add(1);
        l.add(2);
        l.add(3);
        Iterator<Integer> it = l.iterator();
        Integer i1 = it.next();
        assertThat(i1).isEqualTo(1);
        Integer i2 = it.next();
        assertThat(i2).isEqualTo(2);
        Integer i3 = it.next();
        assertThat(i3).isEqualTo(3);
        assertThatThrownBy(it::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void addToIndex() {
        assertThat(l.isEmpty()).isTrue();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(0, 5);
        assertThat(l).size().isEqualTo(4);
        assertThat(l).containsExactly(5, 1, 2, 3);
        l.add(2, 9);
        assertThat(l).containsExactly(5, 1, 9, 2, 3);
    }

    @Test
    public void removeAtIndex() {
        l.add(1);
        l.add(2);
        l.add(3);
        assertThat(l).size().isEqualTo(3);
        Integer removedElement = l.remove(1);
        assertThat(removedElement).isEqualTo(2);
        assertThat(l).containsExactly(1, 3);
        l.add(9);
        assertThat(l).containsExactly(1, 3, 9);
        l.remove(2);
        assertThat(l).containsExactly(1, 3);
    }
}
