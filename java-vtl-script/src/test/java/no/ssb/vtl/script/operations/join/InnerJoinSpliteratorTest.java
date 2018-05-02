package no.ssb.vtl.script.operations.join;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class InnerJoinSpliteratorTest {

    @Test
    public void test() {
        List<List<String>> left = Lists.newArrayList(
                Arrays.asList("2016", "1", "1-2016-left"),
                Arrays.asList("2017", "1", "1-2017-left"),
                Arrays.asList("2018", "1", "1-2018-left"),
                Arrays.asList("2016", "2", "2-2016-left"),
                Arrays.asList("2017", "2", "2-2017-left"),
                Arrays.asList("2018", "3", "3-2018-left"),
                Arrays.asList("2015", "4", "4-2015-left"),
                Arrays.asList("2016", "4", "4-2016-left"),
                Arrays.asList("2017", "4", "4-2017-left")
        );

        List<List<String>> right = Lists.newArrayList(
                Arrays.asList("2015", "1", "1-2015-right"),
                Arrays.asList("2016", "3", "3-2016-right"),
                Arrays.asList("2018", "1", "1-2018-right"),
                Arrays.asList("2016", "4", "4-2016-right"),
                Arrays.asList("2017", "2", "2-2017-right"),
                Arrays.asList("2018", "3", "3-2018-right"),
                Arrays.asList("2015", "4", "4-2015-right"),
                Arrays.asList("2016", "5", "5-2016-right"),
                Arrays.asList("2017", "5", "5-2017-right")
        );

        Comparator<List<String>> predicate = Comparator.nullsFirst(
                Comparator.comparing(list -> list.get(1), Comparator.naturalOrder())
        );

       //InnerJoinSpliterator<List<String>, Map<String, List<String>>> innerJoinSpliterator = new InnerJoinSpliterator<>(
       //        predicate,
       //        (lefts, rights) -> ImmutableMap.of("left", lefts, "right", rights),
       //        left.stream().sorted(predicate).spliterator(),
       //        right.stream().sorted(predicate).spliterator()
       //);

        //innerJoinSpliterator.forEachRemaining(System.out::println);

    }
}