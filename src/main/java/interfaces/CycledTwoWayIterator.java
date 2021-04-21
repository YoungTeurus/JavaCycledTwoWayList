package interfaces;

public interface CycledTwoWayIterator<T> extends TwoWayIterator<T> {
    boolean reachedEnd();
}
