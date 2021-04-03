package CycledTwoWayList;

import Interfaces.ITwoWayIterator;
import Interfaces.ITwoWayListItem;

public class TwoWayIterator<T> implements ITwoWayIterator<T> {
    protected ITwoWayListItem<T> currentListItem;
    private boolean _hasIteratedOnce;

    TwoWayIterator(ITwoWayListItem<T> listItem){
        currentListItem = listItem;
        _hasIteratedOnce = false;
    }

    public T getNext() {
        if(hasIteratedOnce()){
            currentListItem = currentListItem.getNext();
        }
        _hasIteratedOnce = true;
        return currentListItem.getItem();
    }

    public T getPrevious(){
        if(hasIteratedOnce()){
            currentListItem = currentListItem.getPrevious();
        }
        _hasIteratedOnce = true;
        return currentListItem.getItem();
    }

    public boolean hasNext() {
        // TODO: проверть тестами это место, ибо currentListItem теперь чаще может быть null-ом
        if (hasIteratedOnce()){
            return !isCurrentOrNextListItemNull();
        }
        return !isCurrentListItemNull();
    }

    public boolean hasPrevious(){
        if (hasIteratedOnce()){
            return !isCurrentOrPreviousListItemNull();
        }
        return !isCurrentListItemNull();
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

    public void remove() throws Exception {
        if(!hasIteratedOnce()){
            throw new Exception("You should iterate at least once to remove element!");
        }
        if(isCurrentListItemNull()){
            throw new Exception("Current element is NULL!");
        }

        // Если элемент зациклен на самого себя, значит он единственный, достаточно удалить только его:
        if(((TwoWayListItem<T>)currentListItem).isSelfCycled()){
            ((TwoWayListItem<T>) currentListItem).resetConnections();
        } else {
            replaceCurrentListItemWithNextAndRemoveCurrent();
        }
    }

    private void replaceCurrentListItemWithNextAndRemoveCurrent(){
        TwoWayListItem<T> currentListItem = (TwoWayListItem<T>)this.currentListItem;
        this.currentListItem = currentListItem.getNext();
        currentListItem.connectPreviousToNextAndResetConnections();
        _hasIteratedOnce = false;  // Сбрасываем флаг итерации, чтобы при следующем getNext() не сдвинуться с нового элемента.
    }

    protected boolean hasIteratedOnce(){
        return _hasIteratedOnce;
    }
}
