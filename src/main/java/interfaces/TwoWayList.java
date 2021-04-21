package interfaces;

import implemitations.CycledTwoWayIterator;

public interface TwoWayList<T> extends List<T> {
    CycledTwoWayIterator<T> iteratorTail();
}
