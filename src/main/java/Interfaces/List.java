package Interfaces;

public interface List<T> {
    void append(T object);
    void remove(T object);

    int indexOf(T object);

    int length();

    Iterator<T> iterator();
}
