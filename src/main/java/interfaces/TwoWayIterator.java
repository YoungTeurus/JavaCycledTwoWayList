package interfaces;

public interface TwoWayIterator<T> extends Iterator<T> {
    T getPrevious();
    boolean hasPrevious();
}
