package implemitations;

import interfaces.TwoWayListItem;

public class TwoWayIterator<T> implements interfaces.TwoWayIterator<T> {
    protected TwoWayListItem<T> currentListItem;
    private boolean _haveToMoveCurrentItem;
    private boolean _haveMovedCurrentItem;

    TwoWayIterator(TwoWayListItem<T> listItem){
        currentListItem = listItem;
        _haveToMoveCurrentItem = false;
        _haveMovedCurrentItem = false;
    }

    public T getNext() {
        if(haveToMoveCurrentItem()){
            currentListItem = currentListItem.getNext();
            _haveMovedCurrentItem = true;
        }
        _haveToMoveCurrentItem = true;
        return currentListItem.getItem();
    }

    public T getPrevious(){
        if(haveToMoveCurrentItem()){
            currentListItem = currentListItem.getPrevious();
            _haveMovedCurrentItem = true;
        }
        _haveToMoveCurrentItem = true;
        return currentListItem.getItem();
    }

    public boolean hasNext() {
        // TODO: проверть тестами это место, ибо currentListItem теперь чаще может быть null-ом
        if (haveToMoveCurrentItem()){
            return !isCurrentOrNextListItemNull();
        }
        return !isCurrentListItemNull();
    }

    public boolean hasPrevious(){
        if (haveToMoveCurrentItem()){
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
        if(!haveToMoveCurrentItem()){
            throw new Exception("You should iterate at least once to remove element!");
        }
        if(isCurrentListItemNull()){
            throw new Exception("Current element is NULL!");
        }

        // Если элемент зациклен на самого себя, значит он единственный, достаточно удалить только его:
        if(((implemitations.TwoWayListItem<T>)currentListItem).isSelfCycled()){
            ((implemitations.TwoWayListItem<T>) currentListItem).resetConnections();
        } else {
            replaceCurrentListItemWithNextAndRemoveCurrent();
        }
    }

    private void replaceCurrentListItemWithNextAndRemoveCurrent(){
        implemitations.TwoWayListItem<T> currentListItem = (implemitations.TwoWayListItem<T>)this.currentListItem;
        this.currentListItem = currentListItem.getNext();
        currentListItem.connectPreviousToNextAndResetConnections();
        _haveToMoveCurrentItem = false;  // Сбрасываем флаг итерации, чтобы при следующем getNext() не сдвинуться с нового элемента.
    }

    protected boolean haveToMoveCurrentItem(){
        return _haveToMoveCurrentItem;
    }

    protected boolean haveMovedCurrentItem(){
        return _haveMovedCurrentItem;
    }
}
