package CycledTwoWayList;

import Interfaces.TwoWayListItem;

public class TwoWayIterator<T> implements Interfaces.TwoWayIterator<T> {
    protected TwoWayListItem<T> currentListItem;
    private boolean _hasIteratedOnce;

    TwoWayIterator(TwoWayListItem<T> listItem){
        currentListItem = listItem;
        _hasIteratedOnce = false;
    }

    public T getNext() {
        T currentItem = currentListItem.getItem();
        currentListItem = currentListItem.getNext();
        _hasIteratedOnce = true;
        return currentItem;
    }

    public T getPrevious(){
        T currentItem = currentListItem.getItem();
        currentListItem = currentListItem.getPrevious();
        _hasIteratedOnce = true;
        return currentItem;
    }

    public boolean hasNext() {
        // TODO: проверть тестами это место, ибо currentListItem теперь чаще может быть null-ом
        return !isCurrentListItemNull();
        // if (!hasIteratedOnce()){
        //     return !isCurrentListItemNull();
        // }
        // return !isCurrentOrNextListItemNull();
    }

    public boolean hasPrevious(){
        return !isCurrentListItemNull();
        // if(isCurrentOrPreviousListItemNull()){
        //     return false;
        // }
        // return true;
    }

    private boolean isCurrentOrNextListItemNull(){
        return isCurrentListItemNull() || currentListItem.getNext() == null;
    }

    private boolean isCurrentOrPreviousListItemNull(){
        return isCurrentListItemNull() || currentListItem.getPrevious() == null;
    }

    private boolean isCurrentListItemNull(){
        return currentListItem == null;
    }

    public void remove() {
        // TODO: реализовать удаление!
    }

    protected boolean hasIteratedOnce(){
        return _hasIteratedOnce;
    }
}
