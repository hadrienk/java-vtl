package no.ssb.vtl.script.operations.repeat;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.UnmodifiableIterator;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BreakerIteratorTest {

    @Test
    public void testEmpty() {
        BreakerIterator<Integer> iterator = new BreakerIterator<>(
                Collections.emptyIterator(),
                i -> true
        );
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void testAlwaysFalse() {
        BreakerIterator<Integer> iterator = new BreakerIterator<>(
                Iterators.forArray(1, 2),
                i -> false
        );
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void testStopsAfter() {
        UnmodifiableIterator<Integer> source = Iterators.forArray(1, 2);
        PeekingIterator<Integer> peekingSource = Iterators.peekingIterator(source);
        BreakerIterator<Integer> iterator = new BreakerIterator<>(
                peekingSource,
                i -> i < 2
        );
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
        assertThat(source.hasNext()).isFalse();
        assertThat(peekingSource.hasNext()).isTrue();
    }
}