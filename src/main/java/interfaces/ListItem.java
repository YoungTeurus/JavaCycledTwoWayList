package interfaces;

public interface ListItem<T> {
    /**
     * Возвращает содержимое элемента.
     * @return Содержимое элемента.
     */
    T getItem();

    /**
     * Возвращает следующий элемент списка.
     * @return Следующий элемент списка.
     */
    ListItem<T> getNext();

    /**
     * Осуществляет сравнение текущего содержимого элемента с другим элементом.
     * @param other Элемент для сравнения. Должен реализовать интерфейс Comparable.
     * @exception Exception Исключение генерируется, если элемент other не реализует интерфейс Comparable.
     * @return Результат .compare(), применённый для other и текущего содержимого.
     */
    int compareItem(T other) throws Exception;
}
