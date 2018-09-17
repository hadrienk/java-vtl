package no.ssb.vtl.script.operations.foreach;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.Iterator;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

public final class BreakerIterator<T> extends AbstractIterator<T> {

    private final Predicate<T> breaker;
    private final PeekingIterator<T> source;

    public BreakerIterator(Iterator<T> source, Predicate<T> breaker) {
        this.breaker = checkNotNull(breaker);
        this.source = Iterators.peekingIterator(checkNotNull(source));
    }

    @Override
    protected T computeNext() {
        if (source.hasNext() && breaker.test(source.peek())) {
            return source.next();
        } else {
            return endOfData();
        }
    }
}