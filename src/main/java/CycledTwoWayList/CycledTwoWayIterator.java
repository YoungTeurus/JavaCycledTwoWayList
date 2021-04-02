package CycledTwoWayList;

import Interfaces.TwoWayListItem;

public class CycledTwoWayIterator<T> extends TwoWayIterator<T> implements Interfaces.CycledTwoWayIterator<T> {
    private TwoWayListItem<T> startListItem;
    private boolean hasIteratedOnce;

    CycledTwoWayIterator(TwoWayListItem<T> listItem) {
        super(listItem);
        startListItem = listItem;
        hasIteratedOnce = false;
    }

    @Override
    public T getNext() {
        hasIteratedOnce = true;
        return super.getNext();
    }

    @Override
    public T getPrevious() {
        hasIteratedOnce = true;
        return super.getPrevious();
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
        return hasIteratedOnce && isCurrentEqualsStartItem();
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
