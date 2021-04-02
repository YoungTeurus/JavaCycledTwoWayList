package Interfaces;

public interface ListItem<T> {
    T getItem();
    ListItem<T> getNext();

    int compareItem(T other);
}