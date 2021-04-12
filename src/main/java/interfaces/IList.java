package interfaces;

import java.util.function.Function;

public interface IList<T> {
    /**
     * Добавляет элемент в конец списка.
     * @param object Элемент для добавления.
     */
    void append(T object);

    /**
     * Удаляет элемент из списка, если он в нём находится.
     * @param object Элемент для удаления.
     */
    void remove(T object);

    /**
     * Возвращает индекс элемента в списке, если он в нём находится.
     * @param object Элемент для поиска.
     * @return Индекс элемента в списке или -1, если он в нём не находится.
     */
    int indexOf(T object);

    /**
     * Возварщает элемент из списка на индексе index.
     * @param index Индекс элемента в списке.
     * @return Элемент на указанном индексе или null, если индекс находится за пределами списка.
     */
    T atIndex(int index);

    int length();
    boolean isEmpty();

    Iterator<T> iterator();
    void map(Function<T, Boolean> functionToApply);
}
