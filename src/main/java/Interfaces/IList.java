package Interfaces;

import java.util.function.Function;

public interface IList<T> {
    void append(T object);
    void remove(T object);

    int indexOf(T object);
    T atIndex(int index);

    int length();
    boolean isEmpty();

    Iterator<T> iterator();
    void map(Function<T, Boolean> functionToApply);
}
