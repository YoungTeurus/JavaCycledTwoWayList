package interfaces;

import java.util.function.Function;

public interface List<T> {
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
     * Возвращает элемент из списка на индексе index.
     * @param index Индекс элемента в списке.
     * @exception Exception Вызывается исключение, если список пуст.
     * @return Элемент на указанном индексе или null, если индекс находится за пределами списка.
     */
    T atIndex(int index) throws Exception;

    /**
     * Возвращает длину списка.
     * @return Длина списка.
     */
    int length();

    /**
     * Проверяет, является ли список пустым.
     * @return True, если список пуст, иначе - false.
     */
    boolean isEmpty();

    /**
     * Вовзвращает соответствующий итератор для списка.
     * @return Итератор для списка.
     */
    Iterator<T> iterator();

    /**
     * Применяет функцию functionToApply для каждого содержимого элемента списка.
     * Функции передаётся текущее содержимое элемента списка.
     * Если функция возвращает false, обход завершается преждевременно.
     * @param functionToApply Функция для применения.
     */
    void map(Function<T, Boolean> functionToApply);
}
