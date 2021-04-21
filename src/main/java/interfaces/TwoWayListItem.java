package interfaces;

public interface TwoWayListItem<T> extends ListItem<T> {
    @Override
    TwoWayListItem<T> getNext();
    TwoWayListItem<T> getPrevious();
}
