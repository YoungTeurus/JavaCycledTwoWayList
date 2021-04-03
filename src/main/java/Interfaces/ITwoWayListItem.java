package Interfaces;

public interface ITwoWayListItem<T> extends IListItem<T> {
    @Override
    ITwoWayListItem<T> getNext();
    ITwoWayListItem<T> getPrevious();
}
