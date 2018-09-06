package no.ssb.vtl.script.operations.repeat;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.Iterator;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

public class SkipIterator<T> extends AbstractIterator<T> {

    private final Predicate<T> skip;
    private final PeekingIterator<T> source;
    private boolean skipped = false;

    public SkipIterator(Iterator<T> source, Predicate<T> skip) {
        this.skip = checkNotNull(skip);
        this.source = Iterators.peekingIterator(checkNotNull(source));
    }

    @Override
    protected T computeNext() {
        while (!skipped && source.hasNext() && skip.test(source.peek())) {
            source.next();
        }
        skipped = true;
        if (source.hasNext()) {
            return source.next();
        } else {
            return endOfData();
        }
    }
}
