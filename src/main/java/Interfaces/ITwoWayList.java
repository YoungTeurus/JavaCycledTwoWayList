package Interfaces;

import CycledTwoWayList.CycledTwoWayIterator;

public interface ITwoWayList<T> extends IList<T> {
    CycledTwoWayIterator<T> iteratorTail();
}
