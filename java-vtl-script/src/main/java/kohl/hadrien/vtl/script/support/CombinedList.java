package kohl.hadrien.vtl.script.support;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

import static com.google.common.base.Preconditions.checkNotNull;

public class CombinedList<T> extends AbstractList<T> implements RandomAccess {

    final List<List<T>> lists;
    int size = 0;

    public CombinedList(List<T>... lists) {
        this(Arrays.asList(lists));
    }

    public CombinedList(List<List<T>> lists) {
        this.lists = checkNotNull(lists);
        for (List<T> list : lists) {
            // TODO: Check for overflow.
            size += list.size();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        for (List<T> list : lists) {
            if (index < list.size())
                return list.get(index);
            index -= list.size();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public T set(int index, T element) {
        for (List<T> list : lists) {
            if (index < list.size())
                return list.set(index, element);
            index -= list.size();
        }
        throw new IndexOutOfBoundsException();
    }
}
