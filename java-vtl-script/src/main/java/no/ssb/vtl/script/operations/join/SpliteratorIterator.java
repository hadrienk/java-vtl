package no.ssb.vtl.script.operations.join;

import com.google.common.collect.AbstractIterator;

import java.util.Spliterator;

import static com.google.common.base.Preconditions.checkNotNull;

public class SpliteratorIterator<T> extends AbstractIterator<T> {

    private final Spliterator<T> spliterator;
    private T last;

    public SpliteratorIterator(Spliterator<T> spliterator) {
        this.spliterator = checkNotNull(spliterator);
    }

    @Override
    protected T computeNext() {
        boolean hasMore = spliterator.tryAdvance(value -> last = value);
        return hasMore ? last : endOfData();
    }
}
