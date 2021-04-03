package CycledTwoWayList;

import Interfaces.ITwoWayListItem;

public class CycledTwoWayIterator<T> extends TwoWayIterator<T> implements Interfaces.CycledTwoWayIterator<T> {
    private ITwoWayListItem<T> startListItem;


    CycledTwoWayIterator(ITwoWayListItem<T> listItem) {
        super(listItem);
        startListItem = listItem;
    }

    /*
    По идее данную функцию нужно вынести в CycledIterator.
    Множественной наследование: reachedEnd должно наследоваться из CycledIterator, а getPrevious - из TwoWayIterator.

      (getNext)              (reachedEnd)
      Iterator      ->      CycledIterator
          |                        |
          V                        V
    TwoWayIterator  ->   CycledTwoWayIterator
     (getPrevious)

     */
    public boolean reachedEnd(){
        return !hasNext() || isCurrentEqualsStartItemAndHasIteratedOnce();
    }

    private boolean isCurrentEqualsStartItemAndHasIteratedOnce(){
        return hasIteratedOnce() && isCurrentEqualsStartItem();
    }

    private boolean isCurrentEqualsStartItem(){
        return currentListItem == startListItem;
    }


    boolean isCurrentItemEquals(T other){
        return compareCurrentItem(other) == 0;
    }

    int compareCurrentItem(T compareTo){
        return currentListItem.compareItem(compareTo);
    }
}
