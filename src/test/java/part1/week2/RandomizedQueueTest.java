package part1.week2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Chris Qiu
 */
@Slf4j
public class RandomizedQueueTest {
    private RandomizedQueue<Integer> queue;

    @BeforeEach
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
        log.info("dequeue: {}", queue.dequeue());
        log.info("dequeue: {}", queue.dequeue());
        assertThat(queue.size()).isEqualTo(3);
        log.info("dequeue: {}", queue.dequeue());
        log.info("dequeue: {}", queue.dequeue());
        log.info("dequeue: {}", queue.dequeue());
        assertThat(queue.size()).isEqualTo(0);
    }
}
