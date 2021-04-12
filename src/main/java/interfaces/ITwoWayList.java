package interfaces;

import implemitations.CycledTwoWayIterator;

public interface ITwoWayList<T> extends IList<T> {
    CycledTwoWayIterator<T> iteratorTail();
}
