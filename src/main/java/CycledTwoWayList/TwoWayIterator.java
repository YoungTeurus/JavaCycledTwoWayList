package CycledTwoWayList;

import Interfaces.TwoWayListItem;

public class TwoWayIterator<T> implements Interfaces.TwoWayIterator<T> {
    protected TwoWayListItem<T> currentListItem;

    TwoWayIterator(TwoWayListItem<T> listItem){
        currentListItem = listItem;
    }

    public T getNext() {
        T currentItem = currentListItem.getItem();
        currentListItem = currentListItem.getNext();
        return currentItem;
    }

    public T getPrevious(){
        currentListItem = currentListItem.getPrevious();
        return currentListItem.getItem();
    }

    public boolean hasNext() {
        if(isCurrentOrNextListItemNull()){
            return false;
        }
        return true;
    }

    public boolean hasPrevious(){
        if(isCurrentOrPreviousListItemNull()){
            return false;
        }
        return true;
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

    }
}
