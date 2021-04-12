package implemitations;

import interfaces.ITwoWayListItem;

public class CycledTwoWayIterator<T> extends TwoWayIterator<T> implements interfaces.CycledTwoWayIterator<T> {
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
        return !hasNext() || isNextEqualsStartItemAndHaveMovedCurrentItem();
    }

    private boolean isNextEqualsStartItemAndHaveMovedCurrentItem(){
        return haveMovedCurrentItem() && isNextEqualsStartItem();
    }

    private boolean isNextEqualsStartItem(){
        return currentListItem.getNext() == startListItem;
    }
}
