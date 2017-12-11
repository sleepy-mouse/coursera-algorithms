import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Chris Qiu
 */
@Slf4j
public class RandomizedQueueTest {
    private RandomizedQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new RandomizedQueue<>();
    }

    @Test
    public void dequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertThat(queue).containsExactly(1, 2, 3, 4, 5);
        IntStream.iterate(0, i -> i + 1).limit(5).map(a -> queue.dequeue()).forEach(e -> log.info("{}", e));
    }
}
