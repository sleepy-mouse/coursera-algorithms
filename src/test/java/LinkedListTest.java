import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;

@Slf4j
public class LinkedListTest {
    @Test
    public void a() {
        LinkedList<String> l = new LinkedList<>();
        final ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4", "5");
        l.addAll(list);
        Assertions.assertThat(l).containsOnly(list.toArray(new String[0]));
    }
}
