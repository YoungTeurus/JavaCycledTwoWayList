package Interfaces;

public interface IList<T> {
    void append(T object);
    void remove(T object);

    int indexOf(T object);

    int length();

    Iterator<T> iterator();
}
