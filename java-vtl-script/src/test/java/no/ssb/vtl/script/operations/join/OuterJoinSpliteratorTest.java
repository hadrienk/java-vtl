package no.ssb.vtl.script.operations.join;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

public class OuterJoinSpliteratorTest {

    private List<List<String>> right;
    private List<List<String>> left;

    @Before
    public void setUp() throws Exception {
        left = newArrayList(
                asList(null, "1", "null-1-left"),
                asList("A", "2", "A-2-left"),
                asList("A", "3", "A-3-left"),
                asList("B", "4", "B-4-left"),
                asList("C", "5", "C-5-left"),
                asList("D", "6", "D-6-left"),
                asList("D", "7", "D-7-left"),
                asList("E", "8", "E-8-left"),
                asList("F", "9", "F-9-left")
        );

        right = newArrayList(
                asList("D", "1", "D-1-right"),
                asList("E", "2", "E-2-right"),
                asList("F", "3", "F-3-right"),
                asList("F", "4", "F-4-right"),
                asList("G", "5", "G-5-right"),
                asList("H", "6", "H-6-right"),
                asList("I", "7", "I-7-right"),
                asList("I", "8", "I-8-right"),
                asList(null, "9", "null-9-right")
        );
    }

    @Test
    public void testForEachRemaining() {
        OuterJoinSpliterator<List<String>, List<String>, String, Map<String, List<String>>> spliterator = new OuterJoinSpliterator<List<String>, List<String>, String, Map<String, List<String>>>(
                list -> list.get(0),
                list -> list.get(0),
                Comparator.nullsFirst(Comparator.naturalOrder()),
                (left, right) -> {
                    LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
                    map.put("left", left);
                    map.put("right", right);
                    return map;
                },
                left.spliterator(),
                right.spliterator()
        );

        spliterator.forEachRemaining(System.out::println);

    }
}