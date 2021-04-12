package interfaces;

public interface IListItem<T> {
    T getItem();
    IListItem<T> getNext();

    int compareItem(T other);
}
