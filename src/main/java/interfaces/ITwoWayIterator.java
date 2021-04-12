package interfaces;

public interface ITwoWayIterator<T> extends Iterator<T> {
    T getPrevious();
    boolean hasPrevious();
}