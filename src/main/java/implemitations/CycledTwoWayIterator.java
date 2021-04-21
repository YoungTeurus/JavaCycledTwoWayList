package implemitations;

import interfaces.TwoWayListItem;

public class CycledTwoWayIterator<T> extends TwoWayIterator<T> implements interfaces.CycledTwoWayIterator<T> {
    private final TwoWayListItem<T> startListItem;


    CycledTwoWayIterator(TwoWayListItem<T> listItem) {
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

    UDP: Вроде как решение найдено: можно создать поле "Memory memoryForStartItem;" в Iterator.
    Когда оно равно null, Iterator работает как обычный итератор, когда не null - сохраняем в нём первый listItem и
    проверяем memory в getNext()/getPrevious().
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
