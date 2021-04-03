package Interfaces;

public interface Iterator<T> {
    T getNext();
    boolean hasNext();

    void remove() throws Exception;
}
